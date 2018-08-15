package vip.hht.AnnotationAop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.aop.UserService;

@RunWith(SpringJUnit4ClassRunner.class)//Junit��Spring������ ����Spring test�� ʹ��Spring�������Ϊ���Դ�������
@ContextConfiguration("classpath:vip/hht/AnnotationAop/applicationContext.xml")//����ע�� Ϊ�����ȥ���������ļ�
public class Test2 {

	@Resource(name="userService")
	private UserService userService;

	@Test
	public void  test2(){
		userService.add();
	}
	
}
