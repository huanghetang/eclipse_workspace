package vip.hht.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vip.hht.pojo.ProductModel;

@Repository
public class DaoImpl implements Dao{
	@Autowired
	private SolrServer solrServer;
	
	public List<ProductModel> findAll(String queryString,String catalog_name,String price,String sort) throws SolrServerException{
		
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		//新建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		//设置关键词
		if(queryString !=null && !queryString.equals("")){
			solrQuery.setQuery(queryString);
			//设置默认搜索域
			solrQuery.set("df", "product_keywords");
		}
		//商品类型
		if(catalog_name!=null && !catalog_name.equals("")){
			solrQuery.addFilterQuery("product_catalog_name:"+catalog_name);
		}
	
		//过滤条件
		if(price!=null && !"".equals(price.trim())){
			String[] split = price.split("-");
			solrQuery.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		
		//价格排序
		if(sort!=null && sort.equals("1")){
			solrQuery.setSort("product_price", ORDER.desc);
		}else{
			solrQuery.setSort("product_price", ORDER.asc);
		}
		//分页,开始,多少行
		solrQuery.setStart(0);
		solrQuery.setRows(12);
		//设置显示的域
		solrQuery.setFields("id","product_name","product_price","product_picture");
		
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
		
		//获取高亮结果集,通过id获取文档,通过文档中的域名获取多个结果集的高亮显示集合,当前只有一个元素
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//获得结果集
		SolrDocumentList docs = queryResponse.getResults();
		System.out.println("共查询到"+docs.getNumFound()+"条数据");
		
		List<ProductModel> productModelList = new ArrayList<>();
		//处理结果集
		for (SolrDocument solrDocument : docs) {
			ProductModel productModel = new ProductModel();
			String id = (String)solrDocument.get("id");
			productModel.setPid((String)solrDocument.get("id"));
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("product_name");
			//如果高亮显示有这个字段
			if(list!=null && list.size()>0){
				productModel.setName(list.get(0));
			}else{
				productModel.setName( (String) solrDocument.get("product_name"));
			}
			productModel.setPicture((String) solrDocument.get("product_picture"));
			productModel.setPrice(Float.valueOf(solrDocument.get("product_price").toString()));
			
			productModelList.add(productModel);
		}
		return productModelList;
		
	}
	
	@Test
	public void test1(){
		try {
			findAll("手机","手机数码","*-6000","");
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}

}
