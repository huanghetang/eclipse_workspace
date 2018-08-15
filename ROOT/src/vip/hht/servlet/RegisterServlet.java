package vip.hht.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import vip.hht.LoginDao.User;
import vip.hht.service.RegisterService;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(name);
//		String n = new String(name.getBytes("iso-8859-1"),"utf-8");
//		System.out.println(n);
		String remoteAddr = request.getRemoteAddr();
		System.out.println(remoteAddr);
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String key = headerNames.nextElement();
			System.out.println(key+":"+request.getHeader(key));
		}
		String contextPath = getServletContext().getContextPath();
		System.out.println(contextPath);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("uri:"+requestURI);
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("URL:"+requestURL);
		Map<String,String> map = new HashMap<String,String>();
		request.setCharacterEncoding("utf-8");
		Enumeration<String> parameterNames = request.getParameterNames();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		while(parameterNames.hasMoreElements()){
			String key = parameterNames.nextElement();
			map.put(key, request.getParameter(key));
		}
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			RegisterService rs = new RegisterService();
			boolean b  = rs.register(user);
			if(b){
				writer.write("³É¹¦");
			}else{
				writer.write("×¢²áÊ§°Ü");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
