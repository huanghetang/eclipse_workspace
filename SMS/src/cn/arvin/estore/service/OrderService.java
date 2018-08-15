package cn.arvin.estore.service;

import java.util.List;
import java.util.Map;

import cn.arvin.estore.domain.Order;
import cn.arvin.estore.domain.User;

public interface OrderService {

	/**
	 * 创建订单
	 * @param order 
	 */
	void saveOrder(Order order);

	/**
	 * 获取订单列表
	 * @param loginUser
	 * @param ordersList
	 */
	void orderList(User loginUser, List<Order> ordersList);

	/**
	 * 获取订单详细信息
	 * @param oid
	 * @return
	 */
	Order findOrderByOid(String oid);

	/**
	 * 支付成功修改订单状态
	 * @param r6_Order
	 */
	void paysucess(String oid);

	/**
	 * 后台查询订单
	 * @param map
	 * @param page
	 * @param rows
	 */
	void pageQuery(Map<String, Object> map, int page, int rows);

	Map listPendingOrder(int page1, int rows1, int status);

	void pass(String[] id);

}
