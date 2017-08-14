package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月12日
@Description:面试结果
*/
public class Interview {
	private String _id;
	private String admin_id;
	private String adminName;
	private String student_id;
	private String studentName;
	private String studentMajorClass;
	private String commentItems;//各项评价，以json对象形式保存，如：{'兴趣':'10','努力':'10'}
	private String comment;//整体评价
	private int score;//总分数
	private int perfectScore;//总分
	
	public Interview() {
		// TODO Auto-generated constructor stub
	}

	public Interview(String admin_id, String adminName, String student_id, String studentName,
			String studentMajorClass, String commentItems, String comment, int score, int perfectScore) {
		super();
		this.admin_id = admin_id;
		this.adminName = adminName;
		this.student_id = student_id;
		this.studentName = studentName;
		this.studentMajorClass = studentMajorClass;
		this.commentItems = commentItems;
		this.comment = comment;
		this.score = score;
		this.perfectScore = perfectScore;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentMajorClass() {
		return studentMajorClass;
	}

	public void setStudentMajorClass(String studentMajorClass) {
		this.studentMajorClass = studentMajorClass;
	}

	public String getCommentItems() {
		return commentItems;
	}

	public void setCommentItems(String commentItems) {
		this.commentItems = commentItems;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Interview [_id=" + _id + ", admin_id=" + admin_id + ", adminName=" + adminName + ", student_id="
				+ student_id + ", studentName=" + studentName + ", studentMajorClass=" + studentMajorClass
				+ ", commentItems=" + commentItems + ", comment=" + comment + ", score=" + score + ", perfectScore="
				+ perfectScore + "]";
	}
}
