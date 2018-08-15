package vip.hht.service;

import vip.hht.beans.User;

public interface UserService {
	
	public User getUserByEmail(String email);

	//注册用户返回这个用户对象
	public User register(User user);

	public User getUserByPhone(String code);

}
