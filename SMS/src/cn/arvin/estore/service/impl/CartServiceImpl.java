package cn.arvin.estore.service.impl;

import java.util.List;

import cn.arvin.estore.dao.CartDao;
import cn.arvin.estore.dao.impl.CartDaoImpl;
import cn.arvin.estore.domain.Cart;
import cn.arvin.estore.domain.User;
import cn.arvin.estore.factory.BeanFactory;
import cn.arvin.estore.service.CartService;

public class CartServiceImpl implements CartService {
	
	private CartDao cartDao = BeanFactory.getInstance(CartDao.class);
	
	@Override
	public void addProductToCart(User loginUser, String pid, int buynum) {
		//判断用户是否有添加过此商品
		Cart cart = cartDao.findCartByUidAndPid(loginUser.getId(),pid);
		if(cart != null){
			//累加数量
			buynum += cart.getBuynum();
			cartDao.addProductToCart(loginUser.getId(), pid, buynum);
		}else{
			//创建购物车添加数量
			cartDao.createCart(loginUser.getId(), pid, buynum);
		}
	}

	@Override
	public void queryCartByUid(int uid, List<Cart> clist) {
		cartDao.queryCartByUid(uid, clist);
	}

	@Override
	public void changeBuynum(int uid, String pid, int buynum, List<Cart> carts) {
		cartDao.update(uid, pid, buynum);
		cartDao.queryCartByUid(uid, carts);
	}

	@Override
	public void deleteCartByPid(int id, String pid) {
		cartDao.deleteCartByPid(id,pid);
	}

}
