package vip.hht.estore.web.serviceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.hht.estore.beans.Category;
import vip.hht.estore.dao.CategoryDao;
import vip.hht.estore.daoImpl.CategoryDaoImpl;
import vip.hht.estore.service.CategoryService;

/**
 * 商品分类
 * @author zhoumo
 *
 */
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao cdao = new CategoryDaoImpl();

	
	@Override
	public Map<String,Object> getListCategory(int page, int rows){
		 Map<String,Object> map = new HashMap<String, Object>();
		 int size = rows;
		 int startIndex = size*(page-1);
		try {
			List<Category> list = cdao.getListCategory(startIndex,size);
			int totalCounts = cdao.getTotalCounts();
			map.put("total", totalCounts);
			map.put("rows", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}


	@Override
	public void addCategory(Category category) {
		cdao.addCategory(category);
		
	}


	@Override
	public void editCategory(Category category) {
		cdao.editCategory(category);
	}


	@Override
	public boolean deleteCategoryByIds(String cid) {
		return cdao.deleteCategoryByIds(cid);
		
	}

}
