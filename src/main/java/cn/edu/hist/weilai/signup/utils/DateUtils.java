package cn.edu.hist.weilai.signup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String toString(Date date,String formator){
        if(formator == null || formator.length()==0){
            formator = "YY-MM-DD HH-mm-ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formator);
        return simpleDateFormat.format(date);
    }
}
