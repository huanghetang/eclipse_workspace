package vip.hht.service;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import vip.hht.dao.UserDao;
import vip.hht.mapper.UserMapper;
import vip.hht.pojo.User;

public class Test {
	
	private UserDao dao;
	




	public UserDao getDao() {
		return dao;
	}





	public void setDao(UserDao dao) {
		this.dao = dao;
	}




	/**
	 * 原生dao开发(继承mybatis_spring整合包提供的sqlSessionFactory父类,注入)
	 */
	@org.junit.Test
	public void test1(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserDao dao =(UserDao)ac.getBean("userDao");
		Test test =(Test)ac.getBean("test");
		System.out.println(test);
		UserDao dao1= test.getDao();
		System.out.println(dao1);
		User u = new User();
		u.setName("安吉拉大宝贝1");
		dao1.addUser(u);
		
	}
	
	/**
	 * 使用mapper接口开发,使用mybatis_spring整合包提供的org.mybatis.spring.mapper.MapperFactoryBean 提供数据库会话工厂和mapper接口直接得到mapper的代理实现类
	 */
	@org.junit.Test
	public void test2(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserMapper mapper =ac.getBean(UserMapper.class);
		UserMapper mapper = (UserMapper)ac.getBean("userMapper");
		User user = mapper.findUserById(9);
		System.out.println(user);
		
	}
	
	
	/**
	 * 使用mapper接口扫描的方式开发,不需要配置每个mapper接口的实现类配置
	 */
	@org.junit.Test
	public void test3(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserMapper mapper =ac.getBean(UserMapper.class);
		UserMapper mapper = ac.getBean(UserMapper.class);
		User user = mapper.findUserById(10);
		System.out.println(user);
		
	}
}
