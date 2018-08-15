package vip.hht.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MD5Servlet extends HttpServlet {
	/**
	 * 前台传入一个数,返回这个数的MD5加密后的密文
	 * 加密后的密文要和mysql一致
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("mimi");
		
		System.out.println(source);
		try {
			//获取MD5加密实例
			MessageDigest md = MessageDigest.getInstance("MD5");
			//加密,返回加密后的字节数据,包括负数(mysql select md5('abc');返回的值没有符数)
			byte[] digest = md.digest(source.getBytes());
			//
			StringBuilder retVal = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				byte b = digest[i];
				int bb = b & 255;//获取绝对值 (进行  与运算,去掉负数符号位)
				//获取对应的16进制数据
				String hexString="";
				if(bb>=0 && bb<16){//如果这个16进制数只有一位,需要在前面加一个0
					hexString ="0"+Integer.toHexString(bb);
					
				}else{
					 hexString = Integer.toHexString(bb);
				}
				retVal.append(hexString);
			}
			request.setAttribute("retVal", retVal.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			request.setAttribute("retVal", "加密出错");
		}
		request.getRequestDispatcher("/jsp/md5.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//900150983cd24fb0d6963f7d28e17f72
	//900150983cd24fb0d6963f7d28e17f72
	public static void main(String[] args) {
		String source ="王";
		try {
			//获取MD5加密实例
			MessageDigest md = MessageDigest.getInstance("MD5");
			//加密,返回加密后的字节数据,包括负数(mysql select md5('abc');返回的值没有符数)
			byte[] digest = md.digest(source.getBytes());
			//
			StringBuilder retVal = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				byte b = digest[i];
				int bb = b & 255;//获取绝对值 (进行  与运算,去掉负数符号位)
				//获取对应的16进制数据
				String hexString="";
				if(bb>=0 && bb<16){//如果这个16进制数只有一位,需要在前面加一个0
					hexString ="0"+Integer.toHexString(bb);
					
				}else{
					 hexString = Integer.toHexString(bb);
				}
				retVal.append(hexString);
			}
			System.out.println(retVal.toString());
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
