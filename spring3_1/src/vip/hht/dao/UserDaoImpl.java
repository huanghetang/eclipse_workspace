package vip.hht.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.stereotype.Repository;

import vip.hht.beans.User;


/**
 * spring提供jdbcDaosupport类实现了JdbcTemplate接口,至此,dao层的依赖注入关系由dataSource--jdbcTemplate--dao变成了 dataSource--dao(extends JdbcDaoSupport)
 * 直接在此类配置即可
 * @author zhoumo
 *
 */
@Repository("userDaoImpl")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
//	@Resource(name="dataSource")
//	private DataSource dataSource;
//	@Resource(name="jdbcTemplate")
//	private JdbcTemplate jt;
	
	
//	public JdbcTemplate getJt() {
//		return jt;
//	}
//
//	public void setJt(JdbcTemplate jt) {
//		this.jt = jt;
//	}

	@Override
	public void add(User u) {
		String sql  = "insert into user values(null,?)";
//		jt.update(sql,u.getName());
		super.getJdbcTemplate().update(sql,u.getName());
	}

	@Override
	public void deleteById(int id) {
		String sql  = "delete from user where id= ?";
//		jt.update(sql, 7);
		super.getJdbcTemplate().update(sql, 7);
		
	}

	@Override
	public void update(User u) {
		String sql  = "update user set name= ? where id = ?";
//		jt.update(sql, u.getName(),u.getId());
		super.getJdbcTemplate().update(sql, u.getName(),u.getId());
	}

	@Override
	public User findById(int id) {
		String sql  = "select * from user  where id = ?";
//		User u =  jt.queryForObject(sql, new RowMapper<User>(){
		User u =  getJdbcTemplate().queryForObject(sql, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				return user;
			}
		}, id);
		return u;
		
	}

	@Override
	public int findUserCount() {
		String sql  = "select count(*) from user";
//		return jt.queryForObject(sql, int.class);
		return getJdbcTemplate().queryForObject(sql, int.class);
	}

	@Override
	public List<User> findUserList() {
		String sql = "select * from user";
//		List<User> userList = jt.query(sql, new RowMapper<User>(){
			List<User> userList = super.getJdbcTemplate().query(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				return user;
			}
			
		});
		return userList;
	}

}
