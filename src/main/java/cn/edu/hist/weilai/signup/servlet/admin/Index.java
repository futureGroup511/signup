package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.servlet.BaseServlet;

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
		req.setAttribute("navNow1", "nav-a-now");
		int signupNum = studentService.countIntEntity();
		req.setAttribute("signupNum", signupNum);
		forward("admin/index", req, resp);
	}
}
