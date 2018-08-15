package vip.hht.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;

/**
 * ����get��post��������
 * @author zhoumo
 *
 */
public class MyRequest extends HttpServletRequestWrapper{
	//���õı���
	private String encoding;
	//��Ҫ��װ�ε���
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
		if("post".equalsIgnoreCase(requestType)){//post����
			try {
				//����tomcat���뷽ʽ
				req.setCharacterEncoding(encoding);
				return req.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if("get".equalsIgnoreCase(requestType)){//get����
			Map<String, String[]> map = req.getParameterMap();
			if(flag){
				return map;
			}
			//tomcat���Զ����Ҽ��� URIEncoding="UTF-8"
/*			if(map!=null){
				for (String key : map.keySet()) {
					String[] values = map.get(key);//��ȡֵ����
					if(values!=null){
						for (int i =0;i<values.length;i++) {
							try {
								String encodValue = new String(values[i].getBytes("iso-8859-1"),encoding);
								values[i] = encodValue;//���¸�ֵ
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
