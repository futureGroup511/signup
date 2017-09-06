package cn.edu.hist.weilai.signup.service;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.BasicDBObject;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.entity.Interview;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
public class InterviewService extends MongoBaseDao<Interview>{
	
	//倒叙查找，返回最新的一条！
	public Interview getByAdminAndStudent(String admin_id,String student_id) {
		Document doc = getCollection().find(and(eq("admin_id",admin_id),eq("student_id",student_id))).sort(new BasicDBObject("_id", -1)).first();
		return toEntity(doc);
	}
	//倒叙查找，返回最新的一条！
	/*
	public Interview getByStudent(String student_id) {
		Document doc = getCollection().find(and(eq("admin_id",admin_id),eq("student_id",student_id))).sort(new BasicDBObject("_id", -1)).first();
		return toEntity(doc);
	}
	*/
	//若此管理员评论过，则替换
	public void insertOrUpdate(Interview iv) {
		Interview old = getByAdminAndStudent(iv.getAdmin_id(), iv.getStudent_id());
		if(old == null) {
			this.insertEntity(iv);
			return;
		}
		old.setCommentItems(iv.getCommentItems());
		old.setPerfectScore(iv.getPerfectScore());
		old.setScore(iv.getScore());
		this.updateEntity(old);
	}
}
