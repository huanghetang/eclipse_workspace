package cn.arvin.estore.dao;

import cn.arvin.estore.domain.AdminUser;

public interface AdminUserDao {

	/**
	 * 管理员登录
	 * @param adminUser 
	 * @return
	 */
	AdminUser loginAdmin(AdminUser adminUser);

}
