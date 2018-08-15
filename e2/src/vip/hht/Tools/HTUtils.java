package vip.hht.Tools;

import java.util.UUID;

/**
 * 一般工具类
 * @author zhoumo
 *
 */
public class HTUtils {

	/**
	 * 获取一般的UUID
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
