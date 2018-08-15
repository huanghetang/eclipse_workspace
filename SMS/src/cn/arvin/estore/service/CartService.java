package cn.arvin.estore.service;

import java.util.List;

import cn.arvin.estore.domain.Cart;
import cn.arvin.estore.domain.User;

public interface CartService {

	/**
	 *添加商品到购物车 
	 * @param loginUser
	 * @param pid
	 * @param buynum
	 */
	void addProductToCart(User loginUser, String pid, int buynum);

	/**
	 * 查询购物车信息
	 * @param uid
	 * @param clist
	 */
	void queryCartByUid(int uid, List<Cart> clist);

	/**
	 * 修改购买数量
	 * @param uid
	 * @param pid
	 * @param buynum
	 * @param carts
	 */
	void changeBuynum(int uid, String pid, int buynum, List<Cart> carts);

	/**
	 * 删除购物车
	 * @param id
	 * @param pid
	 */
	void deleteCartByPid(int id, String pid);

}
