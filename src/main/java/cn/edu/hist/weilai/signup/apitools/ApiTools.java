package cn.edu.hist.weilai.signup.apitools;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
public class ApiTools {
	public static void respResult(int result,HttpServletResponse resp) {
		ReturnResult rr = new ReturnResult(result);
		try {
			resp.getWriter().print(rr.toJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
