package vip.hht.dao;

import java.sql.Connection;
import java.util.List;

import vip.hht.beans.Product;

public interface ProductDao {

	List findProductList();

	Product findProductById(String pid);

	int findTotalCount();

	List finPageList(int startIndex, int size1);

	void updatePnumByPid(Connection con, String pid, int i);
	


}
