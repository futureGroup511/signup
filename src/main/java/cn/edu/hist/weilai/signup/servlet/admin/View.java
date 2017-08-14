package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.PageCut;
import cn.edu.hist.weilai.signup.utils.TextUtils;

/*
@Author:song
@Date:2017年8月13日
@Description:
*/
@WebServlet("/admin/view")
public class View extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = req.getParameter("search");
		if(! CheckUtils.hasNull(search)) {
			search = new String(search.getBytes("iso8859-1"));
		}
		String p = req.getParameter("page");
		int page = TextUtils.parseInt(p, 1);
		req.setAttribute("search", search);
		PageCut<Student> pc = studentService.getPageCutBySearch(page>0?page:1, 10, search);
		for(Student s:pc.getData()) {
			s.warnFont(search);
		}
		req.setAttribute("pc", pc);
		forward("admin/view", req, resp);
	}
}
