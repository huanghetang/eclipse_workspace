package mapper;

import java.util.List;

import pojo.Order;

public interface OrderMapper {
	/**
	 * 查询订单表order的所有数据
	 */
	public abstract List<Order> findOrders();
	
	/**
	 * 查询所有订单信息，关联查询下单用户信息。1对1关联
	 */
	List<Order> findOrder();
}
