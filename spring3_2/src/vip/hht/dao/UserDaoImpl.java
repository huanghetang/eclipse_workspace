package vip.hht.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * jdbcDaoSupport这个类需要导入jdbc包和tx包(spring封装了jdbc事务操作)
 * @author zhoumo
 *
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Override
	public void increaseMoney(Integer from, Integer to, double money) {
		String sql = "update account set money = money + ? where id = ?";
		getJdbcTemplate().update(sql, money,to);
		
	}

	@Override
	public void decreaseMoney(Integer from, Integer to, double money) {
		String sql = "update account set money = money - ? where id = ?";
		getJdbcTemplate().update(sql, money,from);
	}

}
