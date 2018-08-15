package cn.arvin.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.arvin.estore.dao.ProductDao;
import cn.arvin.estore.domain.Product;
import cn.arvin.estore.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {
	
	//执行sql不处理事务，获取连接池，不选择Connection
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource()); 
	
	//处理事务保证连接相同,后续传Connection执行
	private QueryRunner run = new QueryRunner();
	
	@Override
	public void release(String cid) {
		String sql = "update product set cid = null where cid = ?";
		try {
			run.update(JDBCUtils.getCurrentConnection(), sql, cid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("解除外键失败");
		}
	}

	@Override
	public void findAll(List<Product> list) {
		String sql = "select * from product";
		try {
			List<Product> products = qr.query(sql , new BeanListHandler<Product>(Product.class));
			list.addAll(products);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询所有商品信息失败");
		}
	}

	@Override
	public Product findProductByPid(String pid) {
		String sql = "select * from product where pid = ?";
		try {
			return qr.query(sql, new BeanHandler<Product>(Product.class), pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询指定ID商品失败");
		}
	}

	@Override
	public Product findPnumByPid(String pid) {
		String sql = "select * from product where pid = ?";
		try {
			return run.query(JDBCUtils.getCurrentConnection(),sql, new BeanHandler<Product>(Product.class), pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("生成订单查询指定商品库存失败");
		}
	}

	@Override
	public void minusPNum(String pid, int buynum) {
		String sql = "update product set pnum = pnum - ? where pid = ?";
		try {
			 run.update(JDBCUtils.getCurrentConnection(),sql, buynum, pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("商品库存减少失败");
		}
	}

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

	/*@Override
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
			throw new RuntimeException("添加商品出错");
		}
		return b;
	}*/

	@Override
	public boolean addProduct(Product product) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
//		String sql = "INSERT INTO product VALUES(pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid)";
		String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,0,?,1000)";
		boolean b = false;
		try {
			int update = queryRunner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),
					product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getCid());
			b = update == 0?false:true;
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("插入商品信息出错");
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update product set pname=?,market_price=?,shop_price=?,pdate=?,is_hot=?,pdesc=?,cid=? ,pnum=?  where pid=?";
		boolean b = false;
		try {
			int update = queryRunner.update(sql, product.getPname(),product.getMarket_price(),product.getShop_price(),
					product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getCid(),product.getPnum(),product.getPid());
			b = update == 0?false:true;
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新商品信息出错");
		}
	}

	@Override
	public void delete(String pid) {
		String sql = "update product set pflag = 1 where pid = ?";
		try {
			run.update(JDBCUtils.getCurrentConnection(), sql, pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("下架商品操作失败");
		}
		
	}

}
