package vip.hht.Tools;

import java.io.Closeable;
import java.io.IOException;
import java.util.Random;

public class Tool {

	
	/**
	 * 获取随机4位数字
	 */
	public static String get4Num(){
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<4;i++){
			sb.append( random.nextInt(10));
		}
		return sb.toString();
	}
	/**
	 * 关闭资源
	 * @param args
	 */
	public static void closeIO(Closeable ...ios){
		if(ios==null){
			return ;
		}
		for (Closeable io : ios) {
			try {
				io.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.println(get4Num());;
	}
}
