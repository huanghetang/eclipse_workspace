package vip.hht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.mapper.ItemsMapper;
import vip.hht.mapper.UserMapper;
import vip.hht.pojo.Items;
import vip.hht.pojo.ItemsExample;
import vip.hht.pojo.ItemsExample.Criteria;
/**
 * /**
 * ʵ����Ʒ��ѯ�б?��mysql��ݿ��ѯ��Ʒ��Ϣ��
 * @author zhoumo
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ItemsMapper itemsMapper;
	@Override
	public List<Items> selectItems() {
		System.out.println("selectItems()---");
		return itemsMapper.selectByExample(null);
	}
	
	public Items selectItems(Integer id){
		return itemsMapper.selectByPrimaryKey(id);
	}
	 
	public void updateItems(Items items){
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}

}
