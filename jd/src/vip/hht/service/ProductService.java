package vip.hht.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import vip.hht.pojo.ProductModel;

public interface ProductService {
	public List<ProductModel> findAll(String queryString,String catalog_name,String price,String sort) throws SolrServerException;

}
