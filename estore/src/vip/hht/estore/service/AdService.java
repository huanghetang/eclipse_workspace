package vip.hht.estore.service;

import java.util.List;
import java.util.Map;

import vip.hht.estore.beans.Ad;
import vip.hht.estore.beans.Bigad;

public interface AdService {
	/**
	 * 查找所有的广告列表
	 * @return
	 */
	List<Ad> findAllAd();
	/**
	 * 分页查询数据
	 * @param page
	 * @param rows
	 * @return
	 */
	Map findAdByPage(int page, int rows);
	/**
	 * 添加广告
	 * @param ad
	 */
	void addAd(Ad ad);
	/**
	 * 删除广告
	 * @param ad_id
	 */
	void delete(int ad_id);
	/**
	 * 分页查找轮播图片
	 * @param page
	 * @param rows
	 * @return
	 */
	Map findImgByPage(int page, int rows);
	/**
	 * 添加轮播图
	 * @param bigad
	 */
	void addImg(Bigad bigad);
	/**
	 * 删除轮播图片
	 * @param img_id
	 */
	void deleteImg(int img_id);

}
