package vip.hht.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)//Junit��Spring������ ����Spring test�� ʹ��Spring�������Ϊ���Դ�������
@ContextConfiguration("classpath:applicationContext.xml")//����ע�� Ϊ�����ȥ���������ļ�
public class Test2 {
	@Resource(name="user")
	private User u;
	
	@Test
	public void test1(){
		System.out.println(u);
		
	}
}
