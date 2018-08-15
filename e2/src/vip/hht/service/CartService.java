package vip.hht.service;

import java.util.List;

public interface CartService {

	List findCartList(int uid);

	void deleteCartById(String uid, String pid);
	/**
	 * 根据联合主键查询列表
	 * @param id
	 * @param pids
	 * @return
	 */
	List findCartModelListById(int id, String[] pids);

	/**
	 * 根据联合主键删除购物车
	 * @param id
	 * @param pids
	 */
	void deleteCartByIds(int id, String[] pids);

	void editPnumByPid(String pnum, String pid,String uid);



}
