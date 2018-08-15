package vip.hht.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;


public class responseServlet extends HttpServlet {

	/**
	 * 各种文件的下载
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取文件名称
		String fileName = request.getParameter("name");
		//获取上下文环境
		ServletContext context = getServletContext();
		//获取文件的多媒体类型
		String mimeType = context.getMimeType(fileName);
		//设置浏览器响应方式
		response.setContentType(mimeType);
		
		//获取文件所在的绝对路径
		String absolutePath = context.getRealPath("/download");
		File file = new File(absolutePath,fileName);
		//获取浏览器类型
		String brower = request.getHeader("User-Agent");
		//解决文件名中文乱码
		if(brower.contains("Firefox")){
			//火狐下载文件名乱码处理：（先判断是否是火狐浏览器）
			BASE64Encoder base64Encoder = new BASE64Encoder();
			fileName = "=?utf-8?B?"	+ base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";

		}else{//谷歌和其他浏览器对中文编码
			 fileName = URLEncoder.encode(fileName, "utf-8");
		}
		//设置下载文件的文件名
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);

		//写到浏览器
		FileInputStream is = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len = -1;
		ServletOutputStream os = response.getOutputStream();
		while((len=is.read(buf))!=-1){
			os.write(buf, 0, len);
		}
		is.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
