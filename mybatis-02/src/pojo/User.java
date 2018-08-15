package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
		private int id;
		private String username;// �û�����
		private String sex;// �Ա�
		private Date birthday;// ����
		private String address;// ��
		private  List<Order> orderList;
		public List<Order> getOrderList() {
			return orderList;
		}
		public void setOrderList(List<Order> orderList) {
			this.orderList = orderList;
		}
		public User(){}
		public User(int id, String username, String sex, Date birthday, String address) {
			super();
			this.id = id;
			this.username = username;
			this.sex = sex;
			this.birthday = birthday;
			this.address = address;
		}
		public User( String username, String sex, Date birthday, String address) {
			super();
			this.username = username;
			this.sex = sex;
			this.birthday = birthday;
			this.address = address;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public Date getBirthday() {
			return birthday;
		}
		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", birthday=" + birthday
					+ ", address=" + address + ", orderList=" + orderList + "]";
		}


}
