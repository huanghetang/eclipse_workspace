package vip.hht.LoginDao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import vip.hht.util.JdbcUtils;

public class RegisterDao {
	/**
	 * 查询用户是否存在
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean findUser(User user) throws SQLException{
		String sql = "select uname,upassword from user where uname =? ";
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		User u = qr.query(sql, new BeanHandler<User>(User.class),user.getUname());
		System.out.println(sql);
		if(u!=null){
			return true;
		}
		return false;
	}
	
	/**
	 * 注册用户
	 * @throws SQLException 
	 */
	public void register(User user) throws SQLException{
		String sql = "insert into user(uname,upassword) values(?,?)";
		
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		
		 qr.update(sql, user.getUname(),user.getUpassword());
		 System.out.println(sql);
		
	}

}
