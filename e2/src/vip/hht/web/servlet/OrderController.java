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
	 * �첽��ȡʡ��������
	 * @param request
	 * @param response
	 * @return
	 */
	public String provinceBrother(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("pid");
		OrderService os = ImplFactory.getImpl(OrderService.class);
		//��ѯʡ����list
		List list = os.findListById(id);
		//תΪjson
		String josnStr = JSON.toJSONString(list);
		//��Ӧ�����
		return josnStr;
	}
	
	
	/**
	 * ��ת������дҳ��
	 * @param request
	 * @param response
	 * @return
	 */
	public String order2Submit(HttpServletRequest request,HttpServletResponse response){
		User user =(User)request.getSession().getAttribute("user");
		if(user==null){//�жϳ�ʱ
			//ת��
			return "forward:/login.jsp";
		}
		//��ȡ��ѡ�е���Ʒpid�͵������û�uid(user)
		String pid = request.getParameter("pid");
		String[] pids = pid.split(",");
		//��ѯuid,pid��Ӧ����Ʒ,���Ž�List��
		CartService ps = ImplFactory.getImpl(CartService.class);
		List list = ps.findCartModelListById(user.getId(),pids);
		//ɾ��cart��ѡ�е���Ʒ(�ύ����ʱɾ��)
		//ps.deleteProductsByIds(user.getId(),pids);
		//��������
		request.setAttribute("cartList", list);
		//�������ύ����Ʒid
		request.setAttribute("pid", pid);
		//ת�������Ͷ���
		return "forward:/orders_submit.jsp";
	}
	
	/**
	 * �ύ����
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
		//��װorder����
		Order order = new Order();
		order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setUid(user.getId());
		order.setTotalprice(Double.parseDouble(totalPrice));
		//�ջ���ַ�������ջ�����Ϣ
		order.setAddress(pcd.replaceAll("/", "")+detailAddress+" "+mailCode+" "+reviceNname+" "+telephone);
		order.setPids(pids);
/*		Timestamp t = new Timestamp(System.currentTimeMillis());
		order.setCreatetime(t);*/
		
		OrderService os = ImplFactory.getImpl(OrderService.class);
		os.saveOrderItems(order);
		return "redirect:/sunbmit_order_success.jsp";
	}
	
	/**
	 * ��ѯ���ж���
	 */
