package vip.hht.dao;

import java.sql.Connection;

import vip.hht.beans.OrderItem;

public interface OrderItemsDao {

	void add(Connection con,OrderItem item);
	
	void setIsComment(String oid, String pid);

}
