package vip.hht.estore.service;

import java.sql.SQLException;
import java.util.Map;

public interface OrderService {

	Map getListOrder(int page1, int rows1) throws SQLException ;

	void updateOrderStatus(String id);

	Map listPendingOrder(int page1, int rows1, int status);

}
