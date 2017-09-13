package signup;

import cn.edu.hist.weilai.signup.service.StatisticsService;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
public class AddInterviewItem {
	
	public static void main(String[] args) {
		StatisticsService ss = new StatisticsService();

		for(int x:ss.getWeakSignCount()){
			System.out.println(x);
		}
	}
}
