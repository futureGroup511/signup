package cn.edu.hist.weilai.signup.filter;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.service.AdminService;
import cn.edu.hist.weilai.signup.servlet.api.Signup;

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

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		//后台都是自己小组的人，不做各种验证，只验证是否登录
		Admin admin = (Admin) req.getSession().getAttribute("admin");
		if(admin == null){
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("../login");
			return;
		}

		String globalDanger = "";
		if(!Signup.canSignup) {
			globalDanger += "警告:报名开关未开启,前台无法报名!";
			req.setAttribute("globalDanger",globalDanger);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
