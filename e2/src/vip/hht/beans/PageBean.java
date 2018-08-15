package vip.hht.beans;

import java.util.List;

public class PageBean {
	//总条数
	private int total;
	//每页显示数
	private int size;
	//当前页
	private int pageNum;
	//总页数
	private int end;
	//数据集合
	private List data;
	//导航条
	private int[] bar;
	
	
	public int[] getBar() {
		//初始化
		int start = 0;
		int stop = 0;
		if(end<=10){
			start = 1;
			stop = end;
		}else{
			start = pageNum-4;
			stop = pageNum+5;
			if(start<1){
				start = 1;
				stop = 10;
			}
			if(stop>end){
				stop = end;
				start = end-9;
			}
			
		}
		bar = new int[stop-start+1];
		int index = 0;
		for(int i=start;i<=stop;i++){
			bar[index++] = i;
		}
		return bar;
	}
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}


}
