package cn.arvin.estore.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.arvin.estore.dao.ProductDao;
import cn.arvin.estore.dao.impl.ProductDaoImpl;
import cn.arvin.estore.domain.Product;
import cn.arvin.estore.service.ProductService;
import cn.arvin.estore.utils.JDBCUtils;

public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public void findAll(List<Product> list) {
		productDao.findAll(list);
	}

	@Override
	public Product findProductByPid(String pid) {
		return productDao.findProductByPid(pid);
	}

	@Override
	public void updateByOrderItem(String pid, int buynum) {
		//查询商品库存
		Product product = productDao.findPnumByPid(pid);
		if (product.getPnum() < buynum) {
			throw new RuntimeException("库存不足,余量:" + product.getPnum() + "您购买的的数量："+ buynum);
		}
		
		productDao.minusPNum(pid,buynum);
	}

	@Override
	public void productList(int page1, int rows1, HashMap<String, Object> map) {
			//每页显示数
			int size = rows1;
			//获取起始索引
			int startIndex = size*(page1-1);
			//查询
			List<Product> productList = productDao.productList(startIndex,size);
			long totalCounts = productDao.totalCounts();
			//赋值
			map.put("total", totalCounts);
			map.put("rows", productList);
		}

	@Override
	public boolean addProduct(Product product) {
		
		return productDao.addProduct(product);
	}

	@Override
	public boolean updateProduct(Product p) {
		return productDao.updateProduct(p);
	}

	@Override
	public void deleteProduct(String[] pid) {
		try {
			//开启事务
			JDBCUtils.startTransaction();
			for (String pid_value : pid) {
				
				productDao.delete(pid_value);
			}
			//提交事务
			JDBCUtils.commitTransaction();
		} catch (Exception e) {
			//抓取异常
			e.printStackTrace();
			//异常回滚
			JDBCUtils.rollBack();
		}finally{
			//释放资源
			JDBCUtils.release();
		}
		
	}

}
