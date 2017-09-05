package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月12日
@Description:
*/

import java.sql.Timestamp;
import java.util.Date;

import cn.edu.hist.weilai.signup.utils.CheckUtils;

public class Student extends Entity{
	
	private String _id;
	private String name;
	private String college;
	private String majorClass;//专业班级，如物联网151
	private String phone;
	private String qq;
	private int state;//状态，0-4:[正常，面试中，面试成功，，已淘汰，已删除]，StudentState类有定义
	private Timestamp signupTime;
	private String marks;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, String college, String majorClass, String phone,String qq,int state) {
		super();
		this.name = name;
		this.college = college;
		this.majorClass = majorClass;
		this.phone = phone;
		this.qq = qq;
		this.state = state;
		this.signupTime = new Timestamp(new Date().getTime());
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

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
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajorClass() {
		return majorClass;
	}
	public void setMajorClass(String majorClass) {
		this.majorClass = majorClass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getSignupTime() {
		return signupTime;
	}
	public void setSignupTime(Timestamp signupTime) {
		this.signupTime = signupTime;
	}
	
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Student [_id=" + _id + ", name=" + name + ", college=" + college + ", majorClass=" + majorClass
				+ ", phone=" + phone + ", qq=" + qq + ", state=" + state + ", signupTime=" + signupTime + "]";
	}
	//为了搜索时高亮关键词
	public void warnFont(String font) {
		if(CheckUtils.hasNull(font)) {
			return;
		}
		String str = "<span class=\"am-text-warning\">"+font+"</span>";
		this.college = this.college.replaceAll(font, str);
		this.majorClass = this.majorClass.replaceAll(font, str);
		this.name = this.name.replaceAll(font, str);
		this.phone = this.phone.replaceAll(font, str);
		this.qq = this.qq.replace(font, str);
	}
}
