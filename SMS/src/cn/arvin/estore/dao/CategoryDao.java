package cn.arvin.estore.dao;

import java.util.List;

import cn.arvin.estore.domain.Category;

/**
 * @author Administrator
 *
 */
public interface CategoryDao {

	/**
	 * 总记录数
	 * @return
	 */
	int totalCount();

	/**
	 * 分类记录
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Category> pageQuery(int page, int rows);

	/**
	 * 添加分类
	 * @param cate
	 */
	void addCategory(Category cate);

	/**
	 * 删除分类
	 * @param cid
	 */
	void delete(String cid);
	
	/**
	 * 查询分类
	 * @param cid
	 * @return
	 */
	Category findCategoryByCid(String cid);

	List<Category> findCategory();

	void editCategory(Category category);

}
