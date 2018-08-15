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
 * ?method ���������������
 * �ο���springmvc�������,������"forward:/*"�ؼ���ʱת��,����"redirect:/*"ʱ�ض���(Ĭ��·����classpath)
 * Ĭ�Ϸ���""Ϊjson����ʽ
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
					if(invoke instanceof String){//����ֵ forward:,redirect:,json:
						String keyword = (String)invoke;
						if(keyword.startsWith("forward:")){//ת��
							request.getRequestDispatcher(keyword.substring("forward:".length()+1)).forward(request, response);
							return;
						}
						if(keyword.startsWith("redirect:")){//�ض���
							response.sendRedirect(request.getContextPath()+"/"+keyword.substring("redirect:".length()+1));
							return;
						}
						//����ֵΪStringʱĬ��json
						response.setContentType("text/json;charset=utf-8");
						response.getWriter().write(keyword);
					}
					
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

