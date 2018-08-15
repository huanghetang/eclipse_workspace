package vip.hht.estore.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import vip.hht.estore.service.OrderService;
import vip.hht.estore.web.serviceImpl.OrderServiceImpl;
public class OrderServlet extends BaseServlet {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private OrderService os = new OrderServiceImpl();
	public void listOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//easyUI��ҳ���͵Ĳ���page,rows
		String page = request.getParameter("page");//��ǰҳ
		String rows = request.getParameter("rows");//ÿҳ��ʾ��
		int page1 = page==null?1:Integer.parseInt(page);//Ĭ�ϵ�һҳ
		int rows1 = rows==null?3:Integer.parseInt(rows);//Ĭ��ÿҳ��ʾ3��
		Map map = os.getListOrder(page1,rows1);
		String jsonString = JSON.toJSONString(map);
//		System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	

	public void listPendingOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page = request.getParameter("page");//��ǰҳ
		String rows = request.getParameter("rows");//ÿҳ��ʾ��
		int status = 2;
		int page1 = page==null?1:Integer.parseInt(page);//Ĭ�ϵ�һҳ
		int rows1 = rows==null?3:Integer.parseInt(rows);//Ĭ��ÿҳ��ʾ3��
		Map map = os.listPendingOrder(page1,rows1,status);
		String jsonString = JSON.toJSONString(map);
//		System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
		
	
		
	}
	public void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		os.updateOrderStatus(id);
		response.setContentType("text/json;charset=utf-8");
		response.sendRedirect(request.getContextPath()+"/orderList2.html");
	}
		
}
