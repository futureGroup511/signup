package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;
import cn.edu.hist.weilai.signup.utils.ConvertMap;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;

@WebServlet("/admin/studentmanage")
public class StudentManage extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, String[]> user= req.getParameterMap();
		ConvertMap  con =new ConvertMap() ;
		ConvertMap.convert(user);
		System.out.println(ConvertMap.convert(user));
	}
}
