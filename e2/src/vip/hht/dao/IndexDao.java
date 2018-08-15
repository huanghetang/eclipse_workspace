package vip.hht.dao;

import java.util.List;

import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;

public interface IndexDao {
	/**
	 * ���ݼ۸�ߵͲ�ѯ���
	 * @return
	 */
	List<Ad> adList();
	
	/**
	 * ����id��ѯ�������
	 * @param ad_id
	 * @return
	 */
	Ad findAdInfoById(String ad_id);
	
	/**
	 * �ֲ�ͼƬ
	 * @return
	 */
	List<Bigad> imgList();



}
