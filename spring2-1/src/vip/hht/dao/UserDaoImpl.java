package vip.hht.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class UserDaoImpl implements UserDao {

	@Override
	public void add() {
		System.out.println("add()");
	}

}
