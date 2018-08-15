package cn.arvin.estore.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> map = new ThreadLocal<Connection>();
	
	public static Connection getConnection() throws SQLException{
		return comboPooledDataSource.getConnection();
	}
	
	public static DataSource getDataSource(){
		return comboPooledDataSource;
	}
	
	public static Connection getCurrentConnection(){
	
		Connection conn = null;
		
		conn = map.get();
		if(conn == null){
			try {
				conn = comboPooledDataSource.getConnection();
				map.set(conn);
//				System.out.println("第一次获取连接："+ conn);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		System.out.println("获取map链接：" + conn);
		return conn;
	}
	
	public static void startTransaction(){
		Connection conn = getCurrentConnection();
		try {
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		Connection conn = getCurrentConnection();
		try {
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollBack(){
		Connection conn = getCurrentConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void release(){
		Connection conn = getCurrentConnection();
		try {
			conn.close();
			//连接使用完毕，移除连接
			map.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
