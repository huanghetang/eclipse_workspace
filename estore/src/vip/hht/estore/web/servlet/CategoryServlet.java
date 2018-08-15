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
 * ��Ʒ����
 * @author zhoumo
 *
 */
public class CategoryServlet extends BaseServlet {
	private CategoryService cs = new CategoryServiceImpl();
	/**
	 * ��ѯ��Ʒ��ҳ�б�
	 * ��ҳ���뷵�ذ���total,rows����������json����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//easyUI��ҳ���͵Ĳ���page,rows
		String page = request.getParameter("page");//��ǰҳ
		String rows = request.getParameter("rows");//ÿҳ��ʾ��
		int page1 = page==null?1:Integer.parseInt(page);//Ĭ�ϵ�һҳ
		int rows1 = rows==null?3:Integer.parseInt(rows);//Ĭ��ÿҳ��ʾ3��
		Map map = cs.getListCategory(page1,rows1);
		String jsonString = JSON.toJSONString(map);
//		System.out.println(jsonString);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonString);
	}

	/**
	 * ������Ʒ����
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
	 * �޸���Ʒ����
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
	 * ɾ����Ʒ����
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unused")
		//��������͸�ʽ ids: 1,2,ʹ��String��String[]�����Խ���
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
