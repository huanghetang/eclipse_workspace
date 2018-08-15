package cn.arvin.estore.factory;

import java.util.ResourceBundle;

import cn.arvin.estore.service.CartService;

public class BeanFactory {
	
	@SuppressWarnings({"unchecked","unused","rawtypes"})
	public static <T> T getInstance( Class<T> c ){
		try {
			String interfaceName = c.getSimpleName();//获取类名
			//c.getName(); //获取类全限定名
			String  className = ResourceBundle.getBundle("factory").getString(interfaceName);
			Class clazz = Class.forName(className);
			return (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取接口实例失败" + e);
		}
		
	}
	public static void main(String[] args) {
		//调用service方法添加购物车
		CartService cartService = BeanFactory.getInstance(CartService.class);
		System.out.println(cartService);
	}
}
