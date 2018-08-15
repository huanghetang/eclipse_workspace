package vip.hht.serviceImpl;

import java.util.Date;
import java.util.List;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.Footprint;
import vip.hht.beans.FootprintItem;
import vip.hht.dao.FootprintDao;
import vip.hht.service.FootprintService;



public class FootprintServiceImpl implements FootprintService {
	
	private FootprintDao dao = ImplFactory.getImpl(FootprintDao.class);
	@Override
	
	
	public List<FootprintItem> findAllFootprint(int id) {
		return dao.findAllFootprint(id);
	}
	
	
	@Override
	public void setFootprint(String pid, int uid,Date date) {
		int i = dao.findFootprintByid(pid,uid);
		
		if(i==0){
			dao.addFootprint(pid,uid,date);
		}else{
			dao.updateFootprint(pid,uid,date);
		}
		
	}
	@Override
	public void setFootprints(List<Footprint> footlist, int uid) {
		
			for (Footprint footprint : footlist) {
				
				String pid = footprint.getPid();
				Date lastTime = footprint.getLastTime();
				this.setFootprint(pid,uid,lastTime);
		}
	}
		
}
	

