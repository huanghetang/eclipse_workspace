package vip.hht.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.OrderMapper;
import mapper.QueryVoMapper;
import mapper.UserMapper;
import pojo.Order;
import pojo.QueryVo;
import pojo.User;
import org.junit.*;
public class TestService {
	private static SqlSession sqlSession;
	
	static{
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream("sqlMapConfig.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		 sqlSession = sqlSessionFactory.openSession();
	}
	
	@Test
	public void Test() throws IOException{
		
		QueryVoMapper mapper = sqlSession.getMapper(QueryVoMapper.class);
		QueryVo queryVo = new QueryVo();
		User user = new User();
		user.setUsername("大美女景甜");
		queryVo.setUser(user);
		List<User> userList = mapper.findUserByQueryVo(queryVo);
		for (User user2 : userList) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	/**
	 * 需求:查询用户表数据条数
	 * @throws IOException
	 */
	@Test
	public void findUserCount() throws IOException{
		
		QueryVoMapper mapper = sqlSession.getMapper(QueryVoMapper.class);
		Integer i = mapper.findUserCount();
		System.out.println(i);
		sqlSession.close();
	}
	
	/**
	 * 查询订单表order的所有数据
	 * @throws IOException
	 */
	@Test
	public void findOrders() throws IOException{
		
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orderList = mapper.findOrders();
		for (Order order : orderList) {
			System.out.println(order);
		}
		sqlSession.close();
	}
	
	/**
	 * 根据性别和名字查询用户
	 * @throws IOException
	 */
	@Test
	public void findUserBySexAndName() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setSex("1");
		user.setUsername("景甜");
		List<User> userlist = mapper.findUserBySexAndName(user);
		for (User user2 : userlist) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	
	/**
	 * 根据多个id查询用户信息
	 * @throws IOException
	 */
	@Test
	public void findUsersByIds() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
//		List<Integer> ids = new ArrayList<Integer>();
//		ids.add(1);
//		ids.add(30);
//		ids.add(41);
//		queryVo.setIds(ids);
		Integer[] idArrays = {1,41};
		queryVo.setIdArrays(idArrays);
		List<User> userlist = mapper.findUsersByIds(queryVo);
		for (User user2 : userlist) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	
	@Test
	public void findUsersByIdArrayList() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		List<Integer[]> idArrayList = new ArrayList<Integer[]>();
		Integer[] idArray1 = {1,41};
		Integer[] idArray2 = {10,16};
		Integer[] idArray3 = {22,30,33};
		idArrayList.add(idArray1);
		idArrayList.add(idArray3);
		idArrayList.add(idArray2);
		
		queryVo.setIdArrayList(idArrayList);
		List<User> userlist = mapper.findUsersByIdArrayList(queryVo);
		for (User user2 : userlist) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	
	@Test
	public void findUsersByArray() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		int[] idArrays = {1,41};
		List<User> userlist = mapper.findUsersByArray(idArrays);
		for (User user2 : userlist) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	
	@Test
	public void findUserByList() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List list = new ArrayList();
		list.add(1);
		list.add(41);
		list.add(10);
		List<User> userlist = mapper.findUsersByList(list);
		for (User user2 : userlist) {
			System.out.println(user2);
		}
		sqlSession.close();
	}
	
	@Test
	public void findOrder() throws IOException{
		OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
		List<Order> orderList = mapper.findOrder();
		for (Order order : orderList) {
			System.out.println(order);
		}
		sqlSession.close();
	}
	
	/**
	 * findUserAndOrder 一对多查询
	 */
	
	@Test
	public void findUserAndOrder() throws IOException{
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		List<User> userList = mapper.findUserAndOrder();
		for (User user : userList) {
			System.out.println(user);
		}
		sqlSession.close();
	}
}
