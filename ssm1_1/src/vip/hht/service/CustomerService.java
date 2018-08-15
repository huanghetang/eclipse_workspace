package vip.hht.service;

import java.util.List;

import vip.hht.pojo.BaseDict;
import vip.hht.pojo.Customer;
import vip.hht.pojo.QueryVo;
import vip.hht.utils.Page;

public interface CustomerService {
	public List<BaseDict> queryDictByType(String codeType);
	
	public Page<Customer> queryByVo(QueryVo vo);
	
	public Customer queryCustomerById(Integer id);

	public void updateCustomer(Customer customer);

	public void deleteCustomerById(Integer id);
	
	

}
