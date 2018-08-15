package cn.arvin.estore.service;

import cn.arvin.estore.domain.AdminUser;

public interface AdminUserService {

	/**
	 * 管理员登录
	 * @param adminUser
	 * @return
	 */
	AdminUser loginAdmin(AdminUser adminUser);

}
