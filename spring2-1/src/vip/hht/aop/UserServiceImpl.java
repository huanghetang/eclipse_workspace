package vip.hht.aop;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import vip.hht.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="dao")
	private UserDao dao;
	@Override
	public void add() {
		dao.add();
//		int i=1/0;
	}

}
