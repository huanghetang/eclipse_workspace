package vip.hht.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vip.hht.beans.PageBean;
import vip.hht.beans.PageParam;
import vip.hht.beans.Product;
import vip.hht.beans.ProductExample;
import vip.hht.beans.ProductExample.Criteria;
import vip.hht.mapper.ProductMapper;
import vip.hht.service.AdminProductServcie;

@Service
public class AdminProductServcieImpl implements AdminProductServcie {
	@Autowired
	private ProductMapper productMapper;
	
	
	@Override
	public PageBean findPageProduct(int pageNum) {
		//分页所需要的数据 1,起始页,2,每页数
		int size = 6;//初始化每页显示12条数据
		int startIndex = size*(pageNum-1);
		//创建查询的入参对象
		PageParam pageParam = new PageParam();
		pageParam.setSize(size);
		pageParam.setStartIndex(startIndex);
		//执行分页查询
		List<Product> productList = productMapper.select4PageBean(pageParam);
		//查询总条数
		ProductExample example = new ProductExample();
		int total = productMapper.countByExample(example);
		//创建分页对象PageBean
		PageBean pageBean = new PageBean();
		//初始化pageBean并返回
		pageBean.setTotal(total);
		pageBean.setSize(size);
		pageBean.setPageNum(pageNum);
		
		//计算总页数
		int end = total%size ==0?(total/size):(total/size +1);
		pageBean.setEnd(end);
		pageBean.setData(productList);
		
		return pageBean;
	}


	@Override
	public Product findProductById(String pid) {
		Product product = productMapper.selectByPrimaryKey(pid);
		return product;
	}


	@Override
	public void editProduct(Product product) {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(product.getPid());
		int update = productMapper.updateByExample(product, example);
		//当页面上的值不为Null再修改值
//		int update = productMapper.updateByExampleSelective(product, example);
		String str = update==1?"成功":"失败";
		System.out.println("修改商品"+str);
	}


	@Override
	public void addProduct(Product product) {
		int insert = productMapper.insertSelective(product);
		String str = insert==1?"成功":"失败";
		System.out.println("新增商品"+str);
		
		
	}


	@Override
	public void deleteById(String pid) {
		//直接删除商品
		int del = productMapper.deleteByPrimaryKey(pid);
		String str = del==1?"成功":"失败";
		System.out.println("删除商品"+str);
	}


	@Override
	public void banchDel(Integer[] pids) {
		 productMapper.batchDel(pids);
		 System.out.println("sdfsdf");
		
	}

}
