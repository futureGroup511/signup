package cn.edu.hist.weilai.signup.utils;
/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class TextUtils {
	public static int parseInt(String str,int defaultValue) {
		try {
			defaultValue = Integer.parseInt(str);
		}catch(Exception e) {
		}
		return defaultValue;
	}
}
