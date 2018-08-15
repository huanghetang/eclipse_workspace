package vip.hht.dao;

import java.util.List;

import vip.hht.beans.User;

public interface UserDao {

	void add(User u);
	
	void deleteById(int id);
	
	void update(User u);
	
	User findById(int id);
	
	int findUserCount();
	
	List<User> findUserList();
}
