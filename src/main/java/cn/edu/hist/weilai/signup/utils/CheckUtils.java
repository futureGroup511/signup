package cn.edu.hist.weilai.signup.utils;
/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class CheckUtils {
	public static boolean hasNull(Object ...objects) {
		for(Object obj:objects) {
			if(obj==null || obj.equals("")) {
				return true;
			}
		}
		return false;
	}
	public static boolean hasNull(String[] objects) {
		for(Object obj:objects) {
			if(obj==null || obj.equals("")) {
				return true;
			}
		}
		return false;
	}
}
