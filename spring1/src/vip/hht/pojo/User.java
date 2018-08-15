package vip.hht.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class User {
	private String name;
	private Integer age;
	
	
	private String[] beauties;//array
	

	private List beautyList;//list or set
	
	private Map beautyMap;
	
	private Car car;
	
	public String[] getBeauties() {
		return beauties;
	}
	public void setBeauties(String[] beauties) {
		this.beauties = beauties;
	}
	public List getBeautyList() {
		return beautyList;
	}
	public void setBeautyList(List beautyList) {
		this.beautyList = beautyList;
	}
	public Map getBeautyMap() {
		return beautyMap;
	}
	public void setBeautyMap(Map beautyMap) {
		this.beautyMap = beautyMap;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
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
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public User() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", beauties=" + Arrays.toString(beauties) + ", beautyList="
				+ beautyList + ", beautyMap=" + beautyMap + ", car=" + car + "]";
	}
	public void init(){
		System.out.println("user init()...");
	}
	
	public void destroyMethod(){
		System.out.println("user destroy()...");
	}
	

}
