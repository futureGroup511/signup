package cn.edu.hist.weilai.signup.dao;
/*
@Author:song
@Date:2017年8月3日
@Description:
*/

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import cn.edu.hist.weilai.signup.entity.MongoEntity;
import cn.edu.hist.weilai.signup.utils.PageCut;
/*
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.inc;
*/
//数据库操作基类，只有此类对外暴露数据接口，封装了一系列数据操作方法，为了标识，本类方法一般为final 且方法名字以Entity结束
public abstract class MongoBaseDao<T extends MongoEntity> {
	private Logger logger = Logger.getLogger(MongoBaseDao.class);
	
	protected Class<T> clazz;
	protected String collectionName = "";
	
	@SuppressWarnings("unchecked")
	public MongoBaseDao(){
		Type type=this.getClass().getGenericSuperclass();
		if(!(type instanceof ParameterizedType)){
			type=type.getClass().getSuperclass().getGenericSuperclass();
		}
		clazz=(Class<T>)((ParameterizedType) type).getActualTypeArguments()[0];
		
		try {
			
			this.collectionName = clazz.newInstance().getCollectionName();
			logger.debug("collectionName"+collectionName);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final MongoCollection<Document> getCollection() {
		return MongoUtils.getMongoCollection(collectionName);
	}
	
	public final boolean insertEntity(T entity) {
		getCollection().insertOne(MongoEntityUtils.toDocument(entity));
		return true;
	}
	public final boolean deleteEntity(String _id) {
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));
		return getCollection().findOneAndDelete(query) != null;
	}
	public final boolean updateEntity(T entity) {
		String _id = entity.get_id();
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));
		entity.set_id(null);
		getCollection().findOneAndReplace(query, MongoEntityUtils.toDocument(entity));
		entity.set_id(_id);
		return true;
	}
	
	public final List<T> queryEntityList() {
		return null;
	}
	public final T queryEntity(String _id) {
		logger.debug(_id);
		BasicDBObject query = new BasicDBObject();
		query.put("_id", new ObjectId(_id));
		
		Document doc = getCollection().find(query).first();
		return toEntity(doc);
	}
	
	public final T queryFirstEntity() {
		return null;
	}
	public final List<T> queryAllEntity(){
		MongoCursor<Document> cursor= getCollection().find().iterator();
		List<T> list = new LinkedList<>();
		while(cursor.hasNext()) {
			list.add(MongoEntityUtils.toEntity(cursor.next(), clazz));
		}
		return list;
	}
	
	public final long countEntity() {
		return getCollection().count();
	}
	public final int countIntEntity() {
		Long l = getCollection().count();
		return l.intValue();
	}
	public final T toEntity(Document doc) {
		return MongoEntityUtils.toEntity(doc, clazz);
	}
	public PageCut<T> getPageCutEntity(int page,int size){
		Long count = getCollection().count();
		PageCut<T> pc = new PageCut<>(page, size, count.intValue());
		MongoCursor<Document> results= getCollection().find().skip((page-1)*size).limit(size).iterator();
		List<T> replys = MongoEntityUtils.toList(results, clazz,size);
		pc.setData(replys);
		return pc;
	}
}
