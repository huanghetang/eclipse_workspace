package vip.hht.estore.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.estore.beans.Order;
import vip.hht.estore.dao.OrderDao;
import vip.hht.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<Order> getListOrder(int startIndex, int size) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select id,uid,totalprice,address,acceptperson,telephone,status,createtime from orders limit ?,?";
		
		try {
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), startIndex,size);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询分页订单失败");
		}
	}

	@Override
	public int getTotalCounts() {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select count(1) from orders";
			Long total = queryRunner.query(sql, new ScalarHandler<Long>());
			return total.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询所有订单数据失败");
		}
	}

	@Override
	public void updateOrderStatus(String id) {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "update orders set status=3 where id=?";
			queryRunner.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("修改订单状态失败");
		}
		
	}

	@Override
	public List<Order> listPendingOrder(int startIndex, int size,int status) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select id,uid,totalprice,address,acceptperson,telephone,status,createtime from orders where status=? limit ?,?";
		
		try {
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), status,startIndex,size);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询待处理分页订单失败");
		}
	}

}
