package vip.hht.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.Cart;
import vip.hht.beans.CartModel;
import vip.hht.beans.Category;
import vip.hht.beans.Product;
import vip.hht.dao.CartDao;

public class CartDaoImpl implements CartDao {

	@Override
	public Cart findCartById(String pid, String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from cart where pid =? and uid = ?";
		try {
			return	queryRunner.query(sql, new BeanHandler<Cart>(Cart.class),pid,cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void saveCart(String pid, String cid, String buynum) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="insert into cart(pid,uid,buynum) values(?,?,?)";
		try {
			queryRunner.update(sql, pid,cid,buynum);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void incrCart(String pid, String cid, int buynum) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="update cart set buynum = ? where pid = ? and uid = ? ";
		try {
			queryRunner.update(sql,buynum,pid,cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List<CartModel> findList(int uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="SELECT c.*,p.* FROM cart c INNER JOIN product p ON p.pid = c.pid WHERE c.uid = ?";
		try {
			return	queryRunner.query(sql, new BeanListHandler<CartModel>(CartModel.class),uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteCartById(String uid, String pid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="delete from cart where uid = ? and pid = ? ";
		try {
			queryRunner.update(sql,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
/*	@Override
	public List<CartModel> findCartModelListById(int id, String[] pids) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="SELECT c.*,p.* FROM cart c INNER JOIN product p ON p.pid = c.pid WHERE c.uid ="+id+" and c.pid in(";
		StringBuilder params = new StringBuilder();
		for (int i=0;i<pids.length;i++) {
			if(i==pids.length-1){
				params.append(pids[i]).append(")");
			}else{
				params.append(pids[i]).append(",");
			}
		}
		sql = sql+params.toString();
		try {
			List<CartModel> list=queryRunner.query(sql, new BeanListHandler<CartModel>(CartModel.class));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		
		
	}*/
	
	@Override
	public List<CartModel> findCartModelListById(int id, String[] pids) {
		List<CartModel> list = new ArrayList<CartModel>();
		QueryRunner queryRunner = new QueryRunner();
		 Connection con = JDBCUtils.getConnection();
		String sql = "SELECT c.*,p.* FROM cart c INNER JOIN product p ON p.pid = c.pid WHERE c.uid =? and c.pid = ?";
		try {
			//开启手动事务
			con.setAutoCommit(false);
			//遍历查询每一个数据
			for (String pid : pids) {
				CartModel cartModel = queryRunner.query(con, sql, new BeanHandler<CartModel>(CartModel.class),id,pid);
				//添加
				list.add(cartModel);
			}
			con.commit();
			con.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	

	@Override
	public void deleteCartByIds(int id, String[] pids) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = JDBCUtils.getCurrentConnection();
		try {
			connection.setAutoCommit(false);
			String sql ="delete from cart where uid = "+id+" and pid = ? ";
			for (String pid : pids) {
				queryRunner.update(connection,sql,pid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List findOrderItemList(String[] pids) {
		return null;
	}

	@Override
	public void deleteCartByIds(Connection con, Integer uid, String pid) {
		QueryRunner queryRunner = new QueryRunner();
		String sql ="delete from cart where uid = ? and pid = ? ";
		try {
			queryRunner.update(con,sql,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void editPnumByPid(String pnum, String pid,String uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="update cart set buynum =? where uid = ? and pid = ? ";
		try {
			queryRunner.update(sql,pnum,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
