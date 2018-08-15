package vip.hht.servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MD5Servlet extends HttpServlet {
	/**
	 * ǰ̨����һ����,�����������MD5���ܺ������
	 * ���ܺ������Ҫ��mysqlһ��
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("mimi");
		
		System.out.println(source);
		try {
			//��ȡMD5����ʵ��
			MessageDigest md = MessageDigest.getInstance("MD5");
			//����,���ؼ��ܺ���ֽ�����,��������(mysql select md5('abc');���ص�ֵû�з���)
			byte[] digest = md.digest(source.getBytes());
			//
			StringBuilder retVal = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				byte b = digest[i];
				int bb = b & 255;//��ȡ����ֵ (����  ������,ȥ����������λ)
				//��ȡ��Ӧ��16��������
				String hexString="";
				if(bb>=0 && bb<16){//������16������ֻ��һλ,��Ҫ��ǰ���һ��0
					hexString ="0"+Integer.toHexString(bb);
					
				}else{
					 hexString = Integer.toHexString(bb);
				}
				retVal.append(hexString);
			}
			request.setAttribute("retVal", retVal.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			request.setAttribute("retVal", "���ܳ���");
		}
		request.getRequestDispatcher("/jsp/md5.jsp").forward(request, response);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//900150983cd24fb0d6963f7d28e17f72
	//900150983cd24fb0d6963f7d28e17f72
	public static void main(String[] args) {
		String source ="��";
		try {
			//��ȡMD5����ʵ��
			MessageDigest md = MessageDigest.getInstance("MD5");
			//����,���ؼ��ܺ���ֽ�����,��������(mysql select md5('abc');���ص�ֵû�з���)
			byte[] digest = md.digest(source.getBytes());
			//
			StringBuilder retVal = new StringBuilder();
			for (int i = 0; i < digest.length; i++) {
				byte b = digest[i];
				int bb = b & 255;//��ȡ����ֵ (����  ������,ȥ����������λ)
				//��ȡ��Ӧ��16��������
				String hexString="";
				if(bb>=0 && bb<16){//������16������ֻ��һλ,��Ҫ��ǰ���һ��0
					hexString ="0"+Integer.toHexString(bb);
					
				}else{
					 hexString = Integer.toHexString(bb);
				}
				retVal.append(hexString);
			}
			System.out.println(retVal.toString());
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
