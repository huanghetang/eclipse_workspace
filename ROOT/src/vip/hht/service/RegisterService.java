package vip.hht.service;

import java.sql.SQLException;

import vip.hht.LoginDao.RegisterDao;
import vip.hht.LoginDao.User;

public class RegisterService {
	private  RegisterDao dao = new RegisterDao();
	
	public boolean register(User user) throws SQLException{
		boolean b = dao.findUser(user);
		if(b){//ÒÑ´æÔÚ
			return false;
		}
		dao.register(user);
		return true;
	}

}
