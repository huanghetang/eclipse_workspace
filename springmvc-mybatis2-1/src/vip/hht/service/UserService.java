package vip.hht.service;

import java.util.List;

import vip.hht.pojo.Items;


public interface UserService {

	List<Items> selectItems();
	
	Items selectItems(Integer id);
	
	 void updateItems(Items items);
	
}
