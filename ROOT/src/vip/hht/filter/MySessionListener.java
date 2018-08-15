package vip.hht.filter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated.............");
		//session监听常用来监控用户上限
		ServletContext servletContext = se.getSession().getServletContext();
		//获取在线人数
		Integer users = (Integer)servletContext.getAttribute("onlineNum");
		users++;//+1
		//重新放进去
		servletContext.setAttribute("onlineNum", users);
		System.out.println(users);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sessionDestroyed.............");
		//session监听常用来监控用户上限
		ServletContext servletContext = se.getSession().getServletContext();
		//获取在线人数
		Integer users = (Integer)servletContext.getAttribute("onlineNum");
		users++;//+1
		//重新放进去
		servletContext.setAttribute("onlineNum", users);
		System.out.println(users);
	}

}
