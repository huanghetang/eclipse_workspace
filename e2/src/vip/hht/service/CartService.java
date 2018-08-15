package vip.hht.service;

import java.util.List;

public interface CartService {

	List findCartList(int uid);

	void deleteCartById(String uid, String pid);
	/**
	 * ��������������ѯ�б�
	 * @param id
	 * @param pids
	 * @return
	 */
	List findCartModelListById(int id, String[] pids);

	/**
	 * ������������ɾ�����ﳵ
	 * @param id
	 * @param pids
	 */
	void deleteCartByIds(int id, String[] pids);

	void editPnumByPid(String pnum, String pid,String uid);



}
