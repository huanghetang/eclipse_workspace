package vip.hht.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Java for Redis Tool
 * @author zhoumo
 *
 */
public class JRedisUtil {
	private static Properties properties;
	static{
		InputStream is = First.class.getClassLoader().getResourceAsStream("env.properties");
//		InputStream is = First.class.getResourceAsStream("/env.properties");//此方法'/'代表相对src的路径,不加代表相对包下面的路径,最终还是通过该classloader来找
		properties = new Properties();
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取JedisPool连接池
	 * @return
	 */
	public static JedisPool getJedisPool() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.valueOf(properties.getProperty("jedis.maxTotle")));
		poolConfig.setMinIdle(Integer.valueOf(properties.getProperty("jedis.minIdle")));
		poolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("jedis.maxIdle")));
		JedisPool jedisPool = new JedisPool(poolConfig, properties.getProperty("jedis.url"), Integer.valueOf(properties.getProperty("jedis.port")));
		return jedisPool;
	}
	public static void main(String[] args) {
		JedisPool jedisPool = getJedisPool();
		Jedis jedis = jedisPool.getResource();
		String s = jedis.get("username");
		jedisPool.close();
		System.out.println(s);
		
	}
}
