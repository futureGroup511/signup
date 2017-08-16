package cn.edu.hist.weilai.signup.entity;

import cn.edu.hist.weilai.signup.utils.CheckUtils;

/*
@Author:song
@Date:2017年8月4日
@Description:
*/
public abstract class Entity implements MongoEntity{
	protected String collectionName = "";
	//_id属性就放在各自的bean 类吧
	public Entity() {
		String className = this.getClass().getSimpleName();
		//默认为本类的首字母小写
		this.collectionName =Character.toLowerCase(className.charAt(0))+ className.substring(1);
	}
	
	public String getCollectionName() {
		// TODO Auto-generated method stub
		if(CheckUtils.hasNull(collectionName)) {
			String className = this.getClass().getSimpleName();
			//默认为本类的首字母小写
			this.collectionName =Character.toLowerCase(className.charAt(0))+ className.substring(1);
		}
		return this.collectionName;
	}
	public boolean check() {
		return true;
	}
}
