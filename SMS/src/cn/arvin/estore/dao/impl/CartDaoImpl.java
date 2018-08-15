package cn.arvin.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.arvin.estore.dao.CartDao;
import cn.arvin.estore.domain.Cart;
import cn.arvin.estore.utils.JDBCUtils;

public class CartDaoImpl implements CartDao {
	
	private  QueryRunner run = new QueryRunner();
	private  QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	@Override
	public Cart findCartByUidAndPid(int uid, String pid) {
		String sql = "select * from cart where uid = ? and pid = ?";
		try {
			return qr.query(sql, new BeanHandler<Cart>(Cart.class), uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户商品购物车");
		}
	}
	
	@Override
	public void addProductToCart(int uid, String pid, int buynum) {
		String sql ="update cart set buynum = ? where uid = ? and pid = ?";
		try {
			qr.update(sql, buynum,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加购物车数量失败");
		}
	}

	@Override
	public void createCart(int uid, String pid, int buynum) {
		String sql = "insert into cart values(?,?,?)";
		try {
			qr.update(sql,uid,pid, buynum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建购物车失败");
		}
	}

	@Override
	public void queryCartByUid(int uid, List<Cart> clist) {
		//封装数据要完整，否则cart.jsp页面的数据获取不到，传到下一个请求会失败
//		String sql = "select c.buynum,p.pname,p.market_price,p.shop_price,p.pimage from cart as c, product as p where c.pid = p.pid and c.uid = ? ";
		String sql = "select c.*,p.* from cart as c, product as p where c.pid = p.pid and c.uid = ? ";
		try {
			clist.addAll(qr.query(sql, new BeanListHandler<Cart>(Cart.class),uid)) ;
		} catch (SQLException e) {
			throw new RuntimeException("查询用户购物车失败");
		}
	}

	@Override
	public void update(int uid, String pid, int buynum) {
		String sql = "update cart set buynum = ? where uid = ? and pid = ?";
		try {
			qr.update(sql, buynum,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新购物车数量失败");
		}
	}

	@Override
	public void deleteCartByPid(int id, String pid) {
		String sql = "delete from cart where uid = ? and pid = ?";
		try {
			qr.update(sql, id,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除购物车失败");
		}
	}

	@Override
	public void deleteCartByUid(int uid) {
		String sql = "delete from cart where uid = ?";
		try {
			 run.update(JDBCUtils.getCurrentConnection(),sql, uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("清除购物车失败");
		}
	}
	
}
