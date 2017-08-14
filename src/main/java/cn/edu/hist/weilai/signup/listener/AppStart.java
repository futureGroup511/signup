package cn.edu.hist.weilai.signup.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
@WebListener
public class AppStart implements ServletContextListener{
	private Logger logger = Logger.getLogger(AppStart.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext context = arg0.getServletContext();
		String rootUrl = context.getContextPath();
		if(!rootUrl.endsWith("/")) {
			rootUrl += "/";
		}
		String staticUrl = rootUrl + "static/";
		context.setAttribute("rootUrl", rootUrl);//网站根目录，以/结尾
		context.setAttribute("staticUrl", staticUrl);//静态文件目录，以/结尾，暂定rootUrl + "static/"
		logger.info(String.format("appStart listener,rootUrl:%s,staticUrl:%s", rootUrl,staticUrl));
	}

}
