package vip.hht.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.Ad;
import vip.hht.beans.Bigad;
import vip.hht.dao.IndexDao;

public class IndexDaoImpl implements IndexDao {
	private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	@Override
	public List<Ad> adList() {
		String sql="select * from ad order by ad_money desc limit 0,5";
		try {
			return qr.query(sql, new BeanListHandler<Ad>(Ad.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("π„∏Ê≤È—Ø ß∞‹");
		}
	}

	
	@Override
	public Ad findAdInfoById(String ad_id) {
		String sql="select * from ad where ad_id=?";
		try {
			return qr.query(sql, new BeanHandler<Ad>(Ad.class),ad_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("π„∏ÊœÍ«È≤È’“ ß∞‹");
		}
	}
	
	@Override
	public List<Bigad> imgList() {
		String sql="select * from bigad";
		try {
			return qr.query(sql, new BeanListHandler<Bigad>(Bigad.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("¬÷≤•Õº∆¨≤È’“ ß∞‹");
		}
	}
}
