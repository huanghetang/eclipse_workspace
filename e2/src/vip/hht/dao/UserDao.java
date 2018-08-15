package vip.hht.dao;

import java.sql.Connection;

import vip.hht.beans.User;

public interface UserDao {
	public User getUserByEmail(String email);

	public void addUser(User user,Connection con);
	//返回最后插入的自增长主键
	public int getUserIDAfterInsert(Connection con) ;

	public User findUserByPhone(String code);

}
