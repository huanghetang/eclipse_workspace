package vip.hht.service;

import vip.hht.beans.QueryVo;
import vip.hht.beans.User;

public interface UserService {
	
	
	/**
	 * 用户登陆
	 */
	User findUserWhenLogin(QueryVo vo);

}