/*	public String listOrder(HttpServletRequest request,HttpServletResponse response){
		//��֤��½
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		//��Ӧ�����
		OrderService os = ImplFactory.getImpl(OrderService.class);
//		List<Order> list = os.findOrderList();
		int uid = user.getId();
		List<Order> list = os.findOrderListByUid(uid);
		request.setAttribute("orderList", list);
		return "forward:/orders.jsp";
	}*/
	
	/**
	 * ��ѯ��������
	 */
	public String orderDetail(HttpServletRequest request,HttpServletResponse response){
		String oid =request.getParameter("oid");
		//��֤��½
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		OrderService os = ImplFactory.getImpl(OrderService.class);
		//��ѯ����������Ϣ
		Order order = os.findOrderById(oid);
		//��ѯ����������Ӧ����Ʒ��ϸ
		List<OrderItemModel> itemModelList = os.findOrderDetail(oid);
		request.setAttribute("order", order);
		request.setAttribute("itemModelList", itemModelList);
		return "forward:/orders_detail.jsp";
	}
	
	/**
	 * ȷ��֧��,׼�����͸��ױ�֧���ӿڵ�����
	 */
	public String confirmPay(HttpServletRequest request,HttpServletResponse response){
		//��ȡҳ�����
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		money = "0.01";
		//���нӿ�����
		String pd_FrpId = request.getParameter("pd_FrpId");
		
		//���͵��ױ�֧���ӿ���Ҫ�Ĳ���
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// ֧���ɹ��ص���ַ ---- ������֧����˾����ʡ��û�����
		// ������֧�����Է�����ַ
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
		System.out.println(p8_Url);
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		//����Կ�ͷ��͵Ĳ���һ����ܵõ�hmac��,����У�鷢�͵Ĳ�����û�б��۸� 
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		//���ò��� get��ʽд��url����
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
		//ת��
		return "forward:/confirm.jsp";
	}
	
	
	
	//�ױ��������пۿ�ɹ� ��õ�ַ�������� ,�̼ҽ����ױ���Ӧ���� 

	public String callback(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// �����ױ��ɹ���Ӧ ��Ӧ��Ϣ ��վ ��ɶ���״̬�޸� 0 --->1 ��������...

		// ��ûص��������� �ױ����͵�

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

		// ���У�� --- �ж��ǲ���֧����˾֪ͨ��

		String hmac = request.getParameter("hmac");// �ױ�����

		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");

		// �Լ����������ݽ��м��� --- �Ƚ�֧����˾������hamc

		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid,

				r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);

		// isValid true �ױ����� false �Ƿ���Ӧ

		if (isValid) {

			// ��Ӧ������Ч

			if (r9_BType.equals("1")) {

				// ������ض��� �޸Ķ���״̬

				OrderDao odao = ImplFactory.getImpl(OrderDao.class);

				odao.payok(r6_Order);// update ....
				
				System.out.println("֧�����");
				// �����ʼ����ͻ� session ��ȡ�û�email
				User user = (User) request.getSession().getAttribute("user");
				String email = user.getEmail();
				if(email == null){
					System.out.println("�ʼ�����ʧ��");
				}else{
					try {
						MailUtils.sendMail(email, "����֧���ɹ�,��ע��������Ϣ");
						System.out.println("�ʼ��������");
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}

			if (r9_BType.equals("2")) {

				// ��������Ե� --- ֧����˾֪ͨ��

				System.out.println("����ɹ���");

				// �޸Ķ���״̬ Ϊ�Ѹ���

				// �ظ�֧����˾

				response.getWriter().print("success");

			}

		} else {

			// ������Ч

			System.out.println("���ݱ��۸ģ�");

		}

	return "redirect:/pay_success.jsp";
}
	
	
	
	/**
	 * ��ʼ���ڶ�����ѯ
	 */
	public String queryOrderByTime(HttpServletRequest request,HttpServletResponse response){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//��֤��½
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "forward:/login.jsp";
		}
		int uid = user.getId();
		//��Ӧ�����
		OrderService os = ImplFactory.getImpl(OrderService.class);
		List<Order> list = os.findOrderListByTime(startTime,endTime,uid);
		request.setAttribute("orderList", list);
		//�������ڿؼ�ʱ��
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return "forward:/orders.jsp";
		
	}
	
/*	*//**
	 * �鿴�ҵ��㼣
	 *//*
	public String productHistory(HttpServletRequest request,HttpServletResponse response){
		OrderService os = ImplFactory.getImpl(OrderService.class);
		List<Order> list = os.findOrderHistory();
		req
		return "forward:/myHistory.jsp";
		
	}*/
	
	/**
	 * 
	 * @��������					:	goodsReturn---->OrderController.java  	
	 * @����						:	�����˻�
	 * @����						:	����(Administrator)
	 * @������˾					:	ˮ֮ˮ��ˮ	
	 * @COPYRIGHT				:	copyright(c) 2018,Rights Reserved
	 * @��������					:	2018��6��6�� ����7:49:20  
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
	 * ��ѯ���ж���
	 */
	public String listOrder(HttpServletRequest request,HttpServletResponse response){
		//��֤��½
				User user = (User) request.getSession().getAttribute("user");
				if(user==null){
					return "forward:/login.jsp";
				}
				//��Ӧ�����
				OrderService os = ImplFactory.getImpl(OrderService.class);
				//����uid��ѯ����
				int uid = user.getId();
				List<Order> list = os.findOrderList(uid);
				//�鿴δ֧�������Ƿ���ڣ���������Сʱδ֧����Ϊ���ڶ���
				Date date = new Date();
				Long time1 = date.getTime();
		 		for(Order order : list){
					if(order.getStatus()==0){
						if((time1-order.getCreatetime().getTime())>(3*60*60*1000)){
							order.setStatus(4);
							//���Ķ������е�statues
							 os.updateOrderStatus(order.getId());
						} 
					}
				}
				request.setAttribute("orderList", list);
				return "forward:/orders.jsp";
	}
}
