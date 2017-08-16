package cn.edu.hist.weilai.signup.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hist.weilai.signup.service.AdminService;
import cn.edu.hist.weilai.signup.service.InterviewItemService;
import cn.edu.hist.weilai.signup.service.InterviewService;
import cn.edu.hist.weilai.signup.service.StudentService;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class BaseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String viewDir = "/WEB-INF/view/";
	protected String suffix = ".jsp";
	protected Logger logger = null;
	
	protected AdminService adminService = new AdminService();
	protected StudentService studentService = new StudentService();
	protected InterviewItemService interviewItemService = new InterviewItemService();
	protected InterviewService interviewService = new InterviewService();
	
	public BaseServlet() {
		// TODO Auto-generated constructor stub
		logger = Logger.getLogger(this.getClass());
	}
	
	protected void forward(String view,HttpServletRequest req,HttpServletResponse resp) {
		if(!view.endsWith(suffix)) {
			view += suffix;
		}
		try {
			req.getRequestDispatcher(viewDir+view).forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void respStr(String str,HttpServletResponse resp) {
		try {
			resp.getWriter().print(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//连续获取多个参数
	protected String[] getParams(HttpServletRequest req,String ...params) {
		if(params == null || params.length==0)
			return null;
		
		String[] values = new String[params.length];
		for(int i=0;i<params.length;i++) {
			values[i] = req.getParameter(params[i]);
		}
		return values;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getSessionAttr(String name,Class<? extends Object> t,HttpServletRequest req) {
		Object obj = req.getSession().getAttribute(name);
		//return obj == null?null : (T)obj;
		//null可以转换为任意类型
		return (T)obj;
	}
	
	protected void redir404(HttpServletResponse resp) {
		logger.debug("404.....");
	}
	
	protected void redir500(HttpServletResponse resp) {
		logger.debug("500.....");
	}
}
