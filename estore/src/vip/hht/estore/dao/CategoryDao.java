package vip.hht.estore.dao;

import java.sql.SQLException;
import java.util.List;

import vip.hht.estore.beans.Category;

public interface CategoryDao {
	
	/**
	 * 分页查询
	 * @param startIndex
	 * @param size
	 * @return
	 * @throws SQLException
	 */
	List<Category> getListCategory(int startIndex, int size) throws SQLException;
	
	/**
	 * 查询总条数
	 * @return
	 * @throws SQLException
	 */
	public int getTotalCounts() throws SQLException;

	/**
	 * 添加商品类别
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * 修改商品类别
	 * @param category
	 */
	void editCategory(Category category);

	/**
	 * 根据id删除商品类别
	 * @param category
	 */
	boolean deleteCategoryByIds(String cid);

}
