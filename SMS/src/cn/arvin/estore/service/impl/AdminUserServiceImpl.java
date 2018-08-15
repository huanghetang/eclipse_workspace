package cn.arvin.estore.service.impl;

import cn.arvin.estore.dao.AdminUserDao;
import cn.arvin.estore.dao.impl.AdminUserDaoImpl;
import cn.arvin.estore.domain.AdminUser;
import cn.arvin.estore.service.AdminUserService;

public class AdminUserServiceImpl implements AdminUserService {
	
	private AdminUserDao adminUserDao = new AdminUserDaoImpl();
	
	@Override
	public AdminUser loginAdmin(AdminUser adminUser) {
		return adminUserDao.loginAdmin(adminUser);
	}

}
