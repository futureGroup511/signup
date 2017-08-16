package cn.edu.hist.weilai.signup.service;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.dao.MongoEntityUtils;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.PageCut;
/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class StudentService extends MongoBaseDao<Student>{
	public PageCut<Student> getPageCutBySearch(int page,int size,String search){
		if(page < 1) {
			page = 1;
		}
		MongoCursor<Document> results = null;
		Long count = 0L;
		if(CheckUtils.hasNull(search)) {
			count = getCollection().count();
			results = getCollection().find().skip((page-1)*size).limit(size).sort(new BasicDBObject("_id", -1)).iterator();
		}else {
			String regStr = String.format("\\S*%s\\S*", search);
			Bson filter = or(regex("name",regStr),regex("phone",regStr),regex("college", regStr),regex("majorClass",regStr),regex("qq",regStr));
			count = getCollection().count(filter);
			results = getCollection().find(filter).skip((page-1)*size).limit(size).sort(new BasicDBObject("_id", -1)).iterator();
		}
		PageCut<Student> pc = new PageCut<>(page, size, count.intValue());
		List<Student> articles = MongoEntityUtils.toList(results, Student.class,size);
		pc.setData(articles);
		return pc;
	}
	public PageCut<Student> getPageCutBySearch(int page,int size,String search,int state){
		if(page < 1) {
			page = 1;
		}
		MongoCursor<Document> results = null;
		Long count = 0L;
		if(CheckUtils.hasNull(search)) {
			Bson filter = eq("state",state);
			count = getCollection().count(filter);
			results = getCollection().find(filter).skip((page-1)*size).limit(size).sort(new BasicDBObject("_id", -1)).iterator();
		}else {
			String regStr = String.format("\\S*%s\\S*", search);
			Bson filter = and(eq("state",state),or(regex("name",regStr),regex("phone",regStr),regex("college", regStr),regex("majorClass",regStr),regex("qq",regStr)));
			count = getCollection().count(filter);
			results = getCollection().find(filter).skip((page-1)*size).limit(size).sort(new BasicDBObject("_id", -1)).iterator();
		}
		PageCut<Student> pc = new PageCut<>(page, size, count.intValue());
		List<Student> articles = MongoEntityUtils.toList(results, Student.class,size);
		pc.setData(articles);
		return pc;
	}
	public Student getByPhone(String phone) {
		Document doc = getCollection().find(eq("phone",phone)).first();
		return toEntity(doc);
	}
	public List<Student> queryAllStudent(int ...states){
		
		Bson[] eqs = new Bson[states.length];
		for(int i=-1;++i<states.length;) {
			 eqs[i] = eq("state",states[i]);
		}
		Bson filter = or(eqs);
		MongoCursor<Document> results = getCollection().find(filter).iterator();
		return MongoEntityUtils.toList(results, Student.class);
	}
}
