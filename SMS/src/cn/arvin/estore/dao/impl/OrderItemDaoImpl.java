package cn.arvin.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.arvin.estore.dao.OrderItemDao;
import cn.arvin.estore.domain.OrderItem;
import cn.arvin.estore.utils.JDBCUtils;

public class OrderItemDaoImpl implements OrderItemDao {
	
	private QueryRunner run = new QueryRunner();
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	@Override
	public void save(OrderItem item) {
		try {
			String sql = "insert into orderitems values(?,?,?)";
			run.update(JDBCUtils.getCurrentConnection(), sql,item.getOid(),item.getPid(),item.getBuynum());
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("创建中间表失败");
		}
	}

	@Override
	public List<OrderItem> findOrderByOid(String oid) {
		String sql = "select o.*,p.* from orderitems as o,product as p where o.pid = p.pid and o.oid = ?";
		try {
			return qr.query(sql, new BeanListHandler<OrderItem>(OrderItem.class), oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单的关联信息失败");
		}
	}

}
