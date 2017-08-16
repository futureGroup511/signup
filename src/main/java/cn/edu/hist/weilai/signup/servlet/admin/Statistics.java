package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.model.ScoreStatistics;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;

/*
@Author:song
@Date:2017年8月16日
@Description:
*/
@WebServlet("/admin/statistics")
public class Statistics extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Set<ScoreStatistics> statistics = statisticsService.getStatistics();
		req.setAttribute("statistics", statistics);
		int x =0;
		for(ScoreStatistics ss:statistics) {
			x++;
			logger.debug(x+":" + ss);
		}
		forward("admin/statistics", req, resp);
	}

}
