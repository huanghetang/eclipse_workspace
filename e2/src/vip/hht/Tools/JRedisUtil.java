package vip.hht.Tools;

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
	private static JedisPool jedisPool = null;
	static{
		InputStream is = JRedisUtil.class.getClassLoader().getResourceAsStream("env.properties");
//		InputStream is = First.class.getResourceAsStream("/env.properties");//�˷���'/'�������src��·��,���Ӵ�����԰������·��,���ջ���ͨ����classloader����
		properties = new Properties();
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ��ȡJedisPool���ӳ�
	 * @return
	 */
	public static JedisPool getJedisPool() {
		if(jedisPool!=null){
			return jedisPool;
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(Integer.valueOf(properties.getProperty("jedis.maxTotle")));
		poolConfig.setMinIdle(Integer.valueOf(properties.getProperty("jedis.minIdle")));
		poolConfig.setMaxIdle(Integer.valueOf(properties.getProperty("jedis.maxIdle")));
		JedisPool jedisPool = new JedisPool(poolConfig, properties.getProperty("jedis.url"), Integer.valueOf(properties.getProperty("jedis.port")));
		return jedisPool;
	}
	
	
	/**
	 * ��Redis�д�String���͵�ֵ
	 * @param key
	 * @param value
	 * @param second
	 */
	public static void setString2Redis(String key,String value,int sceond){
		JedisPool jedisPool = getJedisPool();
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		jedis.expire(key, sceond);//���ù���ʱ��
		jedis.close();
	}
	
	/**
	 * ��Redis��ȡkey
	 * @param key
	 * @param value
	 */
	public static String getString4Redis(String key){
		JedisPool jedisPool = getJedisPool();
		Jedis jedis = jedisPool.getResource();
		//�жϳ�ʱ
		 Long ttl = jedis.ttl(key);
		 if(ttl.intValue()==-2){//key��ʱ
			 jedis.close();
			 return "-2";
		 }
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	public static void main(String[] args) {
		JedisPool jedisPool = getJedisPool();
		Jedis jedis = jedisPool.getResource();
		String s = jedis.get("username");
		jedisPool.close();
		System.out.println(s);
		
	}
}
