package vip.hht.test;

import org.apache.catalina.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	
	@Test
	public void test1(){
		/**
		 * BeanFactory为spring框架顶层接口该实现功能较为单一,每次需要使用到类的实例才会区创建
		 * ApplicationContext为后来接口,每次启动就会加载配置中的所有是咧到容器中
		 * 两个子类 ClassPathXmlApplicationContext 和 FileSystemXmlApplicationContext
		 */
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Object user = ac.getBean("user");
		Object user2 = ac.getBean("user");
		System.out.println(user);
		System.out.println(user==user2);
		ac.close();
		ac.destroy();
		
	}
	

}
