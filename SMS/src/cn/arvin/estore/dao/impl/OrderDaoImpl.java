package cn.arvin.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.arvin.estore.dao.OrderDao;
import cn.arvin.estore.domain.Category;
import cn.arvin.estore.domain.Order;
import cn.arvin.estore.domain.User;
import cn.arvin.estore.utils.JDBCUtils;

public class OrderDaoImpl implements OrderDao {
	
	private QueryRunner run = new QueryRunner();//处理事务的连接池
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());//不处理事务
	
	@Override
	public void save(Order order) {
		
		try {
			String sql = "insert into orders values(?,?,?,?,?,?,?,?,null)";
			run.update(JDBCUtils.getCurrentConnection(), sql, order.getId(),
					order.getUid(),order.getTotalprice(),order.getAddress(),order.getPostcode(),
					order.getAcceptperson(),order.getTelephone(),order.getStatus());

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建订单表失败");
		}
	}

	@Override
	public void orderList(User loginUser, List<Order> ordersList) {
		String sql = "select * from orders where uid = ?";
		try {
			List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), loginUser.getId());
			ordersList.addAll(list);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户订单失败");
		}
	}

	@Override
	public Order findOrderByOid(String oid) {
		String sql = "select * from orders where id = ?";
		try {
			return qr.query(sql, new BeanHandler<Order>(Order.class), oid);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单详情失败");
		}
	}

	@Override
	public void paysucess(String oid) {
		
		String sql = "update order set status = 1 where id = ?";
		try {
			qr.update(sql, oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新订单状态失败");
		}
		
	}
	
	@Override
	public int totalCount() {
		String sql = "select count(*) from orders";
		try {
			Long num = qr.query(sql , new ScalarHandler<Long>());
			return Integer.valueOf(num + "");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("订单查询总记录失败");
		}
	}

	@Override
	public List<Order> pageQuery(int page, int rows) {
		String sql = "select * from orders limit ?,?";
		//String sql = " select id,uid,totalprice,address,status,date_format(createtime,'%Y-%m-%d %T') as createtime from orders limit ?,?";
		int startIndex = (page - 1) * rows;
		try {
			return qr.query(sql, new BeanListHandler<Order>(Order.class), startIndex,rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("订单后台分页查询失败");
		}
		
	}

	@Override
	public List<Order> listPendingOrder(int startIndex, int size, int status) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from orders where status=? limit ?,?";
		
		try {
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class), status,startIndex,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询审批分页订单失败");
		}
	}
	
	//获取审批的订单总数
	@Override
	public int getTotalCounts(int status) {
		String sql = "select count(*) from orders where status = ?";
		try {
			Long num = qr.query(sql , new ScalarHandler<Long>(),status);
			return Integer.valueOf(num + "");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单审批总记录失败");
		}
	}

	@Override
	public void pass(String id) {
		String sql = "update orders set status = 3 where id = ?";
		try {
			run.update(JDBCUtils.getCurrentConnection(), sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("审批订单失败");
		}
	}

}
