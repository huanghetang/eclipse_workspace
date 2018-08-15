package vip.hht.service;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

import vip.hht.beans.PageBean;
import vip.hht.beans.Product;

public interface ProductService {

	List findProductList();

	Product findProductById(String pid);

	void addCart(String pid, String cid, String buynum);

	/**
	 * ∑÷“≥≤È—Ø
	 * @param pageNum1
	 * @param size1
	 * @return
	 */
	PageBean findProductPageList(int pageNum1, int size1);
	
	
	
	/**
	 * solr≤È—Ø
	 */
	public PageBean findKeyWords(int pageNum1, int size1,String queryString,String catalog_name,String price,String sort) throws SolrServerException;

}
