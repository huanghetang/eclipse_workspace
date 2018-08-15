package vip.hht.service;

import java.util.List;

import vip.hht.beans.Category;
import vip.hht.beans.PageBean;
import vip.hht.beans.QueryVo;
import vip.hht.beans.User;

public interface AdminService {
	
	/**
	 *管理员登陆
	 */
	User login(QueryVo queryVo);

	
	/**
	 * 分页展示商品分类列表
	 * @return
	 */
	PageBean findAllCategory(int pageNum);


	Category finCategoryById(String id);


	/**
	 * 根据id修改商品类别
	 * @param id
	 */
	void editCategoryById(Category category);

	/**
	 * 根据id删除商品类别
	 * @param id
	 */
	void delCategoryById(String cid);

	/**
	 * 添加商品类别
	 * @param category
	 */
	void addCategory(Category category);
	
	

	

    
    

}
