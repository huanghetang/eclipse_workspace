package vip.hht.estore.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.estore.beans.Product;
import vip.hht.estore.dao.ProductDao;
import vip.hht.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> productList(int startIndex, int size) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		try {
			List<Product> list = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),startIndex,size);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询出错");
		}
		
	}

	@Override
	public long totalCounts() {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(1) from product";
		Long count=null;
		try {
			count = queryRunner.query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count==null?0:count.intValue();
	}

	@Override
	public boolean addProduct(Product product) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
//		String sql = "INSERT INTO product VALUES(pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid)";
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,null,?,?,?,?)";
		boolean b = false;
		try {
			int update = queryRunner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
					product.getPimage(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid());
			b = update == 0?false:true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询出错");
		}
		return b;
	}
	
	
	
}
