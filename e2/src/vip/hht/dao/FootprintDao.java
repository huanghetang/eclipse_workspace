package vip.hht.dao;

import java.util.Date;
import java.util.List;

import vip.hht.beans.FootprintItem;



public interface FootprintDao {
	List<FootprintItem> findAllFootprint(int id);

	void addFootprint(String pid, int id, Date date);

	int findFootprintByid(String pid, int uid);

	void updateFootprint(String pid, int uid, Date date);
}
