package vip.hht.estore.service;

import java.util.Map;

import vip.hht.estore.beans.Category;

public interface CategoryService {
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String,Object> getListCategory(int page, int rows);

	/**
	 * 添加商品列表
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * 修改商品列表
	 * @param category
	 */
	void editCategory(Category category);

	/**
	 * 删除商品列表
	 * @param category
	 */
	boolean deleteCategoryByIds(String cid);

}
