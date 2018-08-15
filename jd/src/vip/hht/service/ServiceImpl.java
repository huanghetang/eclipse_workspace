package vip.hht.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.dao.Dao;
import vip.hht.pojo.ProductModel;

@Service
public class ServiceImpl implements ProductService {
	@Autowired
	private Dao dao;
	
	public List<ProductModel> findAll(String queryString,String catalog_name,String price,String sort) throws SolrServerException{
		return dao.findAll(queryString, catalog_name, price, sort);
	}
	

}
