package vip.hht.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vip.hht.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)//Junit和Spring的整合 导入Spring test包 使用Spring的这个类为测试创建环境
@ContextConfiguration("classpath:applicationContext.xml")//辅助注解 为这个类去加载配置文件
public class Test2 {
	@Resource(name="user")
	private User u;
	
	@Test
	public void test1(){
		System.out.println(u);
		
	}
}
