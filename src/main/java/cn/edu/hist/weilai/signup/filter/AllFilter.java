package cn.edu.hist.weilai.signup.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.edu.hist.weilai.signup.service.VisitLogService;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
@WebFilter("/*")
public class AllFilter implements Filter{
	private VisitLogService visitLogService = new VisitLogService();

	private Logger logger = Logger.getLogger(AllFilter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse res = (HttpServletResponse)arg1;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		logger.debug("req url:"+req.getRequestURL());
		//调试阶段，为每个session添加登录状态
		/*VisitLog visitLog = new VisitLog(req.getRemoteHost()+req.getRemoteUser(),req.getRemoteAddr(),req.getRequestURI(),req.getSession().getId());
		visitLogService.insertEntity(visitLog);*/

		arg2.doFilter(arg0, arg1);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
