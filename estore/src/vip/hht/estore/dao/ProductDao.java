package vip.hht.estore.dao;

import java.util.List;

import vip.hht.estore.beans.Product;

public interface ProductDao {

	List<Product> productList(int startIndex, int size);

	long totalCounts();

	boolean addProduct(Product product);


}
