package cn.arvin.estore.service;

import java.util.List;
import java.util.Map;

import cn.arvin.estore.domain.Category;

public interface CategoryService {

	/**
	 * 分页查询
	 * @param map
	 * @param page
	 * @param rows
	 */
	void pageQuery(Map<String, Object> map, int page, int rows);

	/**
	 * 添加分类
	 * @param cate 
	 */
	void addCategory(Category cate);

	/**
	 * 删除分类
	 * @param cid
	 */
	void deleteCategory(String[] cid);

	/**
	 * 查询分类
	 * @param cid
	 * @return
	 */
	Category findCategoryByCid(String cid);

	List<Category> findCategory();

	void editCategory(Category category);

}
