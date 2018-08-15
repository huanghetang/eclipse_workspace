package vip.hht.estore.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.estore.beans.Ad;
import vip.hht.estore.beans.Bigad;
import vip.hht.estore.dao.AdDao;
import vip.hht.utils.JDBCUtils;

public class AdDaoImpl implements AdDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	@Override
	public List<Ad> findAllAd() {
		String sql="select * from ad";
		try {
			return	qr.query(sql, new BeanListHandler<Ad>(Ad.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("广告查询失败");
		}
		
	}
	@Override
	public List<Ad> findAdByPage(int startIndex, int size) {
		String sql="select * from ad limit ?,?";
		try {
			return qr.query(sql, new BeanListHandler<Ad>(Ad.class),startIndex,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询失败");
		}
	}
	@Override
	public int findAdCount() {
		String sql="select count(*) from ad";
		try {
			Long query = qr.query(sql, new ScalarHandler());
			return query.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查找广告总记录数失败");
		}
	}
	@Override
	public void addAd(Ad ad) {
		String sql ="insert into ad values(null,?,?,?,?)";
		try {
			qr.update(sql, ad.getAd_title(),ad.getAd_money(),ad.getAd_content(),ad.getAd_image());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("插入数据失败");
		}
	}
	@Override
	public void delete(int ad_id) {
		String sql ="delete from ad where ad_id=?";
		try {
			qr.update(sql,ad_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除数据失败");
		}
	}
	@Override
	public List<Bigad> findImgByPage(int startIndex, int size) {
		String sql="select * from bigad limit ?,?";
		try {
			return qr.query(sql, new BeanListHandler<Bigad>(Bigad.class),startIndex,size);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("分页查询失败");
		}
	}
	@Override
	public int findImgCount() {
		String sql="select count(*) from bigad";
		try {
			Long query = qr.query(sql, new ScalarHandler());
			return query.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查找图片总记录数失败");
		}
	}
	@Override
	public void addImg(Bigad bigad) {
		String sql="insert into bigad values(null,?,?,null)";
		try {
			qr.update(sql,bigad.getTitle(),bigad.getImage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("轮播图片上传失败");
		}
	}
	@Override
	public void deleteImg(int img_id) {
		String sql="delete from bigad where id=?";
		try {
			qr.update(sql,img_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除轮播图片失败");
		}
	}

}
