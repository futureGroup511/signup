package cn.edu.hist.weilai.signup.apitools;
/*
@Author:song
@Date:2017年8月14日
@Description:
*/
public class ReturnResult {
	
	private int result ;
	private String message ;
	
	public ReturnResult(int result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	
	public String toJson() {
		return String.format("{\"result\":,%s\"message\":\"%s\"}", result,message);
	}

	@Override
	public String toString() {
		return "ReturnResult [result=" + result + ", message=" + message + "]";
	}
	
}
