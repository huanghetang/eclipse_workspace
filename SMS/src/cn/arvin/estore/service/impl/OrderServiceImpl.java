package cn.arvin.estore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.arvin.estore.dao.CartDao;
import cn.arvin.estore.dao.OrderDao;
import cn.arvin.estore.dao.OrderItemDao;
import cn.arvin.estore.domain.Category;
import cn.arvin.estore.domain.Order;
import cn.arvin.estore.domain.OrderItem;
import cn.arvin.estore.domain.User;
import cn.arvin.estore.factory.BeanFactory;
import cn.arvin.estore.service.OrderService;
import cn.arvin.estore.service.ProductService;
import cn.arvin.estore.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = BeanFactory.getInstance(OrderDao.class);
	private OrderItemDao orderItemDao = BeanFactory.getInstance(OrderItemDao.class);
	private ProductService productService = BeanFactory.getInstance(ProductService.class);
	private CartDao cartDao = BeanFactory.getInstance(CartDao.class);
	
	@Override
	public void saveOrder(Order order) {
		//开启事务
		try {
			JDBCUtils.startTransaction();
			//处理业务
			//order的insert操作
			orderDao.save(order);
			//orderitems的循环insert操作
			
			List<OrderItem> Items = order.getOrderItems();
			for (OrderItem Item : Items) {
				orderItemDao.save(Item);
				//product的库存业务操作
				productService.updateByOrderItem(Item.getPid(),Item.getBuynum());
			}
			
			//cart的delete操作
			cartDao.deleteCartByUid(order.getUid());
			//提交事务
			JDBCUtils.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			//异常回滚
			JDBCUtils.rollBack();
		}finally {
			JDBCUtils.release();
		}
	}

	@Override
	public void orderList(User loginUser, List<Order> ordersList) {
		orderDao.orderList(loginUser,ordersList);
	}

	@Override
	public Order findOrderByOid(String oid) {
		//获取订单的基本信息
		Order order = orderDao.findOrderByOid(oid);
		
		//调用orderItemDao获取层商品信息
		List<OrderItem> Items= orderItemDao.findOrderByOid(oid);
		order.setOrderItems(Items);
		
		return order;
	}

	@Override
	public void paysucess(String oid) {
		orderDao.paysucess(oid);
	}

	@Override
	public void pageQuery(Map<String, Object> map, int page, int rows) {
		int total = orderDao.totalCount();
		List<Order> list = orderDao.pageQuery(page,rows);
		map.put("total", total);
		map.put("rows",list);
	}

	@Override
	public Map listPendingOrder(int page, int rows, int status) {
		 Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Order> list = orderDao.listPendingOrder(startIndex, size,status);
			int totalCounts = orderDao.getTotalCounts(status);
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public void pass(String[] id) {
		try {
			//开启事务
			JDBCUtils.startTransaction();
			
			for (String id_value : id) {
				//批量修改订单
				orderDao.pass(id_value);
			}
			//提交事务
			JDBCUtils.commitTransaction();
		} catch (Exception e) {
			//抓取异常
			e.printStackTrace();
			//异常回滚
			JDBCUtils.rollBack();
		}finally{
			//释放资源
			JDBCUtils.release();
		}
	}
}
