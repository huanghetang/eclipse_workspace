package vip.hht.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 使用solr框剪管理索引库
 * @author zhoumo
 *
 */
public class IndexManager {
	
	//向索引库中添加索引,id相同就是修改(就是lucene修改的原理,先删除原来的索引然后修改)
	@Test
	public void  addDocument() throws SolrServerException, IOException{
//		第三步：和Solr服务器建立连接。HttpSolrServer对象建立连接。
//		第四步：创建一个SolrInputDocument对象，然后添加域。
//		第五步：将SolrInputDocument添加到索引库。
//		第六步：提交。
		HttpSolrServer solerServer = new HttpSolrServer("http://localhost:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "c001");
		document.addField("product_name", "台灯");
		document.addField("product_catalog_name", "不知道台灯");
		document.addField("product_price", "20000000");
		solerServer.add(document);
		solerServer.commit();
		
	}
	//向索引库中删除索引
	@Test
	public void updateDocument(){
		
	}
	
	//删除指定的索引
	@Test
	public void deleteDocument() throws SolrServerException, IOException{
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");//单机版的用HttpSolrServer,云服务的用CloudSolrServer
//		solrServer.deleteByQuery("*:*");
		solrServer.deleteById("c001");
		solrServer.commit();
	}
	
	
	//查询索引
	@Test
	public void queryIndex() throws SolrServerException, IOException{
			//创建连接
			HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
			
			//创建一个query对象
			SolrQuery solrQuery = new SolrQuery();
			//设置查询条件
			solrQuery.setQuery("product_name:台灯");
			//执行查询
			QueryResponse query = solrServer.query(solrQuery);
			//取查询结果
			SolrDocumentList results = query.getResults();
			//共查询到商品数量
			long numFound = results.getNumFound();
			System.out.println("共查询到:"+numFound);
			//遍历查询的结果
			for (SolrDocument solrDocument : results) {
				String id = (String) solrDocument.get("id");
				System.out.println(id);
				String product_name = (String) solrDocument.get("product_name");
//				String product_price = (String) solrDocument.get("product_price");
				String product_catalog_name = (String) solrDocument.get("product_catalog_name");
				String product_picture = (String) solrDocument.get("product_picture");
				System.out.println(id+","+product_name+","+product_catalog_name+","+product_picture+",");
				System.out.println("====================================================");
			}
		
	}
	
	//其中包含查询、过滤、分页、排序、高亮显示等处理。
	@Test
	public void multipleQuery() throws SolrServerException{
		//创建本地Solr服务连接
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		
		//新建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery("product_name:三星");
		//排序条件
		solrQuery.setSort("product_price", ORDER.desc);
		//过滤条件
		solrQuery.setFilterQueries("product_price:[* TO 6000]","product_catalog_name:手机数码");
		//分页,开始,多少行
		solrQuery.setStart(0);
		solrQuery.setRows(8);
		//设置显示的域
		solrQuery.setFields("id","product_name","product_price","product_catalog_name","product_picture");
		//设置默认搜索域
		solrQuery.set("df", "product_keywords");
		//高亮显示开关
		solrQuery.setHighlight(true);
		//指定高亮显示的域
		solrQuery.addHighlightField("product_name");
		//设置高亮前标签
		solrQuery.setHighlightSimplePre("<font color='red'>");
		//s设置高亮后标签
		solrQuery.setHighlightSimplePost("</font>");
		//执行查询
		QueryResponse queryResponse = solrServer.query(solrQuery);
		//获得结果集
		SolrDocumentList response = queryResponse.getResults();
		//获取高亮结果集,通过id获取文档,通过文档中的域名获取多个结果集的高亮显示集合,当前只有一个元素
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		System.out.println(response.getNumFound());
		//处理结果集
		for (SolrDocument solrDocument : response) {
			String id = (String)solrDocument.get("id");
			System.out.println(id);
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("product_name");
			if(list!=null){
				System.out.println(list.get(0));
			}else{
				System.out.println(solrDocument.get("product_name"));
			}
			System.out.println(solrDocument.get("product_name"));
			System.out.println(solrDocument.get("product_price"));
			System.out.println("==============================");
		}
		
	}
	
	

}
