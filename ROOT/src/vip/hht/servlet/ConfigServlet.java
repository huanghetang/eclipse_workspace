package vip.hht.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigServlet extends HttpServlet {
	
	private String path = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// �ڵ�ǰservlet��ʼ����ʱ�򣬾ͼ��س�ʼ������
		path = config.getInitParameter("path");
		System.out.println(path);
		System.out.println("===========");
		Enumeration<String> names = config.getInitParameterNames();
		while(names.hasMoreElements()) {
			
			System.out.println(names.nextElement());
			
		}
		System.out.println(config.getServletName());
		super.init(config);
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ָ����Ŀ¼��d:/picture/aaa/1.jpg����ȡͼƬ����
		//���仯�����ݴ��������ļ�
		//String path = "d:/picture/aaa/1.jpg";
		
		//��Ҫһ�����󣬰������ǻ�ȡ�����ļ��У���ǰconfigservlet�����ò�����servletconfig����
		//ʹ��IO����ȡͼƬ����
		//��ȡ������
		FileInputStream in = new FileInputStream(new File(path));
		//�����
		//������Ӧ���������
		ServletOutputStream out = response.getOutputStream();
		//��׼IO����
		byte[] buf = new byte[1024];
		int len = -1;
		while((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		//����
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}