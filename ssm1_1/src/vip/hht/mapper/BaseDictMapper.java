package vip.hht.mapper;

import java.util.List;

import vip.hht.pojo.BaseDict;
import vip.hht.pojo.Customer;
import vip.hht.pojo.QueryVo;


public interface BaseDictMapper {
	
	List<BaseDict> queryDictByType(String codeType);

	List<Customer> queryByVo(QueryVo vo);

	Integer queryCountByVo(QueryVo vo);
	
	Customer queryCustomerById(Integer id);

	void updateCustomer(Customer customer);

	void deleteCustomerById(Integer id);

}
