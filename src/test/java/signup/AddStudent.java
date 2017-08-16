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
		StudentService studentService = new StudentService();
		for(int i=0;i++ < 99;) {
			List<Student> list = studentService.queryAllEntity();
			if(i%3==0) {
				Student s = list.get(i);
				s.setState(StudentState.INTERVIEW);
				studentService.updateEntity(s);
			}
		}
		System.out.println(studentService.getPageCutBySearch(1, 12, "song"));
	}
}
