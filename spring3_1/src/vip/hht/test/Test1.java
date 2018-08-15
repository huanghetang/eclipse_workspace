package vip.hht.test;

import java.beans.PropertyVetoException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import vip.hht.beans.User;
import vip.hht.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test1 {
	
	@Resource(name="userDaoImpl")
	private UserDao dao;

	@Test
	public void test1() throws PropertyVetoException{
		
		//c3p0连接池
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring3_1");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		//spring提供的jdbc接口
		JdbcTemplate jt = new JdbcTemplate();
		jt.setDataSource(dataSource);
		String sql  = "insert into user values(6,'景甜')";
		int update = jt.update(sql);
		System.out.println(update);
	}
	
	@Test
	public void test2() throws PropertyVetoException{
		User u = new User();
		u.setName("古力娜扎2");
		dao.add(u);
	}
	
	@Test
	public void test22() throws PropertyVetoException{
		dao.deleteById(12);
	}
	@Test
	public void test3(){
		User u = new User();
		u = dao.findById(8);
		System.out.println(u);
	}
	@Test
	public void test4(){
		int i = dao.findUserCount();
		System.out.println(i);
	}
	
	@Test
	public void test5(){
		List<User> userList ;
		userList = dao.findUserList();
		System.out.println(userList);
	}
	
	@Test
	public void test6(){
		User u = new User();
		u.setId(6);
		u.setName("迪丽热巴");
		dao.update(u);
	}
	

}
