package vip.hht.Tools;

import java.util.UUID;

/**
 * һ�㹤����
 * @author zhoumo
 *
 */
public class HTUtils {

	/**
	 * ��ȡһ���UUID
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
