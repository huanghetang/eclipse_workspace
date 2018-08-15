package vip.hht.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.hht.LoginDao.User;
import vip.hht.service.LoginService;

public class LoginServlet extends HttpServlet {
	private String path;
	@Override
	public void init(){
		ServletConfig servletConfig = getServletConfig();
		path = servletConfig.getInitParameter("path");
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取输入流
//		FileInputStream in = new FileInputStream(new File(path));
//		//输出流
//		//发送响应给浏览器。
//		ServletOutputStream out = response.getOutputStream();
//		//标准IO代码
//		byte[] buf = new byte[1024];
//		int len = -1;
//		while((len = in.read(buf)) != -1) {
//			out.write(buf, 0, len);
//		}
//		//关流
//		in.close();
		
		FileInputStream fis = new FileInputStream(new File(path));
		ServletOutputStream os = response.getOutputStream();
		os.write(13);
		os.flush();
		
		byte[] bys= new byte[1024];
		int len = -1;
		while((len=fis.read(bys))!=-1){
			os.write(bys, 0, len);
		}
		fis.close();
}
	

//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(path);
//		String uid = request.getParameter("uid");
//		String upassword = request.getParameter("upassword");
//		LoginService service = new LoginService();
//		User user = service.findUser(uid, upassword);
//		OutputStream os = response.getOutputStream();
//		if(user!=null){
//			os.write("success".getBytes());
//			FileInputStream fis = new FileInputStream(new File(path));
//			byte[] bys= new byte[1024];
//			int len = -1;
//			while((len=fis.read(bys))!=-1){
//				os.write(bys, 0, len);
//			}
//			fis.close();
//		}else{
//			os.write("fail".getBytes());
//		}
//	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
