package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.SignupLog;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.entity.VisitLog;
import cn.edu.hist.weilai.signup.service.StatisticsService;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.servlet.Signup;

/*
@Author:song
@Date:2017年8月13日
@Description:
*/
@WebServlet("/admin/index")
public class Index  extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int[] weakCount = statisticsService.getWeakSignCount();
		StringBuilder weakCountStr = new StringBuilder("[");
		for(int x:weakCount){
			weakCountStr.append(x).append(",");
		}
		weakCountStr.deleteCharAt(weakCountStr.length()-1);
		weakCountStr.append("]");
		req.setAttribute("weakCount",weakCountStr);
		req.setAttribute("NORMAL",studentService.queryCountByState(StudentState.NORMAL));
		req.setAttribute("INTERVIEW",studentService.queryCountByState(StudentState.INTERVIEW));
		req.setAttribute("INTERVIEW_SUCCESS",studentService.queryCountByState(StudentState.INTERVIEW_SUCCESS));
		req.setAttribute("INTERVIEW_FAIL",studentService.queryCountByState(StudentState.INTERVIEW_FAIL));
		List<VisitLog> visitLogs = visitLogService.getNew(20);
		List<SignupLog> signupLogs = signupLogService.getNew(20);
		req.setAttribute("visitLogs",visitLogs);
		req.setAttribute("signupLogs",signupLogs);
		logger.debug(signupLogs.size());
		req.setAttribute("onlineNum", StatisticsService.online);
		req.setAttribute("signupNum",studentService.countIntEntity());
		req.setAttribute("visitNum",visitLogService.countEntity());
		forward("admin/index", req, resp);
	}
}
