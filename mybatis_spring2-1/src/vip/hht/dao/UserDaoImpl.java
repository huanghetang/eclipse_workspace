package vip.hht.dao;

import vip.hht.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public void addUser(User u) {
		this.getSqlSession().update("mapper.addUser", u);
	}

}
