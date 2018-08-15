package vip.hht.dao;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import vip.hht.pojo.ProductModel;

public interface Dao {
	public List<ProductModel> findAll(String queryString,String catalog_name,String price,String sort) throws SolrServerException ;
}
