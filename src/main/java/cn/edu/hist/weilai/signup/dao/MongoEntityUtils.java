 package cn.edu.hist.weilai.signup.dao;
/*
@Author:song
@Date:2017年8月3日
@Description:
*/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.Document;

import com.mongodb.client.MongoCursor;

import cn.edu.hist.weilai.signup.entity.MongoEntity;

//实现MongoEntity的反射与转换
public class MongoEntityUtils {

	// private static Logger logger = Logger.getLogger(MongoUtils.class);
	// 对反射结果进行缓存，第一次费时，以后快速
	private static Map<Class<? extends MongoEntity>, Field[]> entityGetFidlds = new Hashtable<>();
	private static Map<Class<? extends MongoEntity>, Field[]> entitySetFidlds = new Hashtable<>();
	private static Logger logger = Logger.getLogger(MongoEntityUtils.class);

	public static Document toDocument(MongoEntity entity) {
		if (entity == null) {
			return null;
		}
		Field[] fields = entityGetFidlds.get(entity.getClass());
		if (fields == null || fields.length == 0) {
			fields = createGetFields(entity.getClass());
		}
		Document doc = new Document();
		for (Field f : fields) {
			try {
				logger.debug("field:"+f);
				Object obj = f.method.invoke(entity);
				if(obj instanceof Object[]) {
					BsonArray ba = new BsonArray();
					Object[] objArr = (Object[])obj;
					for(Object o:objArr) {
						ba.add(new BsonString(o.toString()));
					}
					doc.append(f.name, ba);
				}else if(obj != null) {
					doc.append(f.name, obj);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return doc;
	}

	public static <T> T toEntity(Document doc, Class<T> cl) {
		if (doc == null || cl == null) {
			return null;
		}
		Field[] fields = entitySetFidlds.get(cl);
		if (fields == null || fields.length == 0) {
			fields = createSetFields(cl);
		}
		T t = null;
		try {
			t = cl.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if (t == null) {
			return null;
		}
		for (Field f : fields) {
			try {
				Object obj = doc.get(f.name);
				// 如果参数是String类型，转换后再赋值
				if (obj != null) {
					logger.debug(obj.getClass());
					logger.debug(f.name);
					logger.debug(f.method);
					String className = f.method.getParameterTypes()[0].getSimpleName();
					switch(className) {
						case "String":
							f.method.invoke(t, obj.toString());
							break;
						case "Timestamp":
							f.method.invoke(t, new Timestamp(((Date)obj).getTime()));
							break;
						default:
							f.method.invoke(t, obj);
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}

	// 创建属性并放入缓存
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Field[] createGetFields(Class cl) {
		Method[] methods = cl.getDeclaredMethods();
		List<Field> list = new LinkedList<>();
		for (Method method : methods) {
			StringBuilder name = new StringBuilder(method.getName());
			if (name.toString().startsWith("get")) {
				name.delete(0, 3);
				name.replace(0, 1, ("" + name.charAt(0)).toLowerCase());
				Field field = new Field(name.toString(), method);
				list.add(field);
			}
		}

		Field[] fields = new Field[list.size()];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = list.get(i);
		}
		entityGetFidlds.put(cl, fields);
		return fields;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Field[] createSetFields(Class cl) {
		Method[] methods = cl.getDeclaredMethods();
		List<Field> list = new LinkedList<>();
		for (Method method : methods) {
			StringBuilder name = new StringBuilder(method.getName());
			if (name.toString().startsWith("set")) {
				name.delete(0, 3);
				name.replace(0, 1, ("" + name.charAt(0)).toLowerCase());
				Field field = new Field(name.toString(), method);
				list.add(field);
			}
		}
		Field[] fields = new Field[list.size()];
		for (int i = 0; i < fields.length; i++) {
			fields[i] = list.get(i);
		}
		entitySetFidlds.put(cl, fields);
		return fields;
	}

	public static <T> List<T> toList(MongoCursor<Document> results, Class<T> cl) {
		List<T> list = new LinkedList<T>();
		while (results.hasNext()) {
			list.add(toEntity(results.next(), cl));
		}
		return list;
	}

	public static <T> List<T> toList(MongoCursor<Document> results, Class<T> cl, int size) {
		List<T> list = new ArrayList<>(size);
		while (results.hasNext()) {
			list.add(toEntity(results.next(), cl));
		}
		return list;
	}

}

// 对反射结果进行缓存，第一次费时，以后快速
class Field {
	public String name;
	public Method method;

	public Field(String name, Method method) {
		super();
		this.name = name;
		this.method = method;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", method=" + method + "]";
	}
	
}
