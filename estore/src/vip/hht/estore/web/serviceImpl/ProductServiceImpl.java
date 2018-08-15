package vip.hht.estore.web.serviceImpl;

import java.util.HashMap;
import java.util.List;

import vip.hht.estore.beans.Product;
import vip.hht.estore.dao.ProductDao;
import vip.hht.estore.daoImpl.ProductDaoImpl;
import vip.hht.estore.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private ProductDao pd = new ProductDaoImpl();
	public void productList(int page1, int rows1, HashMap<String, Object> map){
		//每页显示数
		int size = rows1;
		//获取起始索引
		int startIndex = size*(page1-1);
		//查询
		List<Product> productList = pd.productList(startIndex,size);
		long totalCounts = pd.totalCounts();
		//赋值
		map.put("total", totalCounts);
		map.put("rows", productList);
	}
	@Override
	public boolean addProduct(Product product) {
		pd.addProduct(product);
		
		
		return false;
	}

}
