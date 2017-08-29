package cn.edu.hist.weilai.signup.service;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.dao.MongoEntityUtils;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.VisitLog;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.PageCut;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Filters.regex;

public class VisitLogService extends MongoBaseDao<VisitLog> {
    public List<VisitLog> getNew(int count){
        MongoCursor<Document> results = getCollection().find().sort(new BasicDBObject("_id", -1)).limit(count).iterator();
        List<VisitLog> articles = MongoEntityUtils.toList(results, VisitLog.class);
        return articles;
    }
}
