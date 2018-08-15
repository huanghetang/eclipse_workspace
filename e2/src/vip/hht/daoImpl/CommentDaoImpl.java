package vip.hht.daoImpl;

import java.sql.SQLException;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import vip.hht.Tools.JDBCUtils;
import vip.hht.beans.MyComment;
import vip.hht.dao.CommentDao;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void addComment(String oid,int uid, String pid, String level, String comment) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into comments values(?,?,?,?,?,null)";
		try {
			runner.update(sql,uid,oid,pid,comment,level);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加评论失败失败");
		}
	}

	@Override
	public List<MyComment> findCommentByPid(String pid,int startIndex,int size) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select u.nickname,c.comment,c.level, DATE_FORMAT(c.createtime,'%Y-%m-%d %T') as createtime from"
						+ " comments as c,users as u where c.uid=u.id and pid = ? order by createtime desc limit ?,?";
		try {
			List<MyComment> comments  = runner.query(sql, new BeanListHandler<MyComment>(MyComment.class), pid,startIndex,size);
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败");
		}
		
	}

	@Override
	public int findTotalCounts(String pid) {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select count(*) from comments  where pid = ?";
		try {
			 Long  total = runner.query(sql, new ScalarHandler<Long>(), pid);
			 return total.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询用户名失败");
		}
	}

}
