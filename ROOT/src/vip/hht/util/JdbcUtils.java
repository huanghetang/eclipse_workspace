package vip.hht.util;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * JdbcUtils
 * @author zhoumo
 *
 */
public class JdbcUtils {
	private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<Connection>();//线程局部变量
	private static final DataSource ds = new ComboPooledDataSource();
	/**
	 * 获取线程范围内的数据库连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getCurrentThreadConnection() throws SQLException{
		Connection conn = threadLocalConn.get();
		if(conn==null){
				conn = ds.getConnection();
				threadLocalConn.set(conn);
		}
		return conn;
		
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	

}

