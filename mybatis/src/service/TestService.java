package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import Mapper.UserMapper;
import dao.UserDaoImpl;
import pojo.User;

public class TestService {
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void before() throws IOException{
		InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
		 sqlSessionFactory =  new SqlSessionFactoryBuilder().build(is);
	}
	@Test
	public void test() throws IOException{
		
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper= session.getMapper(UserMapper.class);
		User user = userMapper.findUserById(30);
		System.out.println(user);
//		UserDaoImpl dao = new UserDaoImpl(sqlSessionFactory);
//		System.out.println(dao.findUserByName());;
	}
	
	@Test
	public void test1() throws IOException{
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper userMapper = openSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("景甜");
		openSession.close();
		System.out.println(list);
		
	}
	
	/**
	 * 生成返回值快捷键 alt+shift+L
	 * @throws IOException
	 */
	@Test
	public void add() throws IOException{
		
		SqlSession openSession = sqlSessionFactory.openSession();
		UserMapper mapper = openSession.getMapper(UserMapper.class);
		User user = new User("柳岩1","女",new Date(),"航头镇");
		Integer i = mapper.addUser(user);
//		openSession.commit();
		System.out.println(i);
		
	}
	@Test
	public void delete() throws IOException{
		
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper= session.getMapper(UserMapper.class);
		userMapper.deleteUserById(24);
		session.commit();
	}
	@Test
	public void update() throws IOException{
		
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper= session.getMapper(UserMapper.class);
		User user  = new User(41,"大美女柳岩","女",new Date(),"奋斗者之家");
		userMapper.updateUser(user);
		session.commit();
		session.close();
	}

}
