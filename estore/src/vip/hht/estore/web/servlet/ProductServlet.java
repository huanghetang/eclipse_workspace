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
 * ��Ʒ����
 * @author zhoumo
 *
 */
public class ProductServlet extends BaseServlet {
	private ProductService ps = new ProductServiceImpl();
	
	/**
	 * ��Ʒ��ҳ��ѯ
	 * @throws IOException 
	 */
	public void productList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//��ȡ��ǰҳ
		String page = request.getParameter("page");
		//��ȡÿҳ��
		String rows = request.getParameter("rows");
		//ת������
		int page1 = page==null?1:Integer.parseInt(page);
		int rows1 = rows==null?3:Integer.parseInt(rows);
		//��ʼ����ҳԭ�Ͷ���
		HashMap<String, Object> map = new HashMap<String,Object>();
		//��ֵ
		ps.productList(page1,rows1,map);
		String easyUIJSONDate = JSON.toJSONString(map);
		System.out.println(easyUIJSONDate);
		//��Ӧ
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(easyUIJSONDate);
	}
	
	
	/**
	 * ���
	 */
	public void addProduct(HttpServletRequest request,HttpServletResponse response){
		//��ȡProduct����
		Map<String, String[]> parameterMap = request.getParameterMap();
		//���嵥ֵ����
		HashMap<String, Object> formData = new HashMap<String,Object>();
		if(parameterMap!=null){
			for (String key : parameterMap.keySet()) {
				String[] values = parameterMap.get(key);
				if(values!=null){
					if(values.length==1){//��ֵ
						formData.put(key, values[0]);//��ȡ��ֵ����
					}else{
						formData.put(key, values);//������
					}
				}
			}
		}
		Product product = new Product();
		try {
			BeanUtils.populate(product, formData);
			//���
			boolean b = ps.addProduct(product);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	

}
