package vip.hht.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.FootprintItem;
import vip.hht.beans.User;
import vip.hht.service.FootprintService;
import vip.hht.service.UserService;
import vip.hht.serviceImpl.UserServiceImpl;

/**
 * @author zhoumo
 *
 */
public class RegisterServlet extends BaseServlet{
	
	/**
	 * 用户注册
	 */
	public String userRegister(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		User user = new User();
		user.setEmail(email);
		user.setNickname(nickname);
		user.setPassword(password);
		user.setTelephone(phone);
		UserService us = ImplFactory.getImpl(UserServiceImpl.class);
		User u = us.register(user);
		if(u!=null){
			request.getSession().setAttribute("user", u);
			return "redirect:/index.jsp";
		}
		return "redirect:/register.jsp";
		
	}
	
	/**
	 * 校验用户名是否可用
	 */
	public String checkName(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("name");
		//查询数据库
		UserService us = ImplFactory.getImpl(UserServiceImpl.class);
		User u = us.getUserByEmail(email);
		if(u!=null){//该用户已存在
			return "-1";
		}else{
			return "1";
		}
	}
	
	/**
	 * 校验验证码是否正确
	 */
	public String checkyzm(HttpServletRequest request,HttpServletResponse response){
		String yzm = request.getParameter("yzm");
		String sessyzm = (String) request.getSession().getAttribute("code");
		if(sessyzm.equalsIgnoreCase(yzm)){
			return "1";
		}
		return "-1";
	}
	
	/**
	 * 登陆验证手机号码
	 */
	
	public String loginCheck(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		UserService us = ImplFactory.getImpl(UserServiceImpl.class);
		User user = us.getUserByPhone(code);
		if(user==null){
			return "-1";
		}
		return "1";
	}
	
	/**
	 * 用户登陆
	 */
	public String userLogin(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("username");
		UserService us = ImplFactory.getImpl(UserServiceImpl.class);
		//用户已经在页面校验是存在的
		User user = us.getUserByPhone(code);
		//登陆成功
		request.getSession().setAttribute("user", user);
		//获取头
		String referer = request.getHeader("Referer");
		//有头跳转到原网页(商品详情)
		if(referer!=null){
			//截取原网页 goods_detail.jsp
			String historyJsp = referer.substring(referer.lastIndexOf("/")+1);
			//要不要去购物车
			String goCart = (String)request.getSession().getAttribute("goCart");
			if(goCart!=null){
				return "redirect:/CartController?method=list";
			}
			if("login.jsp".equals(historyJsp) && goCart ==null){
				FootprintService footService = ImplFactory.getImpl(FootprintService.class);
				List<FootprintItem> list = footService.findAllFootprint(user.getId());
				request.setAttribute("fproduct", list);
				return "forward:/index.jsp";
			}
			return "redirect:/"+historyJsp;
		}
		//没头跳到主页
		FootprintService footService = ImplFactory.getImpl(FootprintService.class);
		List<FootprintItem> list = footService.findAllFootprint(user.getId());
		request.setAttribute("fproduct", list);
		
		return "forward:/index.jsp";

		
	}
	
	/**
	 * 退出
	 */
	
	public String logout(HttpServletRequest request,HttpServletResponse response){
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
			return "redirect:/login.jsp";
		
	}
	
	public static void main(String[] args) {
		String referer = "http://localhost/e2/goods_detail.jsp";
		String historyJsp = referer.substring(referer.lastIndexOf("/")+1);
		System.out.println(historyJsp);
	}
	
	
	protected String findAllFootPrint(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		User existUser= (User)request.getSession().getAttribute("user");
		if(existUser==null){
			return "tpslogin.jsp";
		}
		FootprintService footService = ImplFactory.getImpl(FootprintService.class);
		List<FootprintItem> list = footService.findAllFootprint(existUser.getId());
		request.setAttribute("fproduct", list);
		return "forward:/myfootprint.jsp";
	}
	
	protected String index(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		User existUser= (User)request.getSession().getAttribute("user");
		if(existUser==null){
			return "redirect:/index.jsp";
		}
		FootprintService footService = ImplFactory.getImpl(FootprintService.class);
		List<FootprintItem> list = footService.findAllFootprint(existUser.getId());
		request.setAttribute("fproduct", list);
		return "forward:/index.jsp";
	}
}
