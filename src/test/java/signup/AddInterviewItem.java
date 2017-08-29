package signup;

import cn.edu.hist.weilai.signup.entity.InterviewItem;
import cn.edu.hist.weilai.signup.service.InterviewItemService;
import cn.edu.hist.weilai.signup.service.StatisticsService;
import cn.edu.hist.weilai.signup.servlet.admin.Statistics;

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
