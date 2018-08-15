package vip.hht.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyuncs.exceptions.ClientException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import vip.hht.Tools.JRedisUtil;
import vip.hht.Tools.MsgSentUtil;
import vip.hht.Tools.Tool;

public class PhoneCodeServlet extends BaseServlet {
	
	
	/**
	 * ��ȡ�ֻ���֤��,������ʱ��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPhoneCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String code = request.getParameter("code");
			//������֤��
			String msg = Tool.get4Num();
			request.getSession().setAttribute("phone_code", msg);
			//����֤��д�뵽redis�� ����ʱ��30��
//			JRedisUtil.setString2Redis("phone_code", msg,60);
			try {
				//���Ͷ�����֤��
				String retVal = MsgSentUtil.sentMessage(code, msg);
				//�����ֻ���֤��
				if("OK".equalsIgnoreCase(retVal)){
					response.getWriter().write("1");
				}else{
					response.getWriter().write("-1");
				}
			} catch (ClientException e) {
				e.printStackTrace();
			}
		
		
	}
	
	
//	public void getPhoneCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String code = request.getParameter("code");
//		//������֤��
//		String msg = Tool.get4Num();
////		request.getSession().setAttribute("phone_code", msg);
//		System.out.println(msg);
//		//����֤��д�뵽redis�� ����ʱ��30��
//		JRedisUtil.setString2Redis("phone_code", msg,30);
//			//���Ͷ�����֤��
////			String retVal = MsgSentUtil.sentMessage(code, msg);
//			String retVal = "ok";
//			//�����ֻ���֤��
//			if("OK".equalsIgnoreCase(retVal)){
//				response.getWriter().write("1");
//			}else{
//				response.getWriter().write("-1");
//			}
//	
//	
//}

	/**
	 * У���ֻ���֤��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkPhoneCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ͻ���֤��
		String code = request.getParameter("code");
		String phone_code =(String)request.getSession().getAttribute("phone_code");
		//��Redis�л�ȡ��֤��
//		String phone_code = JRedisUtil.getString4Redis("phone_code");
		if("-2".equals(phone_code)){//��֤�볬ʱ
			response.getWriter().write("��֤���ѹ���");
			return;
		}
		if(phone_code.equals(code)){//��֤��У��ɹ�
			response.getWriter().write("1");
		}else{
			response.getWriter().write("-1");
		}
	}
	
	

	
	

	

}
