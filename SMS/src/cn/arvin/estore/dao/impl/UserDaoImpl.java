package cn.arvin.estore.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.arvin.estore.dao.UserDao;
import cn.arvin.estore.domain.User;
import cn.arvin.estore.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	@Override
	public User findUser(String username) {
		String sql = "select * from users where email = ?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询失败");
		}
	}

	@Override
	public void registerUser(User user) {
		String sql = "insert into users values(null,?,?,?,?)";
		try {
			qr.update(sql, user.getNickname(),user.getEmail(),user.getTelephone(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册失败");
		}
	}

	@Override
	public User login(String telephone) {
		String sql = "select * from users where telephone = ?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), telephone);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登录失败");
		}
	}

}
