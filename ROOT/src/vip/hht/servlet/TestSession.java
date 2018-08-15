package vip.hht.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		showLastLoginTime(request,response);
		loginWithYZM(request,response);
	}
	
	/**
	 * ÿ�ε�½��ʾ�ϴεĵ�½ʱ��
	 */
	private void showLastLoginTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String localString = new Date().toLocaleString();
		System.out.println(localString);
		Cookie cookie = new Cookie("time", localString);
		response.addCookie(cookie);
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie2 : cookies) {
				if(cookie2.getName().equals("time")){
					response.getWriter().write("last login time:"+cookie2.getValue());
				}
			}
		}
	}
	
	/**
	 * ��½����,������֤��,���Է�ֹ�������ƽ�����
	 * 
	 */
	private void loginWithYZM(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		//��ȡҳ����֤���session�е���֤�����Ƚ�
		String code = request.getParameter("code");
		String realCode = (String) request.getSession().getAttribute("code_session");
		if(realCode.equalsIgnoreCase(code)){//��֤����ȷ
			System.out.println("��½�ɹ�");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		}else{
			System.out.println("��֤�����");
			request.setAttribute("msg", "��֤�����");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
		
	}

/**
 * ������sessionĬ����Ч��30���ӻ���������ر�֮ǰ,session�ǻ���cookie�ļ���,�������һ������ϵͳ,ϵͳ�ᴴ��һ��session,session�п��Ա�������,
 * Ȼ��ѵ�ǰsession��id���浽�������cookie��,���ǵ�ǰcookie����Ч����������ر�֮ǰ.�����Լ��ӳ����session����Ч��,�ֶ�����һ��cookie,key��
 * JSESSIONID,value��session.getId()����ϵͳ�Լ�������cookie.Ȼ���ٸ����session������Ч�ھͿ�����
 */
/*	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ��ǰ��session
		HttpSession session = request.getSession();
		System.out.println(session);
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
//		cookie.setMaxAge(60*60*7);
		session.invalidate();
		response.addCookie(cookie);
		System.out.println(session);
		HttpSession session2 = request.getSession();
		System.out.println(session2+"========"+session2.getId());
	}*/

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
