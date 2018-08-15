package vip.hht.estore.beans;

import java.sql.Timestamp;

public class Bigad {
	private int id;
	private String title;
	private String image;;
	private Timestamp createtime;
	@Override
	public String toString() {
		return "Bigad [id=" + id + ", title=" + title + ", image=" + image + ", createtime=" + createtime + "]";
	}
	public Bigad() {
		super();
		
	}
	
	public Bigad(int id, String title, String image, Timestamp createtime) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.createtime = createtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	
}
