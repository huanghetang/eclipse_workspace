package vip.hht.mapper;

import vip.hht.pojo.User;

public interface UserMapper {
	void addUser(User u);
	User findUserById(Integer id);

}
