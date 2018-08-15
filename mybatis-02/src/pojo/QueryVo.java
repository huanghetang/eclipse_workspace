package pojo;

import java.util.List;

/**
 * @author zhoumo
 *
 */
public class QueryVo {
	private User user;
	private List<Integer> ids;
	private Integer[] idArrays;
	private List<Integer[]> idArrayList;
	
	
	
	


	public List<Integer[]> getIdArrayList() {
		return idArrayList;
	}

	public void setIdArrayList(List<Integer[]> idArrayList) {
		this.idArrayList = idArrayList;
	}

	public Integer[] getIdArrays() {
		return idArrays;
	}

	public void setIdArrays(Integer[] idArrays) {
		this.idArrays = idArrays;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
