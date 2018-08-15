package vip.hht.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)//Junit��Spring������ ����Spring test�� ʹ��Spring�������Ϊ���Դ�������
@ContextConfiguration("classpath:vip/hht/aop/applicationContext.xml")//����ע�� Ϊ�����ȥ���������ļ�
public class Test2 {

	@Resource(name="userService")
	private UserService userService;

	@Test
	public void  test2(){
		userService.add();
	}
	
}
