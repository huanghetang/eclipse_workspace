package vip.hht.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class testServlet extends BaseServlet{

	
	public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			System.out.println(startTime);
			System.out.println(endTime);
	
	}
}
