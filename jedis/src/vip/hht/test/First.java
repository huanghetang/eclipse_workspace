package vip.hht.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class First {
	private static Properties properties;
	static{
		InputStream is = First.class.getClassLoader().getResourceAsStream("env.properties");
//		InputStream is = First.class.getResourceAsStream("/env.properties");//�˷���'/'�������src��·��,���Ӵ�����԰������·��,���ջ���ͨ����classloader����
		properties = new Properties();
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.valueOf(properties.getProperty("jedis.maxTotle")));
		poolConfig.setMinIdle(Integer.valueOf(properties.getProperty("jedis.minIdle")));
		poolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("jedis.maxIdle")));
		JedisPool jedisPool = new JedisPool(poolConfig, properties.getProperty("jedis.url"), Integer.valueOf(properties.getProperty("jedis.port")));
		Jedis jedis = jedisPool.getResource();
		String s = jedis.get("user");
		System.out.println(s);
		jedis.close();
		jedisPool.close();
		
		
		
		
	}
	
	@Test
	public void test1(){
		 GenericObjectPoolConfig poolConfig = new JedisPoolConfig();
		 poolConfig.setMaxTotal(50);//�������������
		 poolConfig.setMaxIdle(10);//�����������
		 poolConfig.setMinIdle(5);//������С����
		JedisPool pool = new JedisPool(poolConfig, "192.168.246.131", 6379);
		Jedis jedis = pool.getResource();
		jedis.set("user", "zhoumo");
		String s = jedis.get("user");
		System.out.println(s);
		jedis.close();
		pool.close();
		
	}

	@Test
	public void test2(){
//	
		
	}

	private static void JedisAbc() {
		Jedis jedis = new Jedis("192.168.246.131", 6379);
		 jedis.set("username", "zhangsan");
		 String s = jedis.get("username");
		 System.out.println(s);
		 jedis.close();
	}

}
