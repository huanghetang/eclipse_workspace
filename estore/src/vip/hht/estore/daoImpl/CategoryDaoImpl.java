package vip.hht.estore.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.estore.beans.Category;
import vip.hht.estore.dao.CategoryDao;
import vip.hht.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> getListCategory(int startIndex, int size) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select cid,cname from category limit ?,?";
		List<Category>	list  = queryRunner.query(sql, new BeanListHandler<Category>(Category.class), startIndex,size);
		return list;
		
		
	}

	public int getTotalCounts() throws SQLException{
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(1) from category";
		Long total = queryRunner.query(sql, new ScalarHandler<Long>());
		return total.intValue();
		
	}

	@Override
	public void addCategory(Category category) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		try {
			queryRunner.update(sql, category.getCid(),category.getCname());
		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
/*	public boolean deleteCategoryByIds(String cid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql="delete from category where cid in ( ? )";
		try {
			int update = queryRunner.update(sql,cid);
			System.out.println(update);
			boolean b = update!=0?true:false;
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("vip.hht.estore.daoImpl.CategoryDaoImpl.deleteCategoryById(String)ดํมห");
		}
		
	}*/

	
	
	public boolean deleteCategoryByIds(String cid) {
	QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
	String sql="delete from category where cid in (";
	StringBuffer sb = new StringBuffer(sql);
	boolean b = false;
	String[] sss = cid.split(",");
	for (String ss : sss) {
		sb.append("'"+ss+"'").append(",");
//		sb.append(ss).append(",");
	}
	sb.deleteCharAt(sb.length()-1).append(")");
	String sq = sb.toString();
	System.out.println(sq);
	try {
		int update = queryRunner.update(sq);
		System.out.println(update);
		b = update!=0?true:false;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return b;
	
}
	
	public static void main(String[] args) {
//		new CategoryDaoImpl().deleteCategoryByIds("'9','10','11'");
		new CategoryDaoImpl().deleteCategoryByIds("9,10,11");
	}
}
