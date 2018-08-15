package cn.arvin.estore.service;

import java.util.List;
import java.util.Map;

import cn.arvin.estore.domain.Ad;
import cn.arvin.estore.domain.Bigad;


public interface AdService {
	/**
	 * �������еĹ���б�
	 * @return
	 */
	List<Ad> findAllAd();
	/**
	 * ��ҳ��ѯ����
	 * @param page
	 * @param rows
	 * @return
	 */
	Map findAdByPage(int page, int rows);
	/**
	 * ��ӹ��
	 * @param ad
	 */
	void addAd(Ad ad);
	/**
	 * ɾ�����
	 * @param ad_id
	 */
	void delete(int ad_id);
	/**
	 * ��ҳ�����ֲ�ͼƬ
	 * @param page
	 * @param rows
	 * @return
	 */
	Map findImgByPage(int page, int rows);
	/**
	 * ����ֲ�ͼ
	 * @param bigad
	 */
	void addImg(Bigad bigad);
	/**
	 * ɾ���ֲ�ͼƬ
	 * @param img_id
	 */
	void deleteImg(int img_id);

}
