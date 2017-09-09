package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.hist.weilai.signup.entity.InterviewItem;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.servlet.api.Signup;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.TextUtils;

/*
@Author:song
@Date:2017年8月16日
@Description:
*/
@WebServlet("/admin/set")
public class Set extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub



		//获得操作，

		String o = req.getParameter("o");
		//如果操作为deleteInterviewItem，则删除对应的InterviewItem
		if("deleteInterviewItem".equals(o)) {
			String id = req.getParameter("id");
			if(!CheckUtils.hasNull(id)) {
				interviewItemService.deleteEntity(id);
				req.setAttribute("remind", "删除成功！");
			}
		}
		if("closeSignup".equals(o)) {
			Signup.canSignup = false;
			req.setAttribute("warning", "已经关闭报名，学生将无法报名！");
		}

		if("openSignup".equals(o)) {
			Signup.canSignup = true;
			req.setAttribute("globalDanger","");
			req.setAttribute("remind", "开启报名成功！");
		}


		List<InterviewItem> interviewItems = interviewItemService.queryAllEntity();
		req.setAttribute("interviewItems", interviewItems);
		req.setAttribute("canSignup",Signup.canSignup);
		forward("admin/set", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = req.getParameter("operation");
		logger.debug(operation);
		//检查操作是否为null
		if(CheckUtils.hasNull(operation)) {
			//没有操作不管他
			return;
		}
		switch(operation) {
		//添加或者更新评分项
		case "interviewItem":
			//检查是否有ID,有则更新，无则添加
			String[] values = this.getParams(req, "id","name","type","perfectScore");
			if(CheckUtils.hasNull(values[0])) {
				//设置id,为了下面检测id不为Null
				values[0] = "id";
				if(CheckUtils.hasNull(values)) {
					req.setAttribute("warning", "参数不能为null");
					this.doGet(req, resp);
					return;
				}
				InterviewItem ivi = new InterviewItem(values[2],values[1], TextUtils.parseInt(values[3], 0));
				interviewItemService.insertEntity(ivi);
				req.setAttribute("remind", "添加成功！");
				this.doGet(req, resp);
				return;
			}else {
				InterviewItem ivi = new InterviewItem(values[2],values[1], TextUtils.parseInt(values[3], 0));
				ivi.set_id(values[0]);
				interviewItemService.updateEntity(ivi);
				req.setAttribute("remind", "更新成功！");
				this.doGet(req, resp);
				return;
			}
			case "setInterviewTime":
				req.getSession().getServletContext().setAttribute("interviewTime",req.getParameter("interviewTime"));
				req.setAttribute("remind","更新成功！");
				this.doGet(req,resp);
				return;
			
		}
		
		
		
	}

}
