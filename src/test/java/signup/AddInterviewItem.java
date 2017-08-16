package signup;

import cn.edu.hist.weilai.signup.entity.InterviewItem;
import cn.edu.hist.weilai.signup.service.InterviewItemService;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
public class AddInterviewItem {
	
	public static void main(String[] args) {
		InterviewItemService its = new InterviewItemService();
		its.insertEntity(new InterviewItem("number","兴趣",20));
		its.insertEntity(new InterviewItem("number","学习",20));
		its.insertEntity(new InterviewItem(InterviewItem.TYPE_TEXT,"亮点",0));
		its.insertEntity(new InterviewItem(InterviewItem.TTYPE_TEXTAREA,"亮点2",0));
	}
}
