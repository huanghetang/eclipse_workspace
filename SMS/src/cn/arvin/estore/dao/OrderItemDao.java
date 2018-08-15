package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.OrderItem;

public interface OrderItemDao {

	/**
	 * 创建订单商品关系记录
	 * @param item
	 */
	void save(OrderItem item);

	/**
	 * 获取指定订单的商品信息
	 * @param oid
	 * @return
	 */
	List<OrderItem> findOrderByOid(String oid);

}
