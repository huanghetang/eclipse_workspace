package vip.hht.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.mapper.BaseDictMapper;
import vip.hht.pojo.BaseDict;
import vip.hht.pojo.Customer;
import vip.hht.pojo.QueryVo;
import vip.hht.utils.Page;

@Service
public class CustomServiceImpl implements CustomerService {
	@Autowired
	private BaseDictMapper dao;
	public List<BaseDict> queryDictByType(String codeType){
		return dao.queryDictByType(codeType);
	}
	
	public Page<Customer> queryByVo(QueryVo vo){
		Page<Customer> page = new Page<Customer>();
//		private int total;//符和查询条件的总条数
//		private int page;//当前页
//		private int size;//每页显示多少条
//	    private List<T> rows;//分页查询的结果集
		//设置分页对象每页显示5条
		page.setSize(5);
		//设置查询条件每页显示5条
		vo.setSize(5);
		if(vo!=null){
			//设置当前页
			if(vo.getPage()!=null){
				page.setPage(vo.getPage());
				//设置查询条件开始行
				vo.setStartRow((page.getPage()-1)*page.getSize());
			}
			//把查询条件去空格 客户姓名
			if(vo.getCustName()!=null){
				vo.setCustName(vo.getCustName().trim());
			}
		}
		List<Customer> rows = dao.queryByVo(vo);
		//设置分页结果集
		page.setRows(rows);
		Integer total = dao.queryCountByVo(vo);
		//设置条件查询总条数
		page.setTotal(total);
		return page;
		
	}

	@Override
	public Customer queryCustomerById(Integer id) {
		return dao.queryCustomerById(id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		dao.updateCustomer(customer);
		
	}

	@Override
	public void deleteCustomerById(Integer id) {
		dao.deleteCustomerById(id);
		
	}
	
	
}
