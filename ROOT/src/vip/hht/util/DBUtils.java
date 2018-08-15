package vip.hht.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	private static ComboPooledDataSource pool;
	static{
		 pool = new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource(){
		return pool;
	}
	public static Connection getConnection() throws SQLException{
		return pool.getConnection();
	}

}
