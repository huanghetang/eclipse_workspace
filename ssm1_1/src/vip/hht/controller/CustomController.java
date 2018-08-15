package vip.hht.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vip.hht.pojo.BaseDict;
import vip.hht.pojo.Customer;
import vip.hht.pojo.QueryVo;
import vip.hht.service.CustomerService;
import vip.hht.utils.Page;

@Controller
@RequestMapping(value="customer")
public class CustomController {
	@Autowired
	private CustomerService cs;
	
	
	//客户来源
	@Value("${CUSTOMER_FROM_TYPE}")//配置文件被spring读取,直接从容器中读取,解决硬编码问题
	private String CUSTOMER_FROM_TYPE;
	//所属行业
	@Value("${CUSTOMER_INDUSTRY_TYPE}")
	private String CUSTOMER_INDUSTRY_TYPE;
	//客户级别
	@Value("${CUSTOMER_LEVEL_TYPE}")
	private String CUSTOMER_LEVEL_TYPE;
	
	
	
	@RequestMapping(value="list")
	public String queryList(QueryVo vo,Model model){
		List<BaseDict> fromType = cs.queryDictByType(CUSTOMER_FROM_TYPE);
		List<BaseDict> industryType = cs.queryDictByType(CUSTOMER_INDUSTRY_TYPE);
		List<BaseDict> levelType = cs.queryDictByType(CUSTOMER_LEVEL_TYPE);
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		Page<Customer> page = cs.queryByVo(vo);
		model.addAttribute("page", page);
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		
		return "customer";
	}
	
	@RequestMapping(value="edit")
	public @ResponseBody Customer goEdit(Integer id){
		Customer customer = cs.queryCustomerById(id);
		return customer;
	}
	
	@RequestMapping(value="update")
	public @ResponseBody String  Edit(Customer customer ,Model model){
		cs.updateCustomer(customer);
		return "update success";
	}
	
	@RequestMapping(value="delete")
	@ResponseBody//返回值一般是"OK" 但是测试结果 delete/delete success/de 都可以 猜测啥都行
	public String deleteCustomerById(Integer id){
		cs.deleteCustomerById(id);
		return "de";
	}

}
