package vip.hht.dao;

import java.sql.Connection;
import java.util.List;

import vip.hht.beans.Cart;
import vip.hht.beans.CartModel;

public interface CartDao {

	Cart findCartById(String pid, String cid);

	void saveCart(String pid, String cid, String buynum);

	void incrCart(String pid, String cid, int buynum);

	List<CartModel> findList(int uid);

	void deleteCartById(String uid, String pid);
	
	
	/**
	 * ��ѯ���ﳵ�б�
	 * @param id
	 * @param pids
	 */
	List<CartModel> findCartModelListById(int id, String[] pids);
	
	/**
	 * ɾ����Ʒ�б�
	 * @param id
	 * @param pids
	 */
	void deleteCartByIds(int id, String[] pids);

	List findOrderItemList(String[] pids);

	void deleteCartByIds(Connection con, Integer uid, String pid);

	void editPnumByPid(String pnum, String pid,String uid);

}
