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
	 * �����ļ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ļ�����
		String fileName = request.getParameter("name");
		//��ȡ�����Ļ���
		ServletContext context = getServletContext();
		//��ȡ�ļ��Ķ�ý������
		String mimeType = context.getMimeType(fileName);
		//�����������Ӧ��ʽ
		response.setContentType(mimeType);
		
		//��ȡ�ļ����ڵľ���·��
		String absolutePath = context.getRealPath("/download");
		File file = new File(absolutePath,fileName);
		//��ȡ���������
		String brower = request.getHeader("User-Agent");
		//����ļ�����������
		if(brower.contains("Firefox")){
			//��������ļ������봦�������ж��Ƿ��ǻ���������
			BASE64Encoder base64Encoder = new BASE64Encoder();
			fileName = "=?utf-8?B?"	+ base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";

		}else{//�ȸ����������������ı���
			 fileName = URLEncoder.encode(fileName, "utf-8");
		}
		//���������ļ����ļ���
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);

		//д�������
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
