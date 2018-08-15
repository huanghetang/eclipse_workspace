package vip.hht.estore.dao;

import java.sql.SQLException;
import java.util.List;

import vip.hht.estore.beans.Category;

public interface CategoryDao {
	
	/**
	 * ��ҳ��ѯ
	 * @param startIndex
	 * @param size
	 * @return
	 * @throws SQLException
	 */
	List<Category> getListCategory(int startIndex, int size) throws SQLException;
	
	/**
	 * ��ѯ������
	 * @return
	 * @throws SQLException
	 */
	public int getTotalCounts() throws SQLException;

	/**
	 * �����Ʒ���
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * �޸���Ʒ���
	 * @param category
	 */
	void editCategory(Category category);

	/**
	 * ����idɾ����Ʒ���
	 * @param category
	 */
	boolean deleteCategoryByIds(String cid);

}
