package cn.edu.hist.weilai.signup.servlet;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/interviewTime")
public class InterviewTime extends BaseServlet{

    private long lastUpdate = 0L;
    private List<Student> students = new LinkedList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String search = req.getParameter("search");
        if(search!=null && search.trim().length() > 0 ){
            logger.debug(search);
            search = new String(search.getBytes("iso8859-1"));
            logger.debug(search);
            req.setAttribute("search",search);
            List<Student> results = studentService.queryAll(search);
            req.setAttribute("students",results);
            req.setAttribute("search",search);
            req.setAttribute("size",results.size());
            this.forward("interviewTime",req,resp);
            return;
        }

        long time = System.currentTimeMillis() ;
        //一分钟一更新
        if(time - lastUpdate > 60000){
            synchronized (this.students){
                lastUpdate = time;
                students = studentService.queryAllEntity();
                logger.debug(students.size());
                //排序,面试成功\面试失败\面试中\待定,先报名在前
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        if(s1.getState() == s2.getState()){
                            return compareTime(s1,s2);
                        }
                        if(s1.getState() == StudentState.INTERVIEW_SUCCESS){
                            return -1;
                        }
                        if(s1.getState() == StudentState.INTERVIEW_FAIL){
                            if(s2.getState() == StudentState.INTERVIEW_SUCCESS){
                                return 1;
                            }
                            return -1;
                        }
                        if(s1.getState() == StudentState.INTERVIEW){
                            if(s2.getState() == StudentState.INTERVIEW_SUCCESS){
                                return 1;
                            }
                            if(s2.getState() == StudentState.INTERVIEW_FAIL){
                                return 1;
                            }
                            return -1;
                        }
                        return compareTime(s1,s2);
                    }
                    private int compareTime(Student s1,Student s2){
                        if(s1.getSignupTime().getTime() > s1.getSignupTime().getTime()){
                            return 1;
                        }else{
                            return -1;
                        }

                    }
                });
            }
        }
        req.setAttribute("students",students);
        req.setAttribute("size",students.size());
        this.forward("interviewTime",req,resp);
    }
}
