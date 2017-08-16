package cn.edu.hist.weilai.signup.service;

import java.util.List;

import cn.edu.hist.weilai.signup.dao.MongoBaseDao;
import cn.edu.hist.weilai.signup.entity.InterviewItem;

/*
@Author:song
@Date:2017年8月15日
@Description:
*/
public class InterviewItemService extends MongoBaseDao<InterviewItem>{
	public int getPrefectScore() {
		List<InterviewItem> list = queryAllEntity();
		int score = 0;
		for(InterviewItem ivi:list) {
			score += ivi.getScore();
		}
		return score;
	}
}
