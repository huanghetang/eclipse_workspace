package vip.hht.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServletContext extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		System.out.println(context.getContextPath());
		String s = context.getInitParameter("¾°Ìð");
		System.out.println(s);
//		Enumeration<String> attributeNames = context.getAttributeNames();
//		while(attributeNames.hasMoreElements()){
//			String element = attributeNames.nextElement();
//			System.out.println(element+":"+context.getInitParameter(element));
//		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

