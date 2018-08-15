package vip.hht.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * ʵ��װ��ģʽ
 * 1,ʵ�ֹ�ͬ�Ľӿ�
 * 2,����һ����Ա����������Ҫװ�εĶ���
 * 3,�ڹ����и������Ա������ʼ��
 * 4,��ǿ��Ҫ��ǿ�ķ���
 * 5,ֱ�ӵ��ò���Ҫ��ǿ�ķ���
 * @author zhoumo
 *
 */
public class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	
	//��һ�λ�ȡ��������ֵ,����ظ������
	private boolean flag = false;
	
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		//����ʽ
		String method = request.getMethod();
		if("post".equalsIgnoreCase(method)){//post����
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else if("get".equalsIgnoreCase(method)){//get����
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
								String value = new String(values[i].getBytes("iso-8859-1"),"utf-8");//�����
								values[i] = value;//���¸�ֵ
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								return super.getParameterMap();
							}
						}
					}
				}
				//��һ�α����󿪹ش�
				flag = true;
				return map;
			}
		}
		//��������������
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
