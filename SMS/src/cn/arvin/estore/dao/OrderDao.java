package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.Order;
import cn.arvin.estore.domain.User;

/**
 * @author Administrator
 *
 */
public interface OrderDao {

	/**
	 * 创建订单
	 * @param order
	 */
	void save(Order order);

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
	 * 修改订单状态
	 * @param oid
	 */
	void paysucess(String oid);

	/**
	 * 
	 * 查询所有订单数量
	 * @return
	 */
	int totalCount();
	
	
	/**
	 * 查后台订单
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Order> pageQuery(int page, int rows);

	List<Order> listPendingOrder(int startIndex, int size, int status);

	int getTotalCounts(int status);

	void pass(String id_value);

}
