package cn.edu.hist.weilai.signup.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.apitools.ApiTools;
import cn.edu.hist.weilai.signup.apitools.ReturnResult;
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

	public static boolean canSignup = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(!canSignup){
			ApiTools.respResult(ReturnResult.SIGNUP_CLOSE, resp);
			return;
		}

		String[] values = this.getParams(req, "name","phone","qq","college","majorClass");
		
		for(String str:values){
            if(str!=null && str.length()>30) {
            	req.setAttribute("warning", "某些信息填写的太长了?");
            	ApiTools.respResult(ReturnResult.REQUEST_ERROR, resp);
    			return;
            }
			logger.debug(str);
        }
        logger.debug(CheckUtils.hasNull(values));
        logger.debug(CheckUtils.hasStrs(values,"<",">"));
		//检查是否为null,且过滤特殊符号防止页面注入js和css
		if(CheckUtils.hasNull(values) || CheckUtils.hasStrs(values, "<",">")) {
			ApiTools.respResult(ReturnResult.REQUEST_ERROR, resp);
			return;
		}
		Student student = studentService.getByPhone(values[1]);
		if(student != null) {
			ApiTools.respResult(ReturnResult.STUDENT_EXIST, resp);
			return;
		}
		student = new Student(values[0], values[3], values[4], values[1], values[2],StudentState.NORMAL);
		boolean success = studentService.insertEntity(student);
		if(success) {
			ApiTools.respResult(ReturnResult.SUCCESS, resp);
			SignupLog signupLog = new SignupLog(values[0],req.getHeader("X-Real-IP"),"",req.getSession().getId());
			signupLogService.insertEntity(signupLog);
			return;
		}
		ApiTools.respResult(ReturnResult.SERVER_ERROR, resp);
	}
}
