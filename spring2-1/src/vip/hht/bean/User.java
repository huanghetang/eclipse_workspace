package vip.hht.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
//@Component("user1")
@Repository("user")
//@Service
//@Controller
@Scope("singleton")//生成策略
public class User {
	@Value("景甜")
	private String name;
	@Value(value="18")
	private Integer age;
//	@Autowired//自动装配(更具类型)
//	@Qualifier("car1")
	@Resource(name="car")
	private Car car;
	
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
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", car=" + car + ", beauties=" + Arrays.toString(beauties)
				+ ", beautyList=" + beautyList + ", beautyMap=" + beautyMap + "]";
	}
	public User(){}
	public User(String name,Integer age){
		this.name = name;
		this.age = age;
		
	}
	
//	@PostConstruct//在构造方法之后调用
	public void showInit(){
		System.out.println("show");
	}
//	@PreDestroy//在销毁之前
	public void showDestory(){
		System.out.println("destory");
	}
	
	

}
