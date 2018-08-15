package vip.hht.service;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import vip.hht.ssm.mapper.UserMapper;
import vip.hht.ssm.po.User;
import vip.hht.ssm.po.UserExample;
import vip.hht.ssm.po.UserExample.Criteria;


public class Test {

	
	
	/**
	 * 使用mapper接口扫描的方式开发,不需要配置每个mapper接口的实现类配置
	 */
	@org.junit.Test
	public void test3(){
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper mapper = ac.getBean(UserMapper.class);
//		User user = mapper.selectByPrimaryKey(1);
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameLike("%景甜%");
		List<User> userList = mapper.selectByExample(example);
		for (User user : userList) {
			System.out.println(user.getId());
		}
		
		
	}
}
