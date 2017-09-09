package cn.edu.hist.weilai.signup.service;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.entity.Admin;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import static com.mongodb.client.model.Filters.and;

/*
@Author:song
@Date:2017年8月12日
@Description:
*/
public class AdminService extends MongoBaseDao<Admin>{
	public Admin queryByAccountAndPassword(String account,String password) {
		Document doc = getCollection().find(and(eq("phone",account),eq("password",password))).first();
		return doc== null ? null:toEntity(doc);
	}
	public Admin queryByAccount(String account) {
		Document doc = getCollection().find(eq("phone",account)).first();
		return doc== null ? null:toEntity(doc);
	}
}
