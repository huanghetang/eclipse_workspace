package vip.hht.Tools;

import java.util.ResourceBundle;

import vip.hht.serviceImpl.UserServiceImpl;

/**
 * ������ȡ���ֽӿ�ʵ����
 */
public class ImplFactory {
	
	
	/**
	 * ��ȡ�����ļ����������ö���ʵ��
	 * @param T
	 * @return
	 */
	public static <T> T getImpl(Class<T> t){
		//��ȡ����
		String classname = t.getSimpleName();
		//����Ȩ�޶���
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
