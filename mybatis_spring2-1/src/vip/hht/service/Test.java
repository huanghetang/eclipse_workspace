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
	 * ԭ��dao����(�̳�mybatis_spring���ϰ��ṩ��sqlSessionFactory����,ע��)
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
		u.setName("�������󱦱�1");
		dao1.addUser(u);
		
	}
	
	/**
	 * ʹ��mapper�ӿڿ���,ʹ��mybatis_spring���ϰ��ṩ��org.mybatis.spring.mapper.MapperFactoryBean �ṩ���ݿ�Ự������mapper�ӿ�ֱ�ӵõ�mapper�Ĵ���ʵ����
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
	 * ʹ��mapper�ӿ�ɨ��ķ�ʽ����,����Ҫ����ÿ��mapper�ӿڵ�ʵ��������
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
