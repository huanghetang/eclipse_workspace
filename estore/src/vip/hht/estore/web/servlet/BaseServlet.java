package vip.hht.estore.web.servlet;

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
 * ?method ���������������
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
					targetMethod.invoke(this, request,response);
				}else{
					throw new RuntimeException("���õ��������û�ж���!");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}

