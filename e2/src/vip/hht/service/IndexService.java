package vip.hht.service;

import java.util.List;

import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;

public interface IndexService {
	/**
	 * 按照价格高低查询广告
	 * @return
	 */
	List<Ad> adList();
	/**
	 * 根据id查找广告详情
	 * @param ad_id
	 * @return
	 */
	Ad findAdInfoById(String ad_id);
	
	/**
	 * 轮播图片
	 * @return
	 */
	List<Bigad> imgList();

}
