package cn.arvin.estore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.arvin.estore.dao.AdDao;
import cn.arvin.estore.dao.impl.AdDaoImpl;
import cn.arvin.estore.domain.Ad;
import cn.arvin.estore.domain.Bigad;
import cn.arvin.estore.service.AdService;


public class AdServiceImpl implements AdService {
	private AdDao dao = new AdDaoImpl();
	
	
	@Override
	public List<Ad> findAllAd() {
		
		return dao.findAllAd();
	}


	@Override
	public Map findAdByPage(int page, int rows) {
		 Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Ad> list = dao.findAdByPage(startIndex,size);
			int totalCounts = dao.findAdCount();
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	@Override
	public void addAd(Ad ad) {
		dao.addAd(ad);
	}


	@Override
	public void delete(int ad_id) {
		dao.delete(ad_id);
	}


	@Override
	public Map findImgByPage(int page, int rows) {
		Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Bigad> list = dao.findImgByPage(startIndex,size);
			int totalCounts = dao.findImgCount();
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	@Override
	public void addImg(Bigad bigad) {
		dao.addImg(bigad);
	}


	@Override
	public void deleteImg(int img_id) {
		dao.deleteImg(img_id);
	}

}
