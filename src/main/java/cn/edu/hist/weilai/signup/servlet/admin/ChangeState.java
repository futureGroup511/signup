package cn.edu.hist.weilai.signup.servlet.admin;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;
import java.io.IOException;

@WebServlet("/admin/changeState")
public class ChangeState extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = this.getParams(req,"id","toState");
        if(CheckUtils.hasNull(values)){
            resp.getWriter().print("{result:-1,message:'失败，请求不对！'}");
            return;
        }
        Student student = studentService.queryEntity(values[0]);
        if(student == null){
            resp.getWriter().print("{result:1,message:'失败，未找到学生！'}");
            return;
        }
        int state = TextUtils.parseInt(values[1],0);
        if(state<0 || state > 4){
            state = 0;
        }
        student.setState(state);
        studentService.updateEntity(student);
        resp.getWriter().print("{result:0,message:'成功！'}");
    }
}
