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
		// 在当前servlet初始化的时候，就加载初始化参数
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
		//指定的目录（d:/picture/aaa/1.jpg）获取图片数据
		//灵活变化的数据存入配置文件
		//String path = "d:/picture/aaa/1.jpg";
		
		//需要一个对象，帮助我们获取配置文件中，当前configservlet的配置参数：servletconfig对象
		//使用IO流获取图片数据
		//获取输入流
		FileInputStream in = new FileInputStream(new File(path));
		//输出流
		//发送响应给浏览器。
		ServletOutputStream out = response.getOutputStream();
		//标准IO代码
		byte[] buf = new byte[1024];
		int len = -1;
		while((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		//关流
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}