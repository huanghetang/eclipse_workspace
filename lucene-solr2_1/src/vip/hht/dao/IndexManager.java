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
 * ʹ��solr�������������
 * @author zhoumo
 *
 */
public class IndexManager {
	
	//�����������������,id��ͬ�����޸�(����lucene�޸ĵ�ԭ��,��ɾ��ԭ��������Ȼ���޸�)
	@Test
	public void  addDocument() throws SolrServerException, IOException{
//		����������Solr�������������ӡ�HttpSolrServer���������ӡ�
//		���Ĳ�������һ��SolrInputDocument����Ȼ�������
//		���岽����SolrInputDocument��ӵ������⡣
//		���������ύ��
		HttpSolrServer solerServer = new HttpSolrServer("http://localhost:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "c001");
		document.addField("product_name", "̨��");
		document.addField("product_catalog_name", "��֪��̨��");
		document.addField("product_price", "20000000");
		solerServer.add(document);
		solerServer.commit();
		
	}
	//����������ɾ������
	@Test
	public void updateDocument(){
		
	}
	
	//ɾ��ָ��������
	@Test
	public void deleteDocument() throws SolrServerException, IOException{
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");//���������HttpSolrServer,�Ʒ������CloudSolrServer
//		solrServer.deleteByQuery("*:*");
		solrServer.deleteById("c001");
		solrServer.commit();
	}
	
	
	//��ѯ����
	@Test
	public void queryIndex() throws SolrServerException, IOException{
			//��������
			HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
			
			//����һ��query����
			SolrQuery solrQuery = new SolrQuery();
			//���ò�ѯ����
			solrQuery.setQuery("product_name:̨��");
			//ִ�в�ѯ
			QueryResponse query = solrServer.query(solrQuery);
			//ȡ��ѯ���
			SolrDocumentList results = query.getResults();
			//����ѯ����Ʒ����
			long numFound = results.getNumFound();
			System.out.println("����ѯ��:"+numFound);
			//������ѯ�Ľ��
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
	
	//���а�����ѯ�����ˡ���ҳ�����򡢸�����ʾ�ȴ���
	@Test
	public void multipleQuery() throws SolrServerException{
		//��������Solr��������
		HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
		
		//�½�һ����ѯ����
		SolrQuery solrQuery = new SolrQuery();
		//���ò�ѯ����
		solrQuery.setQuery("product_name:����");
		//��������
		solrQuery.setSort("product_price", ORDER.desc);
		//��������
		solrQuery.setFilterQueries("product_price:[* TO 6000]","product_catalog_name:�ֻ�����");
		//��ҳ,��ʼ,������
		solrQuery.setStart(0);
		solrQuery.setRows(8);
		//������ʾ����
		solrQuery.setFields("id","product_name","product_price","product_catalog_name","product_picture");
		//����Ĭ��������
		solrQuery.set("df", "product_keywords");
		//������ʾ����
		solrQuery.setHighlight(true);
		//ָ��������ʾ����
		solrQuery.addHighlightField("product_name");
		//���ø���ǰ��ǩ
		solrQuery.setHighlightSimplePre("<font color='red'>");
		//s���ø������ǩ
		solrQuery.setHighlightSimplePost("</font>");
		//ִ�в�ѯ
		QueryResponse queryResponse = solrServer.query(solrQuery);
		//��ý����
		SolrDocumentList response = queryResponse.getResults();
		//��ȡ���������,ͨ��id��ȡ�ĵ�,ͨ���ĵ��е�������ȡ���������ĸ�����ʾ����,��ǰֻ��һ��Ԫ��
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		System.out.println(response.getNumFound());
		//��������
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
