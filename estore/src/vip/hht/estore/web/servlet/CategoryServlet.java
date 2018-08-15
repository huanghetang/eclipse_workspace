package vip.hht.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import vip.hht.estore.beans.Category;
import vip.hht.estore.service.CategoryService;
import vip.hht.estore.web.serviceImpl.CategoryServiceImpl;
import vip.hht.utils.HTUtils;

/**
 * 商品分类
 * @author zhoumo
 *
 */
public class CategoryServlet extends BaseServlet {
	private CategoryService cs = new CategoryServiceImpl();
	/**
	 * 查询商品分页列表
	 * 分页必须返回包涵total,rows两个参数的json数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//easyUI分页发送的参数page,rows
		String page = request.getParameter("page");//当前页
		String rows = request.getParameter("rows");//每页显示数
		int page1 = page==null?1:Integer.parseInt(page);//默认第一页
		int rows1 = rows==null?3:Integer.parseInt(rows);//默认每页显示3条
		Map map = cs.getListCategory(page1,rows1);
		String jsonString = JSON.toJSONString(map);
//		System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}

	/**
	 * 增加商品分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCname(cname);
		category.setCid(HTUtils.getUUID());
		cs.addCategory(category);
		response.sendRedirect(request.getContextPath()+"/categoryList.html");
	}
	
	/**
	 * 修改商品分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		String cid = request.getParameter("cid");
		Category category = new Category();
		category.setCname(cname);
		category.setCid(cid);
		cs.editCategory(category);
		response.sendRedirect(request.getContextPath()+"/categoryList.html");
	}
	
	
	/**
	 * 删除商品分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		//浏览器发送格式 ids: 1,2,使用String和String[]都可以接受
//		String[] ids = request.getParameterValues("ids");
		String ids = request.getParameter("ids");
		System.out.println(ids.trim());
		boolean b = cs.deleteCategoryByIds(ids);
		if(b){
			response.getWriter().write("1");
		}
//		response.sendRedirect(request.getContextPath()+"/category.do?method=listCategory");
	}
	

}
