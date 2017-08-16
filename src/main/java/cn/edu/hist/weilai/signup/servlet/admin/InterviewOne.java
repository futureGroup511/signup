package cn.edu.hist.weilai.signup.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.entity.InterviewItem;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.TextUtils;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
@WebServlet("/admin/interviewOne")
public class InterviewOne extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String _id = req.getParameter("id");
		if(CheckUtils.hasNull(_id)) {
			redir404(resp);
			return;
		}
		Student student = studentService.queryEntity(_id);
		if(student == null) {
			redir404(resp);
			return;
		}
		Admin admin = (Admin)req.getSession().getAttribute("admin");
		//获取最新的面试结果
		cn.edu.hist.weilai.signup.entity.Interview iv = interviewService.getByAdminAndStudent(admin.get_id(),_id);
		req.setAttribute("student", student);
		List<InterviewItem> interviewItems = interviewItemService.queryAllEntity();
		if(iv != null) {
			req.setAttribute("interview", iv);
			req.setAttribute("warning", "评论成功！你已经评论过此人，再次评论将会覆盖原评论！");
			//把值添加到request域，在页面显示
			JSONObject interviewCommentItems = JSON.parseObject(iv.getCommentItems());
			for(InterviewItem ivi:interviewItems) {
				if(ivi.getType().equals(InterviewItem.TYPE_NUMBER)) {
					ivi.setScore(interviewCommentItems.getIntValue(ivi.getName()));
				}else {
					ivi.setContent(interviewCommentItems.getString(ivi.getName()));
				}
			}
		}
		req.setAttribute("interviewItems", interviewItems);
		forward("admin/interviewOne", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//后台都是自己小组的人，不做各种验证！
		JSONObject commentItems = new JSONObject();
		int score = 0;
		List<InterviewItem> items = interviewItemService.queryAllEntity();
		for(InterviewItem item:items) {
			String name = item.getName();
			if(! name.equals("id")) {
				String value = req.getParameter(name);
				logger.debug(name+value);
				commentItems.put(name, value);
				//必须要判断
				if(item.getType().equals(InterviewItem.TYPE_NUMBER)) {
					score += TextUtils.parseInt(value, 0);
				}
			}
		}
		String id = req.getParameter("id");
		Student student = studentService.queryEntity(id);
		Admin admin = getSessionAttr("admin", Admin.class, req);
		int perfectScore = interviewItemService.getPrefectScore();
		cn.edu.hist.weilai.signup.entity.Interview iv = new cn.edu.hist.weilai.signup.entity.Interview(admin, student, commentItems.toJSONString(), score, perfectScore);
		//不论是否评论过都直接存储到数据库，作为一个评论记录，取的时候倒序取第一个
		interviewService.insertEntity(iv);
		this.doGet(req, resp);
	}

}
