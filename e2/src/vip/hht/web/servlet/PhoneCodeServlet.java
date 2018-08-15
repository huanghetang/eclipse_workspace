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
	 * 获取手机验证码,带过期时间
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPhoneCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String code = request.getParameter("code");
			//创建验证码
			String msg = Tool.get4Num();
			request.getSession().setAttribute("phone_code", msg);
			//将验证码写入到redis中 过期时间30秒
//			JRedisUtil.setString2Redis("phone_code", msg,60);
			try {
				//发送短信验证码
				String retVal = MsgSentUtil.sentMessage(code, msg);
				//设置手机验证码
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
//		//创建验证码
//		String msg = Tool.get4Num();
////		request.getSession().setAttribute("phone_code", msg);
//		System.out.println(msg);
//		//将验证码写入到redis中 过期时间30秒
//		JRedisUtil.setString2Redis("phone_code", msg,30);
//			//发送短信验证码
////			String retVal = MsgSentUtil.sentMessage(code, msg);
//			String retVal = "ok";
//			//设置手机验证码
//			if("OK".equalsIgnoreCase(retVal)){
//				response.getWriter().write("1");
//			}else{
//				response.getWriter().write("-1");
//			}
//	
//	
//}

	/**
	 * 校验手机验证码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkPhoneCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取客户验证码
		String code = request.getParameter("code");
		String phone_code =(String)request.getSession().getAttribute("phone_code");
		//从Redis中获取验证码
//		String phone_code = JRedisUtil.getString4Redis("phone_code");
		if("-2".equals(phone_code)){//验证码超时
			response.getWriter().write("验证码已过期");
			return;
		}
		if(phone_code.equals(code)){//验证码校验成功
			response.getWriter().write("1");
		}else{
			response.getWriter().write("-1");
		}
	}
	
	

	
	

	

}
