package vip.hht.estore.service;

import java.util.HashMap;

import vip.hht.estore.beans.Product;

public interface ProductService {

	void productList(int page1, int rows1, HashMap<String, Object> map);

	boolean addProduct(Product product);

}
