package vip.hht.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

/**
 * 处理get和post请求乱码
 * @author zhoumo
 *
 */
public class MyRequest extends HttpServletRequestWrapper{
	//配置的编码
	private String encoding;
	//需要被装饰的类
	private HttpServletRequest req;
	
	//
	boolean flag = false;
	
	public MyRequest(HttpServletRequest req,String encoding) {
		super(req);
		this.encoding = encoding;
		this.req = req;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		String requestType = req.getMethod();
		if("post".equalsIgnoreCase(requestType)){//post请求
			try {
				//设置tomcat解码方式
				req.setCharacterEncoding(encoding);
				return req.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if("get".equalsIgnoreCase(requestType)){//get请求
			Map<String, String[]> map = req.getParameterMap();
			if(flag){
				return map;
			}
			//tomcat会自动给我加上 URIEncoding="UTF-8"
/*			if(map!=null){
				for (String key : map.keySet()) {
					String[] values = map.get(key);//获取值数据
					if(values!=null){
						for (int i =0;i<values.length;i++) {
							try {
								String encodValue = new String(values[i].getBytes("iso-8859-1"),encoding);
								values[i] = encodValue;//重新赋值
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
							
						}
					}
					
				}
			}*/
			flag = true;
			return map;
		}
		return super.getParameterMap();
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = getParameterMap();
		if(map!=null){
			return map.get(name);
		}
		return super.getParameterValues(name);
	}
	
	@Override
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if(values!=null){
			return values[0];
		}
		return super.getParameter(name);
	}
	
	
}
