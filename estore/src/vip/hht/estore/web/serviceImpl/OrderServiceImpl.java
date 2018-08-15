package vip.hht.estore.web.serviceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.hht.estore.beans.Order;
import vip.hht.estore.dao.OrderDao;
import vip.hht.estore.daoImpl.OrderDaoImpl;
import vip.hht.estore.service.OrderService;
public class OrderServiceImpl implements OrderService {
	private OrderDao odao = new OrderDaoImpl();
	@Override
	public Map getListOrder(int page, int rows) throws SQLException {
		 Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Order> list = odao.getListOrder(startIndex, size);
			int totalCounts = odao.getTotalCounts();
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public void updateOrderStatus(String id) {
		odao.updateOrderStatus(id);
	}

	@Override
	public Map listPendingOrder(int page, int rows, int status) {
		 Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Order> list = odao.listPendingOrder(startIndex, size,status);
			int totalCounts = odao.getTotalCounts();
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
}
