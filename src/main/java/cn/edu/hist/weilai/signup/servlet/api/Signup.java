package cn.edu.hist.weilai.signup.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.apitools.ApiTools;
import cn.edu.hist.weilai.signup.entity.SignupLog;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

/*
@Author:song
@Date:2017年8月14日
@Description:
*/
@WebServlet("/api/signup")
public class Signup extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] values = this.getParams(req, "name","phone","qq","college","majorClass");
		//检查是否为null,且过滤特殊符号防止页面注入js和css
		if(CheckUtils.hasNull(values) || CheckUtils.hasStrs(values, "<",">")) {
			ApiTools.respResult(1, resp);
			return;
		}
		Student student = studentService.getByPhone(values[1]);
		if(student != null) {
			ApiTools.respResult(2, resp);
			return;
		}
		student = new Student(values[0], values[3], values[4], values[1], values[2],StudentState.NORMAL);
		boolean success = studentService.insertEntity(student);
		if(success) {
			ApiTools.respResult(0, resp);
			return;
		}
		SignupLog signupLog = new SignupLog(values[0],req.getRemoteAddr(),"",req.getSession().getId());
		signupLogService.insertEntity(signupLog);

		ApiTools.respResult(3, resp);
	}
}
