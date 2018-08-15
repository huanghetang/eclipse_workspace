package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通用post处理乱码的方式
		request.setCharacterEncoding("utf-8");
		//获取客户端提交不同业务请求 方法参数 由该参数 映射到子类真正执行的业务方法
		String methodName = request.getParameter("methodName");
		if(methodName != null && methodName.trim().length() != 0){
			//执行业务方法 this 真正执行 servlet对象
			try {
				//通过反射获取需要调用的方法
				Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				//调用方法执行
				method.invoke(this, request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
