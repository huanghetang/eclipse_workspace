package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.arvin.estore.factory.BeanFactory;
import cn.arvin.estore.service.CategoryService;
import cn.arvin.estore.service.OrderService;
import cn.arvin.estore.service.impl.CategoryServiceImpl;

public class OrderServlet extends BaseServlet {
	
	private OrderService orderService = BeanFactory.getInstance(OrderService.class);
	
	public void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取ajax请求参数
		String page1 = request.getParameter("page");
		String rows1 = request.getParameter("rows");
		if(page1 == null || rows1 == null){
			response.sendRedirect(request.getContextPath()+"/main.html");
			return;
		}
		int page = Integer.parseInt(page1);
		int rows = Integer.parseInt(rows1);
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		orderService.pageQuery(map,page,rows);
		
		String jsonString = JSON.toJSONString(map);
		// 服务器响应数据类型 json text/plain 普通字符 text/html 超文本 text/xml 传输 xml文档
		// text/json json格式字符
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	
	
	public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
	}
	
	
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
	
	//待审核页面
	public void listPendingOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = BeanFactory.getInstance(OrderService.class);
		String page = request.getParameter("page");//当前页
		String rows = request.getParameter("rows");//每页显示数
//		System.out.println(page + rows);
		int status = 2;
		int page1 = page==null?1:Integer.parseInt(page);//默认第一页
		int rows1 = rows==null?3:Integer.parseInt(rows);//默认每页显示5条
		Map map = orderService.listPendingOrder(page1,rows1,status);
		String jsonString = JSON.toJSONString(map);
//		System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	
	
	//审核通过数据请求
	public void passOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String id1 = request.getParameter("id");
		String[] id = id1.split(",");
		
		//调用service
		orderService.pass(id);
		//跳转页面
		response.sendRedirect(request.getContextPath() + "/orderList2.html" );
		
	}
}