package vip.hht.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.User;
import vip.hht.service.CartService;

public class CartController extends BaseServlet{
	
	/**
	 * @return
	 */
	public String list(HttpServletRequest request,HttpServletResponse response){
		CartService  cs = ImplFactory.getImpl(CartService.class);
		User user =(User)request.getSession().getAttribute("user");
		//直接去购物车
		String flag = request.getParameter("flag");
		if(flag!=null && user==null){
			request.getSession().setAttribute("goCart","true");
			return "redirect:/login.jsp";
		}
		//查看购物车需要先登陆
		if(user==null){//没有登陆
			return "redirect:/login.jsp";
		}

/*		//确认跳转页面来源
		String urlfrom = referer.substring(0,referer.lastIndexOf("/")+1);
		//确定是此网站跳转,不是跳到主页
		if(!"http://localhost/e2/".equals(urlfrom)){
			return "redirect:/index.jsp";
		}*/
		//flag=true
		//验证通过,查询数据返回视图
		List list = cs.findCartList(user.getId());
		request.setAttribute("list", list);
		return "forward:/cart.jsp";
	}
	
	
	/**
	 * 删除购物车,异步方法
	 * @param args
	 */
	public String deleteCartById(HttpServletRequest request,HttpServletResponse response){
		String uid = request.getParameter("uid");
		String pid = request.getParameter("pid");
		CartService cs = ImplFactory.getImpl(CartService.class);
		cs.deleteCartById(uid,pid);
		return "1";
	}
	
	/**
	 * 批量删除
	 * @param args
	 */
	public String deleteCartByIds(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.getId();
		String pid = request.getParameter("pid");
		String[] pids = pid.split(",");
		CartService cs = ImplFactory.getImpl(CartService.class);
		cs.deleteCartByIds(uid,pids);
		return "1";
	}
	
	/**
	 * 异步修改商品数量
	 * @param args
	 */
	
	public String editPnumByPid(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.getId();
		String pid = request.getParameter("pid");
		String pnum = request.getParameter("pnum");
		CartService cs = ImplFactory.getImpl(CartService.class);
		cs.editPnumByPid(pnum,pid,uid+"");
		return "1";
	}
	public static void main(String[] args) {
		System.out.println("http://localhost/e2/buyorcart.jsp".matches("http://localhost/e2/buyorcart"));
	}
}