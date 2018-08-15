package vip.hht.estore.service;

import java.util.Map;

import vip.hht.estore.beans.Category;

public interface CategoryService {
	/**
	 * ��ҳ��ѯ
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String,Object> getListCategory(int page, int rows);

	/**
	 * �����Ʒ�б�
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * �޸���Ʒ�б�
	 * @param category
	 */
	void editCategory(Category category);

	/**
	 * ɾ����Ʒ�б�
	 * @param category
	 */
	boolean deleteCategoryByIds(String cid);

}
