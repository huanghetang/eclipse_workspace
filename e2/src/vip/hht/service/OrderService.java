package vip.hht.service;

import java.util.List;

import vip.hht.beans.MyComment;
import vip.hht.beans.Order;
import vip.hht.beans.OrderItemModel;
import vip.hht.beans.PageBean;

public interface OrderService {

	List findListById(String id);

	/**
	 * 查询对应的购物车信息
	 * @param pids
	 * @return
	 */
	List findOrderItemList(String[] pids);

	void saveOrderItems(Order order);

	List<Order> findOrderList(int uid);

	Order findOrderById(String oid);

	List<OrderItemModel> findOrderDetail(String oid);

	/**
	 * 起始日期订单查询
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Order> findOrderListByTime(String startTime, String endTime,int uid);
	
	//申请退货
	void updateOrderBuId(String oid);
	
	void addComment(String oid,int uid, String pid, String level, String comment);


	List<Order> findOrderListByUid(int uid);
	
	public PageBean findCommentByPid(String pid,int pageNum,int size);
	
	void updateOrderStatus(String id);

}
