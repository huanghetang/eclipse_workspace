package vip.hht.service;

import vip.hht.beans.User;

public interface UserService {
	
	public User getUserByEmail(String email);

	//ע���û���������û�����
	public User register(User user);

	public User getUserByPhone(String code);

}
