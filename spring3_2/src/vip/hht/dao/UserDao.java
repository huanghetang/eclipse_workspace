package vip.hht.dao;

public interface UserDao {
	
	void increaseMoney(Integer from,Integer to,double money);
	void decreaseMoney(Integer from,Integer to,double money);

}
