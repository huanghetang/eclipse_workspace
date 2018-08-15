package vip.hht.service;

import java.util.Date;
import java.util.List;

import vip.hht.beans.Footprint;
import vip.hht.beans.FootprintItem;



public interface FootprintService {
	List<FootprintItem> findAllFootprint(int uid);

	void setFootprint(String pid, int uid,Date date);

	void setFootprints(List<Footprint> footlist, int id);
}
