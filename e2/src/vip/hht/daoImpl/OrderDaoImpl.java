package vip.hht.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.Order;
import vip.hht.beans.OrderItemModel;
import vip.hht.beans.PCA;
import vip.hht.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<PCA> findListById(String id) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from province_city_district where pid=?";
		try {
			List<PCA> list = queryRunner.query(sql,new BeanListHandler<PCA>(PCA.class), id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void addOrder(Connection con, Order o) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO orders(id,uid,totalprice,address,STATUS,createtime) VALUES(?,?,?,?,?,null)";
		try {
			queryRunner.update(con,sql, o.getId(),o.getUid(),o.getTotalprice(),o.getAddress(),o.getStatus());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Deprecated
	@Override
	public List<Order> findOrderList() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT id ,totalprice,STATUS,createtime FROM orders";
		try {
			return  queryRunner.query(sql,new BeanListHandler<Order>(Order.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Order findOrderById(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT id ,totalprice,STATUS,createtime,address FROM orders where id = ?";
		try {
			return  queryRunner.query(sql,new BeanHandler<Order>(Order.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/*@Override
	public List<OrderItemModel> findOrderItems(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT p.pname,p.market_price,p.shop_price,o.buynum,orders.totalprice "
							+ "FROM orderitems o,product p,orders WHERE o.pid = p.pid AND o.oid = orders.id AND o.oid =?";
		try {
			return  queryRunner.query(sql,new BeanListHandler<OrderItemModel>(OrderItemModel.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}*/
	
	@Override
	public List<OrderItemModel> findOrderItems(String oid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT p.pid,p.pname,p.market_price,p.shop_price,o.buynum,orders.totalprice,o.isComment "
							+ "FROM orderitems o,product p,orders WHERE o.pid = p.pid AND o.oid = orders.id AND o.oid =?";
		try {
			return  queryRunner.query(sql,new BeanListHandler<OrderItemModel>(OrderItemModel.class),oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@Override
	public void payok(String id) {
		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "update orders set status=1 where id = ?";
			runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findOrderListByTime(String startTime, String endTime,int uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT id ,totalprice,STATUS,createtime FROM orders "
							+ "where uid = ? and createtime>=? and createtime < ? order by createtime desc";
		try {
			return  queryRunner.query(sql,new BeanListHandler<Order>(Order.class),uid,startTime,endTime);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	//…Í«ÎÕÀªı
	@Override
	public void updateOrderBuId(String id) {
		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "update orders set status=2 where id = ?";
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("…Í«ÎÕÀªı∑Ω∑® ß∞‹:"+e);
		}
		
	}

	@Override
	public List<Order> findOrderListByUid(int uid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT id ,totalprice,STATUS,createtime FROM orders where uid = ? order by createtime desc";
		try {
			return  queryRunner.query(sql,new BeanListHandler<Order>(Order.class),uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
