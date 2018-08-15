package vip.hht.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import vip.hht.Tools.ImplFactory;
import vip.hht.Tools.MailUtils;
import vip.hht.Tools.PaymentUtil;
import vip.hht.beans.Order;
import vip.hht.beans.OrderItemModel;
import vip.hht.beans.User;
import vip.hht.dao.OrderDao;
import vip.hht.service.CartService;
import vip.hht.service.OrderService;

public class OrderController extends BaseServlet {
	
	/**
	 * 异步获取省市县数据
	 * @param request
	 * @param response
	 * @return
	 */
	public String provinceBrother(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("pid");
		OrderService os = ImplFactory.getImpl(OrderService.class);
		//查询省市县list
		List list = os.findListById(id);
		//转为json
		String josnStr = JSON.toJSONString(list);
		//响应浏览器
		return josnStr;
	}
	
	
	/**
	 * 跳转订单填写页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String order2Submit(HttpServletRequest request,HttpServletResponse response){
		User user =(User)request.getSession().getAttribute("user");
		if(user==null){//判断超时
			//转发
			return "forward:/login.jsp";
		}
		//获取到选中的商品pid和当期那用户uid(user)
		String pid = request.getParameter("pid");
		String[] pids = pid.split(",");
		//查询uid,pid对应的商品,并放进List中
		CartService ps = ImplFactory.getImpl(CartService.class);
		List list = ps.findCartModelListById(user.getId(),pids);
		//删除cart中选中的商品(提交订单时删除)
		//ps.deleteProductsByIds(user.getId(),pids);
		//设置数据
		request.setAttribute("cartList", list);
		//传递已提交的商品id
		request.setAttribute("pid", pid);
		//转发到发送订单
		return "forward:/orders_submit.jsp";
	}
	
	/**
	 * 提交订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String saveOrder(HttpServletRequest request,HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		String pcd = request.getParameter("pcd");
		String detailAddress = request.getParameter("detailAddress");
		String mailCode = request.getParameter("mailCode");
		String reviceNname = request.getParameter("reviceNname");
		String telephone = request.getParameter("telephone");
		String pid = request.getParameter("pid");
		String[] pids = pid.split(",");
		String totalPrice = request.getParameter("totalPrice");
		//封装order对象
		Order order = new Order();
		order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setUid(user.getId());
		order.setTotalprice(Double.parseDouble(totalPrice));
		//收货地址，包括收货人信息
		order.setAddress(pcd.replaceAll("/", "")+detailAddress+" "+mailCode+" "+reviceNname+" "+telephone);
		order.setPids(pids);
/*		Timestamp t = new Timestamp(System.currentTimeMillis());
		order.setCreatetime(t);*/
		
		OrderService os = ImplFactory.getImpl(OrderService.class);
		os.saveOrderItems(order);
		return "redirect:/sunbmit_order_success.jsp";
	}
	
	/**
	 * 查询所有订单
	 */
