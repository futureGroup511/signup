package cn.edu.hist.weilai.signup.entity;

import java.sql.Timestamp;
import java.util.Date;


//对学生的备份,继承学生类
public class OldStudent extends Entity{
	//删除原因
	private String _id;
	private String reason = "";
	private String json = "";
	private Timestamp createTime;
	public OldStudent() {
		// TODO Auto-generated constructor stub
	}
	public OldStudent(String reason, String json) {
		super();
		this.reason = reason;
		this.json = json;
		this.createTime = new Timestamp(new Date().getTime());
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
		
}
