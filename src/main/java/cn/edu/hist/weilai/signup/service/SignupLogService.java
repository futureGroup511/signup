package cn.edu.hist.weilai.signup.service;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.dao.MongoEntityUtils;
import cn.edu.hist.weilai.signup.entity.SignupLog;
import cn.edu.hist.weilai.signup.entity.VisitLog;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.List;

public class SignupLogService extends MongoBaseDao<SignupLog> {
    public List<SignupLog> getNew(int count){
        MongoCursor<Document> results = getCollection().find().sort(new BasicDBObject("_id", -1)).limit(count).iterator();
        List<SignupLog> articles = MongoEntityUtils.toList(results, SignupLog.class);
        return articles;
    }
}
