package cn.arvin.estore.service;

import java.util.HashMap;
import java.util.List;

import cn.arvin.estore.domain.Product;

public interface ProductService {

	/**
	 * 查询所有商品信息
	 * @param list
	 */
	void findAll(List<Product> list);

	/**
	 * 通过id获取商品详细信息
	 * @param pid
	 * @return
	 */
	Product findProductByPid(String pid);

	/**
	 * 根据pid减少库存
	 * @param pid
	 * @param buynum
	 */
	void updateByOrderItem(String pid, int buynum);

	/**
	 * 后台商品查询
	 * @param page1
	 * @param rows1
	 * @param map
	 */
	void productList(int page1, int rows1, HashMap<String, Object> map);

	/**
	 * 添加商品
	 * @param product
	 * @return
	 */
	boolean addProduct(Product product);

	boolean updateProduct(Product p);

	void deleteProduct(String[] pid);

}
