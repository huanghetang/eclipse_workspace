package vip.hht.serviceImpl;

import java.util.List;

import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;
import vip.hht.dao.IndexDao;
import vip.hht.daoImpl.IndexDaoImpl;
import vip.hht.service.IndexService;

public class IndexServiceImpl implements IndexService {
	private IndexDao dao=new IndexDaoImpl();
	@Override
	public List<Ad> adList() {
		
		return dao.adList();
	}

	@Override
	public Ad findAdInfoById(String ad_id) {
		
		return dao.findAdInfoById(ad_id);
	}
	
	@Override
	public List<Bigad> imgList() {
		return dao.imgList();
	}

}
