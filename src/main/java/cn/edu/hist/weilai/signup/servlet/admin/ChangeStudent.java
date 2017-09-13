package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.hist.weilai.signup.entity.OldStudent;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
@WebServlet("/admin/changeStudent")
public class ChangeStudent extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		Student student = studentService.queryEntity(id);
		req.setAttribute("student", student);
		forward("admin/changeStudent", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		Student student = studentService.queryEntity(id);
		if(student == null) {
			req.setAttribute("warning", "学生不存在");
			this.doGet(req, resp);
			return;
		}
		oldStudentService.insertEntity(new OldStudent("管理员修改", JSON.toJSONString(student)));
		student.setName(req.getParameter("name"));
		student.setMajorClass(req.getParameter("majorClass"));
		student.setPhone(req.getParameter("phone"));
		student.setQq(req.getParameter("qq"));
		studentService.updateEntity(student);
		req.setAttribute("remind", "更改成功!");
		this.doGet(req, resp);
	}

}
