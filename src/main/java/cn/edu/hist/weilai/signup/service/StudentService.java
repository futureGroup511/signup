package cn.edu.hist.weilai.signup.service;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.gte;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import cn.edu.hist.weilai.signup.utils.TextUtils;
import com.mongodb.client.model.Filters;
import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(this.getClass());
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

	public List<Student> queryAll(int state,String search){
		Bson filters = null;
		if(! CheckUtils.hasNull(search)) {
			String regStr = String.format("\\S*%s\\S*", search);
			filters = Filters.or(regex("name", regStr), regex("phone", regStr), regex("college", regStr), regex("majorClass", regStr), regex("qq", regStr));
		}
		if(state > -1){
			filters = Filters.and(eq("state",state),filters==null?new BasicDBObject():filters);
		}
		if(filters == null){
			filters = new BasicDBObject();
		}
		MongoCursor<Document> results = getCollection().find(filters).iterator();
		return MongoEntityUtils.toList(results, Student.class,20);
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
	public int queryCountByCalendar(Calendar c1,Calendar c2){

		if(c2==null){
			Long count = getCollection().count(gte("signupTime",c1.getTime()));
			return count.intValue();
		}
		Long count = getCollection().count(and(gte("signupTime",c1.getTime()),lt("signupTime",c2.getTime())));
		return count.intValue();
	}
    public int queryCountByState(int state){
        Long count = getCollection().count(eq("state",state));
        return count.intValue();
    }
}
