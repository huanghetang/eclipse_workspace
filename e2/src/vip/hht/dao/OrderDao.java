package vip.hht.dao;

import java.sql.Connection;
import java.util.List;

import vip.hht.beans.Order;
import vip.hht.beans.OrderItemModel;

public interface OrderDao {

	List findListById(String id);

	void addOrder(Connection con, Order order);

	List<Order> findOrderList();

	Order findOrderById(String oid);

	List<OrderItemModel> findOrderItems(String oid);

	public void payok(String id);

	/**
	 * 起始日期查询
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<Order> findOrderListByTime(String startTime, String endTime,int uid);
	//申请退货
	void updateOrderBuId(String id);

	List<Order> findOrderListByUid(int uid);
}
