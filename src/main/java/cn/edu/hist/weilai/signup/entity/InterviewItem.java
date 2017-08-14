package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月12日
@Description:面试评分项
*/
public class InterviewItem extends Entity{
	private String _id;
	private String name;//如：兴趣
	private int score;//本类别分数，如满分
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "InterviewItem [_id=" + _id + ", name=" + name + ", score=" + score + "]";
	}
	
}
