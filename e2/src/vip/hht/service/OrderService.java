package vip.hht.service;

import java.util.List;

import vip.hht.beans.MyComment;
import vip.hht.beans.Order;
import vip.hht.beans.OrderItemModel;
import vip.hht.beans.PageBean;

public interface OrderService {

	List findListById(String id);

	/**
	 * ��ѯ��Ӧ�Ĺ��ﳵ��Ϣ
	 * @param pids
	 * @return
	 */
	List findOrderItemList(String[] pids);

	void saveOrderItems(Order order);

	List<Order> findOrderList(int uid);

	Order findOrderById(String oid);

	List<OrderItemModel> findOrderDetail(String oid);

	/**
	 * ��ʼ���ڶ�����ѯ
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Order> findOrderListByTime(String startTime, String endTime,int uid);
	
	//�����˻�
	void updateOrderBuId(String oid);
	
	void addComment(String oid,int uid, String pid, String level, String comment);


	List<Order> findOrderListByUid(int uid);
	
	public PageBean findCommentByPid(String pid,int pageNum,int size);
	
	void updateOrderStatus(String id);

}
