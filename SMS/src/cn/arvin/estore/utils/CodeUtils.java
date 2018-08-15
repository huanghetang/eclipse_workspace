package cn.arvin.estore.utils;

import java.util.Random;


public class CodeUtils {
	
	public static String generateCheckCode(){
		
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			int num = r.nextInt(10);
			sb.append(num);
		}
		return sb.toString();
	}
}
