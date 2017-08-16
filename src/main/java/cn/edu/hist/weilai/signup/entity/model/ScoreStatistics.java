package cn.edu.hist.weilai.signup.entity.model;

import cn.edu.hist.weilai.signup.entity.StudentState;

/*
@Author:song
@Date:2017年8月16日
@Description:成绩统计
*/
public class ScoreStatistics implements Comparable<ScoreStatistics>{
	private String student_id;
	private String studentName ;
	private String studentState;
	private int score = 0;//得分
	private int perfectScore = 0;//总分
	private double scoreOfPerfect = 0;//分数占总分的百分比
	private int adminNum = 0;//评分人数目
	
	public ScoreStatistics(String student_id, String studentName,int state) {
		super();
		this.student_id = student_id;
		this.studentName = studentName;
		this.studentState = StudentState.getState(state);
	}
	
	public void addScore(int score,int perfectScore) {
		this.score += score;
		this.perfectScore += perfectScore;
		this.adminNum ++;
		if(score == 0 || perfectScore ==0) {
			scoreOfPerfect = 0;
		}else {
			scoreOfPerfect = new Double(score) / new Double(perfectScore);
		}
	}
	@Override
	public int compareTo(ScoreStatistics o) {
		// TODO Auto-generated method stub
		//反顺序，实现分数高的排在前面
		if(o==null) {
			return -1;
		}
		if(scoreOfPerfect > o.scoreOfPerfect) {
			return -1;
		}else if(scoreOfPerfect == o.scoreOfPerfect) {
			/*
			return studentName.compareTo(o.studentName);
			*/
			//若id还相等，视为1人，否则为两人，先报名的排名靠前
			return student_id.compareTo(o.student_id);
		}
		return 1;
	}

	@Override
	public String toString() {
		return "ScoreStatistics [student_id=" + student_id + ", studentName=" + studentName + ", studentState="
				+ studentState + ", score=" + score + ", perfectScore=" + perfectScore + ", scoreOfPerfect="
				+ scoreOfPerfect + ", adminNum=" + adminNum + "]";
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

	public int getAdminNum() {
		return adminNum;
	}

	public void setAdminNum(int adminNum) {
		this.adminNum = adminNum;
	}

	public String getStudentState() {
		return studentState;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

	public double getScoreOfPerfect() {
		return scoreOfPerfect;
	}

	public void setScoreOfPerfect(int scoreOfPerfect) {
		this.scoreOfPerfect = scoreOfPerfect;
	}
	
}
