package vip.hht.dao;

import java.util.List;

import vip.hht.beans.MyComment;

public interface CommentDao {

	void addComment(String oid,int uid, String pid, String level, String comment);

	List<MyComment> findCommentByPid(String pid,int startIndex,int size);

	public int findTotalCounts(String pid);

}
