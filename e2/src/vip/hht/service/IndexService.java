package vip.hht.service;

import java.util.List;

import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;

public interface IndexService {
	/**
	 * ���ռ۸�ߵͲ�ѯ���
	 * @return
	 */
	List<Ad> adList();
	/**
	 * ����id���ҹ������
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
