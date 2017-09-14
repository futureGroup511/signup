package cn.edu.hist.weilai.signup.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.SignupLog;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
@WebServlet("/signup.html")
public class Signup extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(! cn.edu.hist.weilai.signup.servlet.api.Signup.canSignup) {
			req.setAttribute("warning", "抱歉!报名已经关闭,请联系群里的服务人员");
		}
		forward("signup", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!cn.edu.hist.weilai.signup.servlet.api.Signup.canSignup){
			req.setAttribute("warning", "抱歉!报名已经关闭,请联系群里的服务人员");
			this.doGet(req, resp);
			return;
		}

		String[] values = this.getParams(req, "name","phone","qq","college","majorClass");
		
		for(String str:values){
            if(str!=null && str.length()>30) {
            	req.setAttribute("warning", "某些信息填写的太长了?");
    			this.doGet(req, resp);
    			return;
            }
			logger.debug(str);
        }
        logger.debug(CheckUtils.hasNull(values));
        logger.debug(CheckUtils.hasStrs(values,"<",">"));
		//检查是否为null,且过滤特殊符号防止页面注入js和css
		if(CheckUtils.hasNull(values) || CheckUtils.hasStrs(values, "<",">")) {
			req.setAttribute("warning", "请正确输入,不要输入<,>等字符!");
			this.doGet(req, resp);
			return;
		}
		Student student = studentService.getByPhone(values[1]);
		if(student != null) {
			req.setAttribute("warning", "失败,你已经报名过了,如有其它疑问,请联系群里的服务人员.");
			this.doGet(req, resp);
			return;
		}
		student = new Student(values[0], values[3], values[4], values[1], values[2],StudentState.NORMAL);
		boolean success = studentService.insertEntity(student);
		if(success) {
			SignupLog signupLog = new SignupLog(values[0],req.getHeader("X-Real-IP"),"",req.getSession().getId());
			signupLogService.insertEntity(signupLog);
			req.setAttribute("remind", "报名成功!");
			req.setAttribute("name", values[0]);
			forward("signup_success", req, resp);
			return;
		}
	}
}
