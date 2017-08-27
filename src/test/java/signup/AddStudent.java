package signup;

import java.util.List;

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
		
		StudentService ss = new StudentService();
		for(int i=0;i++ <100;) {
			Student s = new Student("wang","信工","物联网工程","110", "3421312",StudentState.NORMAL);
			ss.insertEntity(s);
		}
	}
}
