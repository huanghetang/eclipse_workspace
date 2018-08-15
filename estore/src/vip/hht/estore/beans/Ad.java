package vip.hht.estore.beans;

public class Ad {
	private int ad_id;
	private String ad_title;
	private Double ad_money;
	private String ad_content;
	private String ad_image;
	
	
	public String getAd_image() {
		return ad_image;
	}
	public void setAd_image(String ad_image) {
		this.ad_image = ad_image;
	}
	public Ad() {
		super();
		
	}
	public Ad(int ad_id, String ad_title, Double ad_money, String ad_content) {
		super();
		this.ad_id = ad_id;
		this.ad_title = ad_title;
		this.ad_money = ad_money;
		this.ad_content = ad_content;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_title() {
		return ad_title;
	}
	public void setAd_title(String ad_title) {
		this.ad_title = ad_title;
	}
	public Double getAd_money() {
		return ad_money;
	}
	public void setAd_money(Double ad_money) {
		this.ad_money = ad_money;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	@Override
	public String toString() {
		return "Ad [ad_id=" + ad_id + ", ad_title=" + ad_title + ", ad_money=" + ad_money + ", ad_content=" + ad_content
				+ "]";
	}
	
}
