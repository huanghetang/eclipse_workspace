package cn.arvin.estore.dao;

import cn.arvin.estore.domain.User;

public interface UserDao {

	/**
	 * 查询用户
	 * @param username 
	 * @return
	 */
	User findUser(String username);

	/**
	 * 注册用户信息
	 * @param user
	 */
	void registerUser(User user);

	/**
	 * 手机登录
	 * @param telephone
	 * @return
	 */
	User login(String telephone);

}
