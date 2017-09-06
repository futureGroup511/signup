package cn.edu.hist.weilai.signup.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
@WebServlet("/login")
public class Login extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		forward("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] values= this.getParams(req, "account","password","vCode");
		if( CheckUtils.hasNull(values) || !values[2].equals("weilai")) {
			req.setAttribute("warning", "登录失败，请检查输入的数据！");
			forward("login", req, resp);
			return;
		}
		Admin admin = adminService.queryByAccountAndPassword(values[0],values[1]);
		if(admin==null) {
			req.setAttribute("warning", "登录失败，请检查输入的数据！");
			forward("login", req, resp);
			return;
		}
		req.getSession().setAttribute("admin", admin);
		resp.sendRedirect("admin/index");
	}
}
