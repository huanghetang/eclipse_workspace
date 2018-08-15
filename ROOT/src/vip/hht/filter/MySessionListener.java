package vip.hht.filter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated.............");
		//session��������������û�����
		ServletContext servletContext = se.getSession().getServletContext();
		//��ȡ��������
		Integer users = (Integer)servletContext.getAttribute("onlineNum");
		users++;//+1
		//���·Ž�ȥ
		servletContext.setAttribute("onlineNum", users);
		System.out.println(users);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestroyed.............");
		//session��������������û�����
		ServletContext servletContext = se.getSession().getServletContext();
		//��ȡ��������
		Integer users = (Integer)servletContext.getAttribute("onlineNum");
		users++;//+1
		//���·Ž�ȥ
		servletContext.setAttribute("onlineNum", users);
		System.out.println(users);
	}

}
