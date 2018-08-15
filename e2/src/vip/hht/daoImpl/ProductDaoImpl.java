package vip.hht.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.PageBean;
import vip.hht.beans.Product;
import vip.hht.dao.ProductDao;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List findProductList() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from product";
		try {
			return	queryRunner.query(sql, new BeanListHandler<Product>(Product.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Product findProductById(String pid) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from product where pid =?";
		try {
			return	queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public int findTotalCount() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select count(1) from product";
		try {
			Long total = queryRunner.query(sql, new ScalarHandler<Long>());
			return total.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public List finPageList(int startIndex, int size1) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql ="select * from product limit ?,?";
		try {
			return	queryRunner.query(sql, new BeanListHandler<Product>(Product.class),startIndex,size1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public void updatePnumByPid(Connection con, String pid, int pnum) {
		QueryRunner queryRunner = new QueryRunner();
		String sql ="update product set pnum = ? where pid = ?";
		try {
			queryRunner.update(con,sql, pnum,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}



}
