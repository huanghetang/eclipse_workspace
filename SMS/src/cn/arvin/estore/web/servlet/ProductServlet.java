package cn.arvin.estore.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;

import cn.arvin.estore.domain.Category;
import cn.arvin.estore.domain.Product;
import cn.arvin.estore.service.CategoryService;
import cn.arvin.estore.service.ProductService;
import cn.arvin.estore.service.impl.CategoryServiceImpl;
import cn.arvin.estore.service.impl.ProductServiceImpl;
import cn.arvin.estore.utils.UploadFileUtils;

public class ProductServlet extends BaseServlet {

	private ProductService productService = new ProductServiceImpl();

	public void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		productService.productList(page1,rows1,map);
		String easyUIJSONDate = JSON.toJSONString(map);
//		System.out.println(easyUIJSONDate);
		//响应
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(easyUIJSONDate);
		
	}
	
	
//	public void addProduct1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//获取Product对象
//		Map<String, String[]> parameterMap = request.getParameterMap();
//		//定义单值集合
//		HashMap<String, Object> formData = new HashMap<String,Object>();
//		if(parameterMap!=null){
//			for (String key : parameterMap.keySet()) {
//				String[] values = parameterMap.get(key);
//				if(values!=null){
//					if(values.length==1){//单值
//						formData.put(key, values[0]);//获取单值集合
//					}else{
//						formData.put(key, values);//不处理
//					}
//				}
//			}
//		}
//		Product product = new Product();
//		try {
//			BeanUtils.populate(product, formData);
//			//添加
//			boolean b = ps.addProduct(product);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> paramMap = new HashMap<String,String>();
		 //上传图片
	     String savePath = UploadFileUtils.uploadFile(request, paramMap);
	     
	   //封装数据
		Product p = new Product();
			//使用UUID随机生成一个随机码来做pid,保证了唯一性
		String pid = UUID.randomUUID().toString().replace("-", "");
		p.setPid(pid);
		p.setPname(paramMap.get("pname"));
		p.setIs_hot(Integer.parseInt(paramMap.get("is_hot")));
		p.setPimage(savePath);
		p.setMarket_price(Double.parseDouble(paramMap.get("market_price")));
		p.setShop_price(Double.parseDouble(paramMap.get("shop_price")));
		p.setCid(paramMap.get("cid"));
		p.setPdesc(paramMap.get("pdesc"));
		p.setPnum(Integer.parseInt(paramMap.get("pnum")));
		//生成年月日,当做修改的那天的日期
	    Date date = new Date();
		 p.setPdate(date);
		// System.out.println(date.getClass());
		 boolean b =productService.addProduct(p);
	    if(b){
	    response.sendRedirect(request.getContextPath()+"/products.html");
	    }
	}
	
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> paramMap = new HashMap<String,String>();
	   //封装数据
		Product p = new Product();
			//使用UUID随机生成一个随机码来做pid,保证了唯一性
		String savePath = UploadFileUtils.uploadFile(request, paramMap);
		p.setPid(paramMap.get("pid"));
		p.setPname(paramMap.get("pname"));
		p.setIs_hot(Integer.parseInt(paramMap.get("is_hot")));
		p.setMarket_price(Double.parseDouble(paramMap.get("market_price")));
		p.setShop_price(Double.parseDouble(paramMap.get("shop_price")));
		p.setCid(paramMap.get("cid"));
		p.setPdesc(paramMap.get("pdesc"));
		p.setPnum(Integer.parseInt(paramMap.get("pnum")));
		//生成年月日,当做修改的那天的日期
	    Date date = new Date();
		p.setPdate(date);
		// System.out.println(date.getClass());
	    boolean b = productService.updateProduct(p);
	    if(b){
	    response.sendRedirect(request.getContextPath()+"/products.html");
	   }
			
	}
	
	
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String pid1 = request.getParameter("pid");
		String[] pid = pid1.split(",");
		
		//调用service删除
		productService.deleteProduct(pid);
		//跳转页面
		response.sendRedirect(request.getContextPath() + "/products.html" );
		
	}
	
	
	
	
	
	
	
	
	
	
}