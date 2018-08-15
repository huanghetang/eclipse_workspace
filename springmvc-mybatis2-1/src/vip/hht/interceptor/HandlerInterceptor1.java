package vip.hht.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Springmvc中的处理器拦截器类似于Servlet中的Fileter,用于对handler方法的预处理和后处理
 * @author zhoumo
 *
 */
public class HandlerInterceptor1 implements HandlerInterceptor{

	/**
	 * 方法执行前(常用于检查用户是否登陆)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle1");
		//URL  http://localhost:8081/springmvc-mybatis1-1/itemEdit/3.action
		//URI  itemEdit/3.action
		String requestURI = request.getRequestURI();
		if(!requestURI.contains("/login")){
			Object username =  request.getSession().getAttribute("USERNAME");
			if(username==null){
				response.sendRedirect(request.getContextPath()+"/login.action");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 方法执行后
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle1");
	}
	/**
	 * 页面选然后
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterCompletion1");
	}



}
