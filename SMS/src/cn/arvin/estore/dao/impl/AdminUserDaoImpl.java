package cn.arvin.estore.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.arvin.estore.dao.AdminUserDao;
import cn.arvin.estore.domain.AdminUser;
import cn.arvin.estore.utils.JDBCUtils;

public class AdminUserDaoImpl implements AdminUserDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	@Override
	public AdminUser loginAdmin(AdminUser adminUser) {
		String sql = "select * from admin_user where email = ? and password = ?";
		String email = adminUser.getEmail();
		String password = adminUser.getPassword();
		try {
			return qr.query(sql, new BeanHandler<AdminUser>(AdminUser.class), email,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登录管理员失败");
		}
	}
	
}
