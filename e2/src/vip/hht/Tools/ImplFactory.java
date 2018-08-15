package vip.hht.Tools;

import java.util.ResourceBundle;

import vip.hht.serviceImpl.UserServiceImpl;

/**
 * 工厂获取各种接口实现类
 */
public class ImplFactory {
	
	
	/**
	 * 读取配置文件参数创建该对象实例
	 * @param T
	 * @return
	 */
	public static <T> T getImpl(Class<T> t){
		//获取类名
		String classname = t.getSimpleName();
		//加载权限定名
		Object instance = null;
		if(!"".equals(classname)){
			String qualityName = ResourceBundle.getBundle("impl").getString(classname);
			try {
				Class<?> cc = Class.forName(qualityName);
				instance = cc.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return (T)instance;
	}
	public static void main(String[] args) {
		UserServiceImpl impl = getImpl(UserServiceImpl.class);
		System.out.println(impl);
	}

}
