package cn.edu.hist.weilai.signup.servlet.admin;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/changeMarks")
public class ChangeMarks  extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = this.getParams(req,"id","marks");
        if(CheckUtils.hasNull(values)){
            resp.getWriter().print("{result:-1,message:'请求为空'}");
            return;
        }
        Student student = studentService.queryEntity(values[0]);
        if(student == null){
            resp.getWriter().print("{result:1,message:'学生不存在'}");
            return;
        }
        student.setMarks(values[1]);
        studentService.updateEntity(student);
        resp.getWriter().print("{result:0,message:'success'}");
    }
}
