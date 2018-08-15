package vip.hht.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vip.hht.pojo.User;

public class Test1 {
	
	@Test
	public void test1(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		User u = (User)ac.getBean("user1");
//		User u2 = (User)ac.getBean("user2");
		User u3 = (User)ac.getBean("user3");
//		System.out.println(u2);
		System.out.println(u3);
		User u4 = ac.getBean(User.class);
		System.out.println("-----------------");
	}

}
