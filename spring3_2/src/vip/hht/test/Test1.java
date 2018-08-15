package vip.hht.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.dao.UserDao;
import vip.hht.service.UserService;

//���ò��Ի���
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")//��ȡ���������� �õ�ApplicationContext����
public class Test1 {
	@Resource(name="userService")//����ļ�����ʹ��ע��ע��,����ʹ��<bean>����
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
