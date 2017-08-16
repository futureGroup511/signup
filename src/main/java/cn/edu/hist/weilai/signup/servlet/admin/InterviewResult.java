package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.entity.Interview;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.entity.model.AverageScore;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

/*
@Author:song
@Date:2017年8月16日
@Description:
*/
@WebServlet("/admin/interviewResult")
public class InterviewResult extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String _id = req.getParameter("id");
		if(CheckUtils.hasNull(_id)) {
			redir404(resp);
			return;
		}
		Student student = studentService.queryEntity(_id);
		if(student == null) {
			redir404(resp);
			return;
		}
		//看前台是否有操作，正规项目中不允许使用get更新资源，这里没外人，用着方便
		String o = req.getParameter("o");
		if("pass".equals(o)) {
			student.setState(StudentState.INTERVIEW_SUCCESS);
			studentService.updateEntity(student);
			req.setAttribute("remind", "通过面试！");
		}else if("refuse".equals(o)) {
			student.setState(StudentState.INTERVIEW_FAIL);
			req.setAttribute("warning", "面试失败！");
			studentService.updateEntity(student);
		}
		req.setAttribute("student", student);
		List<Admin> admins = adminService.queryAllEntity();
		List<Interview> adminInterviews = new LinkedList<>();
		AverageScore as = new AverageScore();
		for(Admin admin:admins) {
			//用此方法获得最新的评分
			Interview iv = interviewService.getByAdminAndStudent(admin.get_id(), _id);
			if(iv!=null) {
				//添加到平均分的统计上
				as.addAdmin(iv.getScore(), iv.getPerfectScore());
				//对json数据的评论数据进行一次转换，前台直接显示
				JSONObject temp = JSON.parseObject(iv.getCommentItems());
				Set<String> keys = temp.keySet();
				StringBuilder comment = new StringBuilder();
				for(String key:keys) {
					comment.append(key).append(":").append(temp.get(key)).append("<br>");
				}
				iv.setCommentItems(comment.toString());
				adminInterviews.add(iv);
			}
			
		}
		req.setAttribute("averageScore", as);
		req.setAttribute("adminInterviews", adminInterviews);
		forward("admin/interviewResult", req, resp);
	}

}
