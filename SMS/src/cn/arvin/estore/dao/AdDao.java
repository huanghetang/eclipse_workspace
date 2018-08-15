package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.Ad;
import cn.arvin.estore.domain.Bigad;


public interface AdDao {
	/**
	 * �������еĹ��
	 * @return
	 */
	List<Ad> findAllAd();
	/**
	 * ��ҳ��ѯ
	 * @param startIndex
	 * @param size
	 * @return
	 */
	List<Ad> findAdByPage(int startIndex, int size);
	/**
	 * ���ҹ���������
	 * @return
	 */
	int findAdCount();
	/**
	 * ��ӹ��
	 * @param ad
	 */
	void addAd(Ad ad);
	/**
	 * ɾ����Ʒ
	 * @param ad_id
	 */
	void delete(int ad_id);
	/**
	 * ��ҳ�����ֲ�ͼƬ��Ϣ
	 * @param startIndex
	 * @param size
	 * @return
	 */
	List<Bigad> findImgByPage(int startIndex, int size);
	/**
	 * ����ͼƬ����
	 * @return
	 */
	int findImgCount();
	/**
	 * ����ֲ�ͼƬ
	 * @param bigad
	 */
	void addImg(Bigad bigad);
	/**
	 * ɾ���ֲ�ͼƬ
	 * @param img_id
	 */
	void deleteImg(int img_id);

}
