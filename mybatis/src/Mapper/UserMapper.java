package Mapper;

import java.util.List;

import pojo.User;

public interface UserMapper {

		User findUserById(Integer id);
		List<User> findUserByName(String name);
		void updateUser(User user);
		void deleteUserById(Integer id);
		Integer addUser(User user);
}
