package vip.hht.daoImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.FootprintItem;
import vip.hht.dao.FootprintDao;



public class FootprintDaoImpl implements FootprintDao {

	@Override
	public List<FootprintItem> findAllFootprint(int id) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select f.uid,f.pid,p.pname,p.pimage,p.market_price,p.shop_price,f.lastTime from users as u, product as p, footprint as f where u.id =f.uid and p.pid=f.pid and f.uid=? order by f.lastTime desc limit 0,5 ";
		try {
			List<FootprintItem> list= runner.query(sql, new BeanListHandler<FootprintItem>(FootprintItem.class),id);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败");
		}
	}

	@Override
	public void addFootprint(String pid, int uid,Date date) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into footprint values(?,?,?)";
		try {
			runner.update(sql,uid,pid,date);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加浏览记录失败");
		}
		
	}

	@Override
	public int findFootprintByid(String pid, int uid) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(1) from footprint where pid=? and uid=?";
		try {
			Long n = runner.query(sql, new ScalarHandler<Long>(1),pid,uid);
			return Integer.parseInt(n + "");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败");
		}
		
	}

	@Override
	public void updateFootprint(String pid, int uid, Date date) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update footprint set lastTime=? where uid=? and pid = ?";
		try {
			runner.update(sql,date,uid,pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新浏览记录失败");
		}
		
	}

}
