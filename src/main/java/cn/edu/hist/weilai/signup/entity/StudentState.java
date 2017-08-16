package cn.edu.hist.weilai.signup.entity;
/*
@Author:song
@Date:2017年8月14日
@Description:
*/
public class StudentState {
	public static final int NORMAL = 0;
	public static final int INTERVIEW = 1;
	public static final int INTERVIEW_SUCCESS = 2;
	public static final int INTERVIEW_FAIL = 3;
	public static final int DELETE = 4;
	
	public static String getState(int state) {
		switch (state) {
		case NORMAL:
			return "未面试";
		case INTERVIEW:
			return "面试";
		case INTERVIEW_SUCCESS:
			return "面试成功";
		case INTERVIEW_FAIL:
			return "面试失败";
		case DELETE:
			return "已删除";
		}
		return "未知";
	}
}
