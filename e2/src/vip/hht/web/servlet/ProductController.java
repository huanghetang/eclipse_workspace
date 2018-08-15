package vip.hht.web.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrServerException;

import com.alibaba.fastjson.JSON;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.MyComment;
import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.beans.User;
import vip.hht.service.FootprintService;
import vip.hht.service.OrderService;
import vip.hht.service.ProductService;

public class ProductController extends BaseServlet{
	
	/**
	 * 查询所有
	 * @param request
	 * @param response
	 * @return
	 */
	@Deprecated
	public String list(HttpServletRequest request,HttpServletResponse response){
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		List list = ps.findProductList();
		request.setAttribute("list", list);
		return "forward:/goods.jsp";
	}
	
	/**
	 * 全文检索,默认域 product_keywords,按照商品名称,商品描述,分类描述,来搜素   
	 *
	 */
	
	public String solrList(HttpServletRequest request,HttpServletResponse response){
		//直接点击商品列表过来,没有参数
		String keywords = request.getParameter("keywords");
		String pageNum = request.getParameter("page");
		String size = request.getParameter("size");
		int pageNum1 = pageNum==null?1:Integer.parseInt(pageNum);
		int size1 = size==null?10:Integer.parseInt(size);
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		PageBean pageBean = null;
		try {
			pageBean = ps.findKeyWords(pageNum1, size1, keywords, null, null, null);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keywords", keywords);
		return "forward:/goods.jsp";
	}
	
/*	public String solrList(HttpServletRequest request,HttpServletResponse response){
		//直接点击商品列表过来,没有参数
		String keywords = request.getParameter("keywords");
		String pageNum = request.getParameter("page");
		String size = request.getParameter("size");
		int pageNum1 = pageNum==null?1:Integer.parseInt(pageNum);
		int size1 = size==null?10:Integer.parseInt(size);
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		PageBean pageBean = null;
		try {
			pageBean = ps.findKeyWords(pageNum1, size1, keywords, null, null, null);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keywords", keywords);
		return "forward:/goods.jsp";
	}*/
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@Deprecated
	public String pageList(HttpServletRequest request,HttpServletResponse response){
		String pageNum = request.getParameter("page");
		String size = request.getParameter("size");
		int pageNum1 = pageNum==null?1:Integer.parseInt(pageNum);
		int size1 = size==null?10:Integer.parseInt(size);
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		PageBean pageBean = ps.findProductPageList(pageNum1,size1);
		request.setAttribute("pageBean", pageBean);
		return "forward:/goods.jsp";
	}
	
/*	*//**
	 * 查询商品详情,需要先登陆
	 *//*
	public String productDetail(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			String header = request.getHeader("Referer");
			return "forward:/login.jsp";
		}
		
		Date date = new Date(System.currentTimeMillis());
		String pid = request.getParameter("pid");
		FootprintService footService  = ImplFactory.getImpl(FootprintService.class);
		footService.setFootprint(pid,user.getId(),date);
		
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		Product product = ps.findProductById(pid);
		request.setAttribute("product", product);
		return "forward:/goods_detail.jsp";
	}*/
	
	/**
	 * 查询商品详情,需要先登陆
	 */
	public String productDetail(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		
		//获取用户id
		String pid = request.getParameter("pid");
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		//查询商品
		Product product = ps.findProductById(pid);
		//放入请求中
		request.setAttribute("product", product);
		
		OrderService ordser = ImplFactory.getImpl(OrderService.class);
		//获取分页信息
		String pageNum = request.getParameter("pageNum");
		
		//获取分页信息
		String size = request.getParameter("size");
		int pageNum1 = pageNum==null?1:Integer.parseInt(pageNum);
		int size1 = size==null?10:Integer.parseInt(size);
		//查询分页
		PageBean pageBean = ordser.findCommentByPid(pid,pageNum1,size1);
		request.setAttribute("pageBean", pageBean);
		//获取请求头
		String referer = request.getHeader("Referer");
		//跳转主页
		if(referer==null){
			return "forward:/goods_detail.jsp";
		}
		//截取源页面
		String historyJSP = referer.substring(referer.lastIndexOf("/")+1,referer.lastIndexOf("?"));
		//System.out.println(historyJSP);
		//订单-----新加,有oid时需要挑着到评论
		String oid = request.getParameter("oid");
		if(historyJSP.equals("OrderController")&&oid!=null){
			request.setAttribute("oid", oid);
			return "forward:/comment.jsp";
		}
		
		//添加我的足迹
		Date date = new Date(System.currentTimeMillis());
		FootprintService footService  = ImplFactory.getImpl(FootprintService.class);
		footService.setFootprint(pid,user.getId(),date);
		//转发到详情页
		return "forward:/goods_detail.jsp";
	}
	
	/**
	 * 增加购物车
	 */
	public String addCart(HttpServletRequest request,HttpServletResponse response){
		User user = (User)request.getSession().getAttribute("user");
		String uid = String.valueOf(user.getId());
		String pid = request.getParameter("pid");
		String buynum = request.getParameter("buynum");
		ProductService ps = ImplFactory.getImpl(ProductService.class);
		ps.addCart(pid,uid,buynum);
		return "redirect:/buyorcart.jsp";
	}
	
	
	/**
	 * 获取分页评论
	 */
	public String findCommentPageBean(HttpServletRequest request,HttpServletResponse response){
		OrderService ordser = ImplFactory.getImpl(OrderService.class);
		//商品主键
		String pid = request.getParameter("pid");
		//获取分页信息
		String pageNum = request.getParameter("pageNum");
		String size = request.getParameter("size");
		int pageNum1 = pageNum==null?1:Integer.parseInt(pageNum);
		int size1 = size==null?10:Integer.parseInt(size);
		//查询分页
		PageBean pageBean = ordser.findCommentByPid(pid,pageNum1,size1);
		String commentJSONList = JSON.toJSONString(pageBean);
		return commentJSONList;
	}

}
