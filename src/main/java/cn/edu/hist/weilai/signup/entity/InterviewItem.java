package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月12日
@Description:面试评分项
*/
public class InterviewItem extends Entity{
	
	public static final String TYPE_NUMBER = "number";
	public static final String TTYPE_TEXTAREA = "textarea";
	public static final String TYPE_TEXT = "text";
	private String _id;
	private String type;//number || textarea || text
	private String name;//如：兴趣
	private int score=0;//本类别分数，
	private String content;//内容
	private int perfectScore = 0;//满分
	
	public InterviewItem() {
		// TODO Auto-generated constructor stub
	}
	public InterviewItem(String type, String name,int perfectScore) {
		super();
		this.type = type;
		this.name = name;
		if(this.type.equals(InterviewItem.TYPE_NUMBER)) {
			this.perfectScore = perfectScore;
		}
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public int getPerfectScore() {
		return perfectScore;
	}
	public void setPerfectScore(int perfectScore) {
		this.perfectScore = perfectScore;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "InterviewItem [_id=" + _id + ", type=" + type + ", name=" + name + ", score=" + score + ", content="
				+ content + ", perfectScore=" + perfectScore + "]";
	}
	
}
