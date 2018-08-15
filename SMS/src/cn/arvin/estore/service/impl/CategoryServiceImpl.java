package cn.arvin.estore.service.impl;

import java.util.List;
import java.util.Map;

import cn.arvin.estore.dao.CategoryDao;
import cn.arvin.estore.dao.ProductDao;
import cn.arvin.estore.dao.impl.CategoryDaoImpl;
import cn.arvin.estore.dao.impl.ProductDaoImpl;
import cn.arvin.estore.domain.Category;
import cn.arvin.estore.service.CategoryService;
import cn.arvin.estore.utils.JDBCUtils;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao categoryDao = new CategoryDaoImpl();
	private ProductDao productDao = new ProductDaoImpl();
	
	@Override
	public void pageQuery(Map<String, Object> map, int page, int rows) {
		int total = categoryDao.totalCount();
		List<Category> list = categoryDao.pageQuery(page,rows);
		map.put("total", total);
		map.put("rows",list);
	}

	@Override
	public void addCategory(Category cate) {
		categoryDao.addCategory(cate);
	}

	@Override
	public void deleteCategory(String[] cid) {
		
		try {
			//开启事务
			JDBCUtils.startTransaction();
			for (String cid_value : cid) {
				//解除外键
				productDao.release(cid_value);
				//删除分类
				categoryDao.delete(cid_value);
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

	@Override
	public Category findCategoryByCid(String cid) {
		return categoryDao.findCategoryByCid(cid);
	}

	@Override
	public List<Category> findCategory() {
		return categoryDao.findCategory();
	}

	@Override
	public void editCategory(Category category) {
		categoryDao.editCategory(category);
	}

}
