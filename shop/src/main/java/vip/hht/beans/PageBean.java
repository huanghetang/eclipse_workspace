package vip.hht.beans;

import java.util.List;

/**
 * 分页对象
 * @author zhoumo
 *
 */
public class PageBean {
	//总条数
	private long total;
	//总页数
	private int end;
	//每页多少条
	private int size;
	//当前页
	private int pageNum; 
	//分页数据
	private List data;
	//导航条
	private int[] bar;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
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
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	
	/**
	 * 导航条
	 * @return
	 */
	public int[] getBar() {
		//参考的是百度,导航条超过10页数据时,始终显示10个有效页码
		//每页默认显示10条数据
		int start = 0;//左边第一个页码
		int stop = 0; //右边最后一个页码
		if(end<=10){//总页数不超过10页时
			start = 1;
			stop = end;
		}else{//超过10页时
			start = pageNum-5;
			stop = pageNum+4;
			//当小于10页时,让负数页码不显示
			if(start<1){
				start = 1;
				stop = 10;
			}
			//当超过总页数时让无效的页不显示
			if(stop>=end){
				start = end - 9;
				stop = end;
			}
		}
		bar = new int[stop-start+1];//页码的总个数
		int index = start;//页码的第一个数
		for(int i=0;i<bar.length;i++){//给页码赋值
			bar[i] = index++;
		}
		return bar;
	}
	public void setBar(int[] bar) {
		this.bar = bar;
	}
	
	
	
}
