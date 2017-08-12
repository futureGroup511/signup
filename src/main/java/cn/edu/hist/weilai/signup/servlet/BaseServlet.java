package cn.edu.hist.weilai.signup.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void redir404(HttpServletResponse resp) {
		logger.debug("404.....");
	}
	
	protected void redir500(HttpServletResponse resp) {
		logger.debug("500.....");
	}

}
