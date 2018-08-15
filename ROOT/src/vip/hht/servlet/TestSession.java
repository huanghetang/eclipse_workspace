package vip.hht.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		showLastLoginTime(request,response);
		loginWithYZM(request,response);
	}
	
	/**
	 * 每次登陆显示上次的登陆时间
	 */
	private void showLastLoginTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String localString = new Date().toLocaleString();
		System.out.println(localString);
		Cookie cookie = new Cookie("time", localString);
		response.addCookie(cookie);
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie2 : cookies) {
				if(cookie2.getName().equals("time")){
					response.getWriter().write("last login time:"+cookie2.getValue());
				}
			}
		}
	}
	
	/**
	 * 登陆案列,带上验证码,可以防止程序暴力破解密码
	 * 
	 */
	private void loginWithYZM(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//获取页面验证码和session中的验证码做比较
		String code = request.getParameter("code");
		String realCode = (String) request.getSession().getAttribute("code_session");
		if(realCode.equalsIgnoreCase(code)){//验证码正确
			System.out.println("登陆成功");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		}else{
			System.out.println("验证码错误");
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
		
	}

/**
 * 不交互session默认有效期30分钟或者浏览器关闭之前,session是基于cookie的技术,浏览器第一次请求系统,系统会创建一个session,session中可以保存数据,
 * 然后把当前session的id保存到浏览器的cookie中,但是当前cookie的有效期是浏览器关闭之前.可以自己延长这个session的有效期,手动创建一个cookie,key是
 * JSESSIONID,value是session.getId()覆盖系统自己创建的cookie.然后再给这个session设置有效期就可以了
 */
/*	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前的session
		HttpSession session = request.getSession();
		System.out.println(session);
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
//		cookie.setMaxAge(60*60*7);
		session.invalidate();
		response.addCookie(cookie);
		System.out.println(session);
		HttpSession session2 = request.getSession();
		System.out.println(session2+"========"+session2.getId());
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
