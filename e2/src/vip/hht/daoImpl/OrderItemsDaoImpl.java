package vip.hht.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.OrderItem;
import vip.hht.dao.OrderItemsDao;

public class OrderItemsDaoImpl implements OrderItemsDao {

	@Override
	public void add(Connection con,OrderItem item) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "INSERT INTO orderitems(oid,pid,buynum) VALUES(?,?,?)";
		try {
			queryRunner.update(con,sql,item.getOid(),item.getPid(),item.getBuynum());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public void setIsComment(String oid, String pid) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update orderitems set isComment=1 where oid=? and pid=?";
		try {
			 runner.update(sql,oid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败");
		}
		
	}

}
