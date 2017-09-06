package cn.edu.hist.weilai.signup.apitools;
/*
@Author:song
@Date:2017年8月14日
@Description:
*/
public class ReturnResult {
	public static final int SUCCESS = 0;
	public static final int REQUEST_ERROR = 1;
	public static final int STUDENT_EXIST = 2;
	public static final int SERVER_ERROR = 3;
	public static final int SIGNUP_CLOSE = 4;
	
	private static final String[] messages = {"成功","请求错误","您已经报名过,请不要重复报名!","未知原因导致报名失败","报名已经关闭!"};
	
	private int result ;
	private String message ;
	
	public ReturnResult(int result) {
		this.result = result;
		this.message = messages[result];
	}
	
	public String toJson() {
		return String.format("{\"result\":%s,\"message\":\"%s\"}", result,message);
	}

	@Override
	public String toString() {
		return "ReturnResult [result=" + result + ", message=" + message + "]";
	}
	
}
