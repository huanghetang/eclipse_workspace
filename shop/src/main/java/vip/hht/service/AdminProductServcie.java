package vip.hht.service;

import java.util.List;

import vip.hht.beans.PageBean;
import vip.hht.beans.Product;

public interface AdminProductServcie {

	/**
	 * 查询当前页所有商品
	 * @return
	 */
	PageBean findPageProduct(int pageNum);

	
	/**
	 * 根据商品主键pid查询商品
	 * @param pid
	 * @return
	 */
	Product findProductById(String pid);


	/**
	 * 根据商品Id修改商品
	 * @param product
	 */
	void editProduct(Product product);


	/**
	 * 新增商品
	 * @param product
	 */
	void addProduct(Product product);


	/**
	 * 根据一个id删除
	 * @param pid
	 */
	void deleteById(String pid);


	/**
	 * 批量删除
	 * @param pids
	 */
	void banchDel(Integer[] pids);

}
