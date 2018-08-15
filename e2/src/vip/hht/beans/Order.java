package vip.hht.beans;

import java.sql.Timestamp;

public class Order {
	
	private String id;
	private Integer uid;
	private Double totalprice;
	private String address;
	private Integer status = 0;
	//使用Timestamp类型才能用null给mysql数据库timestamp赋值
	private Timestamp createtime;
	//购物车表中提交的商品id
	private String[] pids;
	
	
	
	
	
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String[] getPids() {
		return pids;
	}
	public void setPids(String[] pids) {
		this.pids = pids;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
