package vip.hht.estore.dao;

import java.util.List;

import vip.hht.estore.beans.Order;

public interface OrderDao {

	List<Order> getListOrder(int startIndex, int size);

	int getTotalCounts();

	void updateOrderStatus(String id);

	List<Order> listPendingOrder(int startIndex, int size, int status);

}
