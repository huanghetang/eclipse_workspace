package vip.hht.test;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vip.hht.pojo.User;

public class Test1 {

	/**
	 *测试bean的构造方法 
	 */
	@Test
	public void getBean1(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		User user = (User)context.getBean("user");
		System.out.println(user);
//		User user1 = context.getBean(User.class);
//		System.out.println(user1);
//		System.out.println("user==user1:"+(user==user1));
		context.close();
		context.destroy();
		
	}
	
	/**
	 * 测试bean的属性注入
	 */
	
	@Test
	public void getBean2(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		User user1 = (User) context.getBean("user1");
		System.out.println(user1);
		
		
	}
	
	public static void main(String[] args) {
		String str= "abc123cde";
		String[] a = str.split("\\d");
		System.out.println(Arrays.toString(a));
	}
}
