package vip.hht.serviceImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utils.JDBCUtils;
import vip.hht.beans.PageBean;
import vip.hht.beans.PageParam;
import vip.hht.beans.Product;
import vip.hht.beans.ProductExample;
import vip.hht.beans.ProductVo;
import vip.hht.mapper.ProductMapper;
import vip.hht.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public List<ProductVo> findAll() {
		//创建查询对象
		ProductExample example = new ProductExample();
		//无查询条件
		List<Product> productList = productMapper.selectByExample(example);
		System.out.println(productList);
		ArrayList<ProductVo> arrayList = new ArrayList<>();
		for (Product product : productList) {
			ProductVo pData = new ProductVo();
			pData.setPname(product.getPname());
			pData.setShop_price(product.getShopPrice().toString());
			pData.setPimage(product.getPimage());
			arrayList.add(pData);
		}
		System.out.println(arrayList);
		return arrayList;
	}

	@Override
	public PageBean findPageList(long pageNum) {
		//计算分页起始位置和每页显示数
		int size = 6;//初始化每页显示为12
		int startIndex = size*((int)pageNum-1);
		//设置查询对象
		PageParam pageParam = new PageParam();
		pageParam.setSize(size);
		pageParam.setStartIndex(startIndex);
		//查询分页数据
		List<Product> porductList = productMapper.select4PageBean(pageParam);
		//查询总条数
		ProductExample example = new ProductExample();
		int total = productMapper.countByExample(example);
		//计算总页数
		int end = total/size;
		if(total%size !=0){
			end++;
		}
		//讲结果包装到PageBean
		PageBean pageBean = new PageBean();
		pageBean.setTotal(total);
		pageBean.setSize(size);
		
		pageBean.setPageNum((int)pageNum);
		pageBean.setEnd(end);
		pageBean.setData(porductList);
		return pageBean;
	}

}
