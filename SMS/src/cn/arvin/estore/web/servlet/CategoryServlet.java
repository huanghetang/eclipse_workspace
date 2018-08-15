package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.arvin.estore.domain.Category;
import cn.arvin.estore.service.CategoryService;
import cn.arvin.estore.service.impl.CategoryServiceImpl;

public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryServiceImpl();
	
	public void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		categoryService.pageQuery(map,page,rows);
		//打印查看
		String jsonString = JSON.toJSONString(map);
		// 服务器响应数据类型 json text/plain 普通字符 text/html 超文本 text/xml 传输 xml文档
		// text/json json格式字符
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}
	
	public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String cname = request.getParameter("cname");
		//设置主键
		String cid = UUID.randomUUID().toString().replace("-", "");
		//封装数据
		Category cate = new Category();
		cate.setCid(cid);
		cate.setCname(cname);
		//调用service方法添加数据
		categoryService.addCategory(cate);
		//跳转到main.html
		response.sendRedirect(request.getContextPath() + "/categorys.html");
	}
	
	public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String cname = request.getParameter("cname");
		String cid = request.getParameter("cid");
		Category category = new Category();
		category.setCname(cname);
		category.setCid(cid);
		categoryService.editCategory(category);
		response.sendRedirect(request.getContextPath()+"/categorys.html");
		
		
	}
	
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String cid1 = request.getParameter("cid");
//		System.out.println(cid1);
		String[] cid = cid1.split(",");
		
		//调用service删除
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.deleteCategory(cid);
		//跳转页面
		response.sendRedirect(request.getContextPath() + "/categorys.html" );
		
	}
	
	public void findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> clist = categoryService.findCategory();
     
		String jsonString = JSON.toJSONString(clist);
		//System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
		
	}
}