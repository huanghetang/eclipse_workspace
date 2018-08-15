package vip.hht.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.beans.QueryVo;
import vip.hht.beans.User;
import vip.hht.mapper.UserMapper;
import vip.hht.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserWhenLogin(QueryVo vo) {
		return userMapper.findUserWhenLogin(vo);
	}

}
