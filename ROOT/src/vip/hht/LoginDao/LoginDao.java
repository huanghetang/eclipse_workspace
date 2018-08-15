package vip.hht.LoginDao;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import vip.hht.util.DBUtils;

public class LoginDao {

	/**
	 * 根据用户名和密码查询用户
	 */
	public User findUser(String uid,String upassword){
		DataSource ds = DBUtils.getDataSource();
		QueryRunner queryRunner = new QueryRunner(ds);
		String sql = "select uid,uname from user where uid=? and upassword=?";
		try {
			User user = queryRunner.query(sql, new BeanHandler<User>(User.class), uid,upassword);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
