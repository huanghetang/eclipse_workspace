package vip.hht.service;

import vip.hht.LoginDao.LoginDao;
import vip.hht.LoginDao.User;

public class LoginService {
	
	public User findUser(String uid,String upassword){
		LoginDao loginDao = new LoginDao();
		 User user = loginDao.findUser(uid, upassword);
		 return user;
	}
}
