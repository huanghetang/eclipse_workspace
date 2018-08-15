package vip.hht.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public static String getPWD(String password) {
		
		try {
			//获取加密的对象
			MessageDigest instance = MessageDigest.getInstance("md5");
			//加密完成
			byte[] bs = instance.digest(password.getBytes());
			//向mysql方向优化
			/**
			 * 1 将所有的数据转换成正数 
			 *  byte b  1001 1101
			 *  int  b  0000 0000 0000 0000 0000 0000 1001 1101
			 *& int 255 0000 0000 0000 0000 0000 0000 1111 1111
			 *----------------------------------------------------------
			 * 			0000 0000 0000 0000 0000 0000 1001 1101
			 * 
			 * 2 将所有的数据转换成16进制的格式
			 * 
			 * 
			 * */
			String str = "";
			for (byte b : bs) {
				//位运算
				int temp = b & 255;
				//数据不足16，补0
				if(temp >=0 && temp < 16) {
					str = str + "0" +Integer.toHexString(temp);
				}else {
					str = str + Integer.toHexString(temp);
				}
			}
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] args) {
		String pwd = getPWD("王剑");
		System.out.println(pwd);
		//90 150983cd24fb0d6963f7d28e17f72
		//900150983cd24fb0d6963f7d28e17f72
		//900150983cd24fb0d6963f7d28e17f72
	}

}
