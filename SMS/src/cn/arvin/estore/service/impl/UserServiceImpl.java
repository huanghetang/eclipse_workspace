package cn.arvin.estore.service.impl;

import cn.arvin.estore.dao.UserDao;
import cn.arvin.estore.dao.impl.UserDaoImpl;
import cn.arvin.estore.domain.User;
import cn.arvin.estore.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User findUser(String username) {
		return userDao.findUser(username);
	}

	@Override
	public void registerUser(User user) {
		userDao.registerUser(user);
	}

	@Override
	public User login(String telephone) {
		return userDao.login(telephone);
	}

	
}
