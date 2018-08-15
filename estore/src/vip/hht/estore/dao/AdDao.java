package vip.hht.estore.dao;

import java.util.List;

import vip.hht.estore.beans.Ad;
import vip.hht.estore.beans.Bigad;
import vip.hht.estore.beans.Category;

public interface AdDao {
	/**
	 * 查找所有的广告
	 * @return
	 */
	List<Ad> findAllAd();
	/**
	 * 分页查询
	 * @param startIndex
	 * @param size
	 * @return
	 */
	List<Ad> findAdByPage(int startIndex, int size);
	/**
	 * 查找广告的总条数
	 * @return
	 */
	int findAdCount();
	/**
	 * 添加广告
	 * @param ad
	 */
	void addAd(Ad ad);
	/**
	 * 删除商品
	 * @param ad_id
	 */
	void delete(int ad_id);
	/**
	 * 分页查找轮播图片信息
	 * @param startIndex
	 * @param size
	 * @return
	 */
	List<Bigad> findImgByPage(int startIndex, int size);
	/**
	 * 查找图片总数
	 * @return
	 */
	int findImgCount();
	/**
	 * 添加轮播图片
	 * @param bigad
	 */
	void addImg(Bigad bigad);
	/**
	 * 删除轮播图片
	 * @param img_id
	 */
	void deleteImg(int img_id);

}
