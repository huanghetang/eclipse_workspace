package vip.hht.mapper;

import vip.hht.beans.QueryVo;
import vip.hht.beans.User;

public interface UserMapper {

	/**
	 * 根据用户名和密码查询用户
	 */
	User findUserWhenLogin(QueryVo vo);
	
}
