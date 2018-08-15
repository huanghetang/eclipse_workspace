package vip.hht.daoImpl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.User;
import vip.hht.dao.UserDao;

public class UserDaoImpl implements UserDao {
	
	public User getUserByEmail(String email){
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from users where email = ?";
			User u = queryRunner.query(sql, new BeanHandler<User>(User.class), email);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*	@Override
	public User addUser(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into users(id,email,nickname,password,telephone) values(null,?,?,?,?)";
		User u = null;
		try {
			u = queryRunner.insert(sql, new BeanHandler<User>(User.class), user.getEmail(),
						user.getNickname(),user.getPassword(),user.getTelephone());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}*/
	
	@Override
	public void addUser(User user,Connection con) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into users(id,email,nickname,password,telephone) values(null,?,?,?,?)";
		try {
			//执行插入
			queryRunner.update(con,sql,user.getEmail(),
						user.getNickname(),user.getPassword(),user.getTelephone());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@Override
	public int getUserIDAfterInsert(Connection con) {
		QueryRunner queryRunner = new QueryRunner();
		String sql4id = "select last_insert_id() as id";
		try {
			//查询这条插入的数据主键(自增长的id)
			BigInteger id = queryRunner.query(con,sql4id,new ScalarHandler<BigInteger>());
			return id.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	@Override
	public User findUserByPhone(String code) {
		try {
			QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
			String sql = "select * from users where telephone = ?";
			User u = queryRunner.query(sql, new BeanHandler<User>(User.class), code);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return null;
	}
	

	

}
