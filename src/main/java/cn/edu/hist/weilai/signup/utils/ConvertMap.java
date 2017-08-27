package cn.edu.hist.weilai.signup.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConvertMap {
		public static Map<String, String> convert(Map<String, String[]> params){
			Map<String, String> map=new HashMap<String, String>();
			if(params==null){
				return map;
			}
			for(Map.Entry<String, String[]> entry:params.entrySet()){
				String key=entry.getKey();
				String []values=entry.getValue();
				String value="";
				if(values!=null){
					value=Arrays.toString(values).replace(", ", ",");
					value=value.substring(1, value.length()-1);
				}
				map.put(key, value);
			}
			return map;
		}
	}

