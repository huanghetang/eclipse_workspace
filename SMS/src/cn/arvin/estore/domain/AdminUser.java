package cn.arvin.estore.domain;

public class AdminUser {
	private String id;
	private String email;
	private String password;
	private String nickname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", email=" + email + ", password=" + password + ", nickname=" + nickname + "]";
	}

}
