package cn.edu.hist.weilai.signup.servlet.admin;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/register")
public class Register extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.forward("admin/register",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String values[] = this.getParams(req,"phone","name","password","vCode");
        if(CheckUtils.hasNull(values)){
            req.setAttribute("warning","正确填写所有字段!");
            this.doGet(req,resp);
            return;
        }
        if(!"weilai".equals(values[3])){
            req.setAttribute("warning","验证码错误!");
            this.doGet(req,resp);
            return;
        }
        Admin admin = adminService.queryByAccount(values[0]);
        if(admin!=null){
            req.setAttribute("warning","用户已经存在!");
            this.doGet(req,resp);
            return;
        }
        admin = new Admin();
        admin.setPhone(values[0]);
        admin.setName(values[1]);
        admin.setPassword(values[2]);
        adminService.insertEntity(admin);
        req.getSession().setAttribute("admin",admin);
        resp.sendRedirect("index");
    }
}
