package vip.hht.serviceImpl;
import java.sql.Connection;
import java.sql.SQLException;

import vip.hht.Tools.ImplFactory;
import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.User;
import vip.hht.dao.UserDao;
import vip.hht.daoImpl.UserDaoImpl;
import vip.hht.service.UserService;

public class UserServiceImpl implements UserService {
	
	/**
	 * 验证用户名
	 */
	public User getUserByEmail(String email){
		UserDao dao = ImplFactory.getImpl(UserDaoImpl.class);
		User user = dao.getUserByEmail(email);
		return user;
	}

	@Override
	public User register(User user) {
		UserDao dao = ImplFactory.getImpl(UserDaoImpl.class);
		Connection con = JDBCUtils.getConnection();
		try {
			con.setAutoCommit(false);
			//插入一条数据
			dao.addUser(user, con);
			//查询主键
			int userId = dao.getUserIDAfterInsert(con);
			//给user设置主键id
			user.setId(userId);
			con.commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public User getUserByPhone(String code) {
		UserDao dao = ImplFactory.getImpl(UserDaoImpl.class);
		User u = dao.findUserByPhone(code);
		return u;
	}

	
	public static void main(String[] args) {
		User user = new User();
		user.setEmail("1232@aa112");
		user.setNickname("123");
		user.setPassword("123");
		user.setTelephone("123");
		UserServiceImpl serviceImpl = new UserServiceImpl();
		User user2 = serviceImpl.register(user);
		System.out.println(user2.getId());
		System.out.println(user2);

	}
}