/*	public String listOrder(HttpServletRequest request,HttpServletResponse response){
		//验证登陆
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		//响应浏览器
		OrderService os = ImplFactory.getImpl(OrderService.class);
//		List<Order> list = os.findOrderList();
		int uid = user.getId();
		List<Order> list = os.findOrderListByUid(uid);
		request.setAttribute("orderList", list);
		return "forward:/orders.jsp";
	}*/
	
	/**
	 * 查询订单详情
	 */
	public String orderDetail(HttpServletRequest request,HttpServletResponse response){
		String oid =request.getParameter("oid");
		//验证登陆
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		OrderService os = ImplFactory.getImpl(OrderService.class);
		//查询这条订单信息
		Order order = os.findOrderById(oid);
		//查询这条订单对应的商品明细
		List<OrderItemModel> itemModelList = os.findOrderDetail(oid);
		request.setAttribute("order", order);
		request.setAttribute("itemModelList", itemModelList);
		return "forward:/orders_detail.jsp";
	}
	
	/**
	 * 确认支付,准备发送给易宝支付接口的数据
	 */
	public String confirmPay(HttpServletRequest request,HttpServletResponse response){
		//获取页面参数
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		money = "0.01";
		//银行接口名称
		String pd_FrpId = request.getParameter("pd_FrpId");
		
		//发送到易宝支付接口需要的参数
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		System.out.println(p8_Url);
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//用密钥和发送的参数一起加密得到hmac码,用来校验发送的参数有没有被篡改 
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		//设置参数 get方式写在url后面
		request.setAttribute("pd_FrpId", pd_FrpId);
		request.setAttribute("p0_Cmd", p0_Cmd);
		request.setAttribute("p1_MerId", p1_MerId);
		request.setAttribute("p2_Order", p2_Order);
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur", p4_Cur);
		request.setAttribute("p5_Pid", p5_Pid);
		request.setAttribute("p6_Pcat", p6_Pcat);
		request.setAttribute("p7_Pdesc", p7_Pdesc);
		request.setAttribute("p8_Url", p8_Url);
		request.setAttribute("p9_SAF", p9_SAF);
		request.setAttribute("pa_MP", pa_MP);
		request.setAttribute("pr_NeedResponse", pr_NeedResponse);
		request.setAttribute("hmac", hmac);
		//转发
		return "forward:/confirm.jsp";
	}
	
	
	
	//易宝接受银行扣款成功 向该地址发送请求 ,商家接受易宝响应数据 

	public String callback(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 接受易宝成功响应 回应信息 网站 完成订单状态修改 0 --->1 发货处理...

		// 获得回调所有数据 易宝发送的

		String p1_MerId = request.getParameter("p1_MerId");

		String r0_Cmd = request.getParameter("r0_Cmd");

		String r1_Code = request.getParameter("r1_Code");

		String r2_TrxId = request.getParameter("r2_TrxId");

		String r3_Amt = request.getParameter("r3_Amt");

		String r4_Cur = request.getParameter("r4_Cur");

		String r5_Pid = request.getParameter("r5_Pid");

		String r6_Order = request.getParameter("r6_Order");

		String r7_Uid = request.getParameter("r7_Uid");

		String r8_MP = request.getParameter("r8_MP");

		String r9_BType = request.getParameter("r9_BType");

		String rb_BankId = request.getParameter("rb_BankId");

		String ro_BankOrderId = request.getParameter("ro_BankOrderId");

		String rp_PayDate = request.getParameter("rp_PayDate");

		String rq_CardNo = request.getParameter("rq_CardNo");

		String ru_Trxtime = request.getParameter("ru_Trxtime");

		// 身份校验 --- 判断是不是支付公司通知你

		String hmac = request.getParameter("hmac");// 易宝发送

		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc

		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,

				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);

		// isValid true 易宝发送 false 非法响应

		if (isValid) {

			// 响应数据有效

			if (r9_BType.equals("1")) {

				// 浏览器重定向 修改订单状态

				OrderDao odao = ImplFactory.getImpl(OrderDao.class);

				odao.payok(r6_Order);// update ....
				
				System.out.println("支付完成");
				// 发送邮件给客户 session 获取用户email
				User user = (User) request.getSession().getAttribute("user");
				String email = user.getEmail();
				if(email == null){
					System.out.println("邮件发送失败");
				}else{
					try {
						MailUtils.sendMail(email, "您已支付成功,请注意物流消息");
						System.out.println("邮件发送完毕");
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}

			if (r9_BType.equals("2")) {

				// 服务器点对点 --- 支付公司通知你

				System.out.println("付款成功！");

				// 修改订单状态 为已付款

				// 回复支付公司

				response.getWriter().print("success");

			}

		} else {

			// 数据无效

			System.out.println("数据被篡改！");

		}

	return "redirect:/pay_success.jsp";
}
	
	
	
	/**
	 * 起始日期订单查询
	 */
	public String queryOrderByTime(HttpServletRequest request,HttpServletResponse response){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//验证登陆
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		int uid = user.getId();
		//响应浏览器
		OrderService os = ImplFactory.getImpl(OrderService.class);
		List<Order> list = os.findOrderListByTime(startTime,endTime,uid);
		request.setAttribute("orderList", list);
		//回显日期控件时间
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return "forward:/orders.jsp";
		
	}
	
/*	*//**
	 * 查看我的足迹
	 *//*
	public String productHistory(HttpServletRequest request,HttpServletResponse response){
		OrderService os = ImplFactory.getImpl(OrderService.class);
		List<Order> list = os.findOrderHistory();
		req
		return "forward:/myHistory.jsp";
		
	}*/
	
	/**
	 * 
	 * @方法名称					:	goodsReturn---->OrderController.java  	
	 * @描述						:	请求退货
	 * @作者						:	王飞(Administrator)
	 * @开发公司					:	水之水和水	
	 * @COPYRIGHT				:	copyright(c) 2018,Rights Reserved
	 * @创建日期					:	2018年6月6日 下午7:49:20  
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String goodsReturn(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String oid = request.getParameter("oid");
		OrderService or = ImplFactory.getImpl(OrderService.class);
		or.updateOrderBuId(oid);
		return "redirect:/OrderController?method=listOrder";
	}
	
	
	public String subComment(HttpServletRequest request, HttpServletResponse response) throws IOException{
		User user = (User)request.getSession().getAttribute("user");
		int uid = user.getId();
		String oid = request.getParameter("oid");
		String pid = request.getParameter("pid");
		String level = request.getParameter("comment_rank");
		String comment = request.getParameter("content");
		
		OrderService or = ImplFactory.getImpl(OrderService.class);
		or.addComment(oid,uid,pid,level,comment);
		return "redirect:/comment_sub.jsp";
	}
	
	
	/**
	 * 查询所有订单
	 */
	public String listOrder(HttpServletRequest request,HttpServletResponse response){
		//验证登陆
				User user = (User) request.getSession().getAttribute("user");
				if(user==null){
					return "forward:/login.jsp";
				}
				//响应浏览器
				OrderService os = ImplFactory.getImpl(OrderService.class);
				//根据uid查询订单
				int uid = user.getId();
				List<Order> list = os.findOrderList(uid);
				//查看未支付订单是否过期，超过三个小时未支付设为过期订单
				Date date = new Date();
				Long time1 = date.getTime();
		 		for(Order order : list){
					if(order.getStatus()==0){
						if((time1-order.getCreatetime().getTime())>(3*60*60*1000)){
							order.setStatus(4);
							//更改订单库中的statues
							 os.updateOrderStatus(order.getId());
						} 
					}
				}
				request.setAttribute("orderList", list);
				return "forward:/orders.jsp";
	}
}
