package vip.hht.service;

import java.util.List;

import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.beans.ProductVo;

public interface ProductService {
	/**
	 * 查询全部商品
	 */
	List<ProductVo> findAll();

	
	/**
	 * 分页查询商品列表
	 * @param pageNum
	 * @return
	 */
	PageBean findPageList(long pageNum);

}
