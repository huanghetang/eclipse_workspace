package vip.hht.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ?method 解决方法调用问题
 * 参考了springmvc解决方案,当返回"forward:/*"关键字时转发,返回"redirect:/*"时重定向(默认路径是classpath)
 * 默认返回""为json处理方式
 * @author zhoumo
 *
 */
public class BaseServlet extends GenericServlet {


	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;
		String methodName = request.getParameter("method");
		if(methodName!=null){
			Class<? extends BaseServlet> clazz = this.getClass();
			try {
				Method targetMethod = clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				if(targetMethod!=null){
					Object invoke = targetMethod.invoke(this, request,response);
					if(invoke==null){
						return;
					}
					if(invoke instanceof String){//返回值 forward:,redirect:,json:
						String keyword = (String)invoke;
						if(keyword.startsWith("forward:")){//转发
							request.getRequestDispatcher(keyword.substring("forward:".length()+1)).forward(request, response);
							return;
						}
						if(keyword.startsWith("redirect:")){//重定向
							response.sendRedirect(request.getContextPath()+"/"+keyword.substring("redirect:".length()+1));
							return;
						}
						//返回值为String时默认json
						response.setContentType("text/json;charset=utf-8");
						response.getWriter().write(keyword);
					}
					
				}else{
					throw new RuntimeException("调用的这个方法没有定义!");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

}

