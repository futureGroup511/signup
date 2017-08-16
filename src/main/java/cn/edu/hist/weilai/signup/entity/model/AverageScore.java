package cn.edu.hist.weilai.signup.entity.model;
/*
@Author:song
@Date:2017年8月16日
@Description:
*/
public class AverageScore {
	private int score = 0;
	private int perfectScore = 0;
	private int adminNum = 0;
	
	public void addAdmin(int score,int perfectScore) {
		this.score += score;
		this.perfectScore += perfectScore;
		this.adminNum ++;
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

	@Override
	public String toString() {
		return "AverageScore [score=" + score + ", perfectScore=" + perfectScore + ", adminNum=" + adminNum + "]";
	}
}
