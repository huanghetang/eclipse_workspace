package vip.hht.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class User {
	private String name;
	private Integer age;
	
	private String[] beauties;
	private List<String> beautyList;
	private Map<String,String> beautyMap;
	
	public String[] getBeauties() {
		return beauties;
	}
	public void setBeauties(String[] beauties) {
		this.beauties = beauties;
	}
	public List<String> getBeautyList() {
		return beautyList;
	}
	public void setBeautyList(List<String> beautyList) {
		this.beautyList = beautyList;
	}
	public Map<String, String> getBeautyMap() {
		return beautyMap;
	}
	public void setBeautyMap(Map<String, String> beautyMap) {
		this.beautyMap = beautyMap;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", beauties=" + Arrays.toString(beauties) + ", beautyList="
				+ beautyList + ", beautyMap=" + beautyMap + "]";
	}
	public User(){}
	public User(String name,Integer age){
		this.name = name;
		this.age = age;
		
	}
	
	

}
