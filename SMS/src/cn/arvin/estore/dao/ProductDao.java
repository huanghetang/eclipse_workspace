package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.Product;

public interface ProductDao {

	/**
	 * 解除外键
	 * @param cid
	 */
	void release(String cid);

	/**
	 * 查询所有商品信息
	 * @param list 
	 */
	void findAll(List<Product> list);

	/**
	 * 获取商品信息
	 * @param pid
	 * @return
	 */
	Product findProductByPid(String pid);

	/**
	 * 查询商品库存
	 * @param pid
	 * @return
	 */
	Product findPnumByPid(String pid);

	/**
	 * 生成订单减少库存
	 * @param pid
	 * @param buynum
	 */
	void minusPNum(String pid, int buynum);
	
	//后台查询
	List<Product> productList(int startIndex, int size);
	//后台查询
	long totalCounts();

	boolean addProduct(Product product);

	boolean updateProduct(Product p);

	void delete(String pid_value);

}
