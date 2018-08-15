package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.Cart;

public interface CartDao {

	/**
	 * 添加商品
	 * @param uid
	 * @param pid
	 * @param buynum
	 */
	void addProductToCart(int uid, String pid, int buynum);

	/**
	 * 查询用户的购物车
	 * @param uid
	 * @param pid
	 * @return
	 */
	Cart findCartByUidAndPid(int uid, String pid);

	/**
	 * 创建购物车
	 * @param uid
	 * @param pid
	 * @param buynum
	 */
	void createCart(int uid, String pid, int buynum);

	/**
	 * 查询用户购物车 
	 * @param uid
	 * @param clist
	 */
	void queryCartByUid(int uid, List<Cart> clist);

	/**
	 * 更新购物车数据
	 * @param uid
	 * @param pid
	 * @param buynum
	 */
	void update(int uid, String pid, int buynum);

	/**
	 * 删除购物车
	 * @param id
	 * @param pid
	 */
	void deleteCartByPid(int id, String pid);

	/**
	 * 根据用户清空购物车
	 * @param uid
	 */
	void deleteCartByUid(int uid);

}
