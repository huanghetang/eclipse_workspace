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
		//�½�һ����ѯ����
		SolrQuery solrQuery = new SolrQuery();
		//���ùؼ���
		if(queryString !=null && !queryString.equals("")){
			solrQuery.setQuery(queryString);
			//����Ĭ��������
			solrQuery.set("df", "product_keywords");
		}
		//��Ʒ����
		if(catalog_name!=null && !catalog_name.equals("")){
			solrQuery.addFilterQuery("product_catalog_name:"+catalog_name);
		}
	
		//��������
		if(price!=null && !"".equals(price.trim())){
			String[] split = price.split("-");
			solrQuery.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		
		//�۸�����
		if(sort!=null && sort.equals("1")){
			solrQuery.setSort("product_price", ORDER.desc);
		}else{
			solrQuery.setSort("product_price", ORDER.asc);
		}
		//��ҳ,��ʼ,������
		solrQuery.setStart(0);
		solrQuery.setRows(12);
		//������ʾ����
		solrQuery.setFields("id","product_name","product_price","product_picture");
		
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
		
		//��ȡ���������,ͨ��id��ȡ�ĵ�,ͨ���ĵ��е�������ȡ���������ĸ�����ʾ����,��ǰֻ��һ��Ԫ��
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
		//��ý����
		SolrDocumentList docs = queryResponse.getResults();
		System.out.println("����ѯ��"+docs.getNumFound()+"������");
		
		List<ProductModel> productModelList = new ArrayList<>();
		//��������
		for (SolrDocument solrDocument : docs) {
			ProductModel productModel = new ProductModel();
			String id = (String)solrDocument.get("id");
			productModel.setPid((String)solrDocument.get("id"));
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("product_name");
			//���������ʾ������ֶ�
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
			findAll("�ֻ�","�ֻ�����","*-6000","");
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
	}

}
