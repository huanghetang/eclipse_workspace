package vip.hht.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;

import vip.hht.estore.beans.Product;
import vip.hht.estore.service.ProductService;
import vip.hht.estore.web.serviceImpl.ProductServiceImpl;

/**
 * 商品管理
 * @author zhoumo
 *
 */
public class ProductServlet extends BaseServlet {
	private ProductService ps = new ProductServiceImpl();
	
	/**
	 * 商品分页查询
	 * @throws IOException 
	 */
	public void productList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取当前页
		String page = request.getParameter("page");
		//获取每页数
		String rows = request.getParameter("rows");
		//转换类型
		int page1 = page==null?1:Integer.parseInt(page);
		int rows1 = rows==null?3:Integer.parseInt(rows);
		//初始化分页原型对象
		HashMap<String, Object> map = new HashMap<String,Object>();
		//赋值
		ps.productList(page1,rows1,map);
		String easyUIJSONDate = JSON.toJSONString(map);
		System.out.println(easyUIJSONDate);
		//响应
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(easyUIJSONDate);
	}
	
	
	/**
	 * 添加
	 */
	public void addProduct(HttpServletRequest request,HttpServletResponse response){
		//获取Product对象
		Map<String, String[]> parameterMap = request.getParameterMap();
		//定义单值集合
		HashMap<String, Object> formData = new HashMap<String,Object>();
		if(parameterMap!=null){
			for (String key : parameterMap.keySet()) {
				String[] values = parameterMap.get(key);
				if(values!=null){
					if(values.length==1){//单值
						formData.put(key, values[0]);//获取单值集合
					}else{
						formData.put(key, values);//不处理
					}
				}
			}
		}
		Product product = new Product();
		try {
			BeanUtils.populate(product, formData);
			//添加
			boolean b = ps.addProduct(product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
