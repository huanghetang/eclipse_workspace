package dao;

import java.io.IOException;

import pojo.User;

public interface UserDao {
	
	User findUserById() throws IOException;

}
