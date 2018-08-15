package cn.arvin.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.arvin.estore.dao.CategoryDao;
import cn.arvin.estore.domain.Category;
import cn.arvin.estore.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	@Override
	public int totalCount() {
		String sql = "select count(*) from category";
		try {
			Long num = qr.query(sql , new ScalarHandler<Long>());
			return Integer.valueOf(num + "");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询总记录失败");
		}
	}

	@Override
	public List<Category> pageQuery(int page, int rows) {
		String sql = "select * from category limit ?,?";
		int startIndex = (page - 1) * rows;
		try {
			return qr.query(sql, new BeanListHandler<Category>(Category.class), startIndex,rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询失败");
		}
		
	}

	@Override
	public void addCategory(Category cate) {
		String sql = "insert into category values(?,?)";
		try {
			qr.update(sql, cate.getCid(),cate.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加失败");
		}
	}
	
	private QueryRunner run = new QueryRunner();
	@Override
	public void delete(String cid) {
		String sql = "delete from category where cid = ?";
		try {
			run.update(JDBCUtils.getCurrentConnection(), sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除分类失败");
		}
	}

	@Override
	public Category findCategoryByCid(String cid) {
		String sql = "select * from category where cid = ?";
		try {
			return qr.query(sql, new BeanHandler<Category>(Category.class), cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询指定分类失败");
		}
	}

	@Override
	public List<Category> findCategory() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from category";
		try {
			return queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询所有的分类");
		}
	}

	@Override
	public void editCategory(Category category) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql="update category set cname=? where cid=?";
		try {
			queryRunner.update(sql, category.getCname(),category.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
