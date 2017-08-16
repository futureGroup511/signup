package cn.edu.hist.weilai.signup.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.entity.Interview;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.entity.model.ScoreStatistics;

/*
@Author:song
@Date:2017年8月16日
@Description:
*/
public class StatisticsService {
	public Set<ScoreStatistics> getStatistics(){
		Set<ScoreStatistics> set = new TreeSet<>();
		StudentService studentService = new StudentService();
		AdminService adminService = new AdminService();
		InterviewService interviewService = new InterviewService();
		List<Admin> admins = adminService.queryAllEntity();
		List<Student> students = studentService.queryAllStudent(StudentState.INTERVIEW,StudentState.INTERVIEW_SUCCESS,StudentState.INTERVIEW_FAIL);
		for(Student student:students) {
			ScoreStatistics ss = new ScoreStatistics(student.get_id(), student.getName(),student.getState());
			for(Admin admin:admins) {
				Interview iv = interviewService.getByAdminAndStudent(admin.get_id(), student.get_id());
				if(iv!=null) {
					ss.addScore(iv.getScore(), iv.getPerfectScore());
				}
			}
			set.add(ss);
			student = null;
		}
		return set;
	}
}
