package vip.hht.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 实现装饰模式
 * 1,实现共同的接口
 * 2,定义一个成员变量保存需要装饰的对象
 * 3,在构造中给这个成员变量初始化
 * 4,增强需要增强的方法
 * 5,直接调用不需要增强的方法
 * @author zhoumo
 *
 */
public class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	
	//第一次获取到编解码后赋值,解决重复编解码
	private boolean flag = false;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		//请求方式
		String method = request.getMethod();
		if("post".equalsIgnoreCase(method)){//post请求
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if("get".equalsIgnoreCase(method)){//get请求
			Map<String,String[]> map = request.getParameterMap();
			if(flag){
				return map;
			}
			if(map!=null){
				for(String key : map.keySet()){
					String[] values = map.get(key);
					if(values != null){
						for(int i=0;i<values.length;i++){
							try {
								String value = new String(values[i].getBytes("iso-8859-1"),"utf-8");//编解码
								values[i] = value;//重新赋值
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								return super.getParameterMap();
							}
						}
					}
				}
				//第一次编解码后开关打开
				flag = true;
				return map;
			}
		}
		//其他请求不做处理
		return super.getParameterMap();
	}

	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> map = this.getParameterMap();
		if(map!=null){
			String[] strings = map.get(name);
			return strings;
		}
		return super.getParameterValues(name);
	}
	
	@Override
	public String getParameter(String name) {
		String[] values = this.getParameterValues(name);
		if(values !=null){
			return values[0];
		}
		return super.getParameter(name);
	}

}
