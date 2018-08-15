package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pojo.User;

public class UserDaoImpl implements UserDao {
	private SqlSessionFactory sqlSessionFactory;
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory = sqlSessionFactory;
	}
	public UserDaoImpl(){}
	
	/**
	 * 原始的dao开发创建工厂,创建会话,调用方法
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(is);
		System.out.println(new UserDaoImpl(sqlSessionFactory).findUserByName());;
	}

	@Override
	public User findUserById() throws IOException {
		
		SqlSession sqlsession = sqlSessionFactory.openSession();
		User user= sqlsession.selectOne("test.findUserById",10);
		sqlsession.close();
		return  user;

	}

	public List<User> findUserByName() throws IOException {
		SqlSession sqlsession = sqlSessionFactory.openSession();
		List<User>  list = sqlsession.selectList("test.findUserByName","大美女景甜");
		return list;

	}
}
