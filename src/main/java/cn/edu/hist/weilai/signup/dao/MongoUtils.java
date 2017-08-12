package cn.edu.hist.weilai.signup.dao;
/*
@Author:song
@Date:2017年8月3日
@Description:
*/

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

class MongoUtils {
	private static MongoClient mongoClient = null;
	private static String dbName = "signup";
	
	public static MongoClient getMongoClient() {
		if(mongoClient == null) {
			synchronized (MongoUtils.class) {
				if(mongoClient==null) {
					MongoClientOptions options = MongoClientOptions.builder().minConnectionsPerHost(1).build();
					mongoClient = new MongoClient("localhost:27017",options);
				}
			}
		}
		return mongoClient;
	}
	//DB代表一个连接，用完会自动关闭，DB作用域应该作用于方法
	public static MongoDatabase getMongoDatabase() {
		return getMongoClient().getDatabase(dbName);
	}
	//一个线程只会开启一个连接，所以可以随时获取
	public static MongoCollection<Document> getMongoCollection(String name){
		return getMongoClient().getDatabase(dbName).getCollection(name);
	}
}
