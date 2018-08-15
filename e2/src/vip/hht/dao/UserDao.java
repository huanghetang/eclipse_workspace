package vip.hht.dao;

import java.sql.Connection;

import vip.hht.beans.User;

public interface UserDao {
	public User getUserByEmail(String email);

	public void addUser(User user,Connection con);
	//���������������������
	public int getUserIDAfterInsert(Connection con) ;

	public User findUserByPhone(String code);

}
