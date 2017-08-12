package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class Admin extends Entity{
	private String _id;
	private String name;
	private String phone;
	private String password;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Admin [_id=" + _id + ", name=" + name + ", phone=" + phone + ", password=" + password + "]";
	}
}
