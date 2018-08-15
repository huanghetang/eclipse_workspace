package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC工具包
 * @author zhoumo
 * @version 1.0
 * @createTime 2018/05/18
 */
public class JDBCUtils {
	//创建线程单例集合
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	//初始化一个数据库连接池
	private static DataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 获取当前线程绑定的数据库连接
	 * @return
	 */
	public static Connection getCurrentConnection(){
		Connection con = threadLocal.get();
		if(con == null){
			con = getConnection();
			threadLocal.set(con);
			return con;
		}
		return con;
	}

	/**
	 * 创建一个新的数据库连接并返回
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return connection;
	}
	
	/**
	 * 给当请线程绑定的Connection开始事务
	 */
	public static void startTransaction(){
		Connection con = getCurrentConnection();
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给当前线程绑定的Connection提交事务
	 */
	public static void commitTransaction(){
		Connection con = getCurrentConnection();
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给当前线程绑定的Connection回滚事务
	 */
	public static void rollbackTransaction(){
		Connection con = getCurrentConnection();
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 释放当前线程绑定的connection并关闭资源
	 */
	
	public static void closeConnection(){
		Connection con = getCurrentConnection();
		try {
			con.close();
			threadLocal.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}



