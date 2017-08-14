package signup;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.service.StudentService;

/*
@Author:song
@Date:2017年8月13日
@Description:
*/
public class AddStudent {
	
	public static void main(String[] args) {
		StudentService studentService = new StudentService();
		for(int i=0;i++ < 99;) {
			Student s = new Student("宋民举", "信息工程学院", "物联网工程", "1551667256", "860080937", StudentState.NORMAL);
			studentService.insertEntity(s);
		}
		System.out.println(studentService.getPageCutBySearch(1, 12, "song"));
	}
}
