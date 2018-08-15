package vip.hht.test;

import org.apache.catalina.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	
	
	@Test
	public void test1(){
		/**
		 * BeanFactoryΪspring��ܶ���ӿڸ�ʵ�ֹ��ܽ�Ϊ��һ,ÿ����Ҫʹ�õ����ʵ���Ż�������
		 * ApplicationContextΪ�����ӿ�,ÿ�������ͻ���������е��������ֵ�������
		 * �������� ClassPathXmlApplicationContext �� FileSystemXmlApplicationContext
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
