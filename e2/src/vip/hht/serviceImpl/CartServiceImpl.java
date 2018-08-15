package vip.hht.serviceImpl;

import java.util.List;

import vip.hht.Tools.ImplFactory;
import vip.hht.beans.CartModel;
import vip.hht.dao.CartDao;
import vip.hht.service.CartService;

public class CartServiceImpl implements CartService {
	private CartDao cdao = ImplFactory.getImpl(CartDao.class);
	@Override
	public List<CartModel> findCartList(int uid) {
		//��ѯÿһ�����ﳵ��Ϣ�е���Ʒ��Ϣ,�͹�������,�۸�
		List<CartModel> list = cdao.findList(uid);
		return list;
	}
	@Override
	public void deleteCartById(String uid, String pid) {
		cdao.deleteCartById(uid,pid);
	}
	
	@Override
	public List findCartModelListById(int id, String[] pids) {
		List list = cdao.findCartModelListById(id,pids);
		return list;
	}
	@Override
	public void deleteCartByIds(int id, String[] pids) {
		cdao.deleteCartByIds(id,pids);
	}
	@Override
	public void editPnumByPid(String pnum, String pid,String uid) {
		cdao.editPnumByPid(pnum,pid,uid);
		
	}

}
