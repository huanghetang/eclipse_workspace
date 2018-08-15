package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;

import cn.arvin.estore.domain.AdminUser;
import cn.arvin.estore.service.AdminUserService;
import cn.arvin.estore.service.impl.AdminUserServiceImpl;

public class UserServlet2 extends BaseServlet {

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> map = request.getParameterMap();
		AdminUser adminUser = new AdminUser();
		try {
			BeanUtils.populate(adminUser, map);
		} catch (Exception e) {
			e.printStackTrace(); 
			throw new RuntimeException("封装错误");
		}
//		System.out.println(adminUser);
		
		AdminUserService adminUserService = new AdminUserServiceImpl();
		AdminUser loginUser = adminUserService.loginAdmin(adminUser);
		if(loginUser != null){
			//登录通过
//			
//			//添加Cookie
//			Cookie cookie = new Cookie("email", loginUser.getEmail());
//			cookie.setMaxAge(60*60*24);
//			response.addCookie(cookie);
//			
			//添加session
			request.getSession().setAttribute("loginUser", loginUser);
			response.getWriter().write("1");
			//跳转主页
//			response.sendRedirect(request.getContextPath() + "/main.html");
//			return;
		}else{
			//登录失败
//			request.setAttribute("msg", "登录用户或密码错误");
//			request.getRequestDispatcher("/index.html").forward(request, response);
//			String msg = "登录用户或密码错误";
//			String jsonString = JSON.toJSONString(msg);
//			response.setContentType("text/json;charset=utf-8");
//			response.getWriter().write(jsonString);
			response.getWriter().write("0");
			//继续登录
//			response.sendRedirect(request.getContextPath() + "/index.html");
//			return;
		}
		
	}
	
	
	
	
}