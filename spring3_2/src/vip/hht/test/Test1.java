package vip.hht.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.dao.UserDao;
import vip.hht.service.UserService;

//配置测试环境
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")//读取上下文配置 得到ApplicationContext对象
public class Test1 {
	@Resource(name="userService")//这个文件必须使用注解注入,不能使用<bean>配置
	private UserService us;
	@Resource(name="userDaoImpl")
	private UserDao dao;
	
	public void setUs(UserService us) {
		this.us = us;
	}
	

	public UserService getUs() {
		return us;
	}


	public UserDao getDao() {
		return dao;
	}


	public void setDao(UserDao dao) {
		this.dao = dao;
	}


	@Test
	public void testTransfer(){
		System.out.println(dao);
		System.out.println(us);
		us.transfer(1, 2, 100d);
	}

}
