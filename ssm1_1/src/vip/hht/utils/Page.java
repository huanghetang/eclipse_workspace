package vip.hht.utils;

import java.util.List;

/**
 *返回给页面的分页数据,包含了分页信息和分页显示的结果集
 */
public class Page<T> {
    
	private int total;//符和查询条件的总条数
	private int page;//当前页
	private int size;//每页显示多少条
    private List<T> rows;//分页查询的结果集
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
	
    
}
