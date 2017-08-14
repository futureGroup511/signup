package cn.edu.hist.weilai.signup.servlet.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.apitools.ReturnResult;
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
		if(CheckUtils.hasNull(values)) {
			ReturnResult rr = new ReturnResult(-1, "请求数据有误,请正确填写数据！");
			respStr(rr.toJson(), resp);
			return;
		}
		
	}
}
