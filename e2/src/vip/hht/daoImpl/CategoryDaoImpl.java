package vip.hht.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.Category;
import vip.hht.beans.Product;
import vip.hht.dao.CategoryDao;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public Category findCategoryById(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from category where cid =?";
		try {
			return	queryRunner.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
