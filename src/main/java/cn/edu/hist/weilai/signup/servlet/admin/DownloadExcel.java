package cn.edu.hist.weilai.signup.servlet.admin;

import cn.edu.hist.weilai.signup.entity.Admin;
import cn.edu.hist.weilai.signup.entity.Interview;
import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.entity.model.AverageScore;
import cn.edu.hist.weilai.signup.entity.model.ScoreStatistics;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.DateUtils;
import cn.edu.hist.weilai.signup.utils.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@WebServlet("/admin/downloadExcel")
public class DownloadExcel extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = req.getParameter("result");
        if("statistics".equalsIgnoreCase(result)){
            this.downloadByStatistics(req,resp);
            return;
        }
        this.downloadByStateAndSearch(req,resp);
    }
    private void downloadByStatistics(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        Set<ScoreStatistics> statistics = statisticsService.getStatistics();
        String filename = "学生成绩-统计.xlsx";
        resp.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFCellStyle cellStyle=xssfWorkbook.createCellStyle();
        cellStyle.setWrapText(true);

        XSSFSheet spreadsheet = xssfWorkbook.createSheet("名单");
        spreadsheet.setColumnWidth(0,1000);
        spreadsheet.setColumnWidth(1,2000);
        spreadsheet.setColumnWidth(2,5000);
        spreadsheet.setColumnWidth(3,3000);
        spreadsheet.setColumnWidth(4,2000);
        spreadsheet.setColumnWidth(5,2000);
        spreadsheet.setColumnWidth(6,2000);
        spreadsheet.setColumnWidth(7,10000);
        XSSFRow row = spreadsheet.createRow((short)0);
        row.createCell(0).setCellValue("排名");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("电话");
        row.createCell(3).setCellValue("状态");
        row.createCell(4).setCellValue("得分率");
        row.createCell(5).setCellValue("分数");
        row.createCell(6).setCellValue("满分");
        row.createCell(7).setCellValue("评委评论");
        int i = 1;
        for(ScoreStatistics ss:statistics){
            row = spreadsheet.createRow(i);
            row.createCell(0).setCellValue(i++);
            row.createCell(1).setCellValue(ss.getStudentName());
            row.createCell(2).setCellValue(ss.getStudentPhone());
            row.createCell(3).setCellValue(ss.getStudentState());
            row.createCell(4).setCellValue(String.format("%.2f",ss.getScoreOfPerfect()));
            row.createCell(5).setCellValue(ss.getScore());
            row.createCell(6).setCellValue(ss.getPerfectScore());
            List<Admin> admins = adminService.queryAllEntity();
            StringBuilder comment = new StringBuilder();
            XSSFCell cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            for(Admin admin:admins) {
                //用此方法获得最新的评分
                Interview iv = interviewService.getByAdminAndStudent(admin.get_id(), ss.getStudent_id());
                if(iv!=null) {
                    //对json数据的评论数据进行一次转换，前台直接显示
                    comment.append(admin.getName()).append("<").append(admin.get_id()).append(">").append("\r\n");
                    JSONObject temp = JSON.parseObject(iv.getCommentItems());
                    Set<String> keys = temp.keySet();
                    for(String key:keys) {
                        String content = (String)temp.get(key);
                        if(content != null){
                            content = content.replace("\n","\n    ");
                        }

                        comment.append("    ").append(key).append(":").append(content).append("\r\n");
                    }
                }
            }
            cell.setCellValue(comment.toString());


        }
        xssfWorkbook.write(resp.getOutputStream());

    }
    private void downloadByStateAndSearch(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        String search = req.getParameter("search");
        if(! CheckUtils.hasNull(search)) {
            search = new String(search.getBytes("iso8859-1"));
        }
        String state = req.getParameter("state");
        int stateint = TextUtils.parseInt(state,-1);
        List<Student> students = studentService.queryAll(stateint,search);
        String filename = "学生信息-"+search+ "-"+ StudentState.getState(stateint)+".xlsx";
        resp.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename,"utf-8"));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = xssfWorkbook.createSheet("名单");
        spreadsheet.setColumnWidth(0,2000);
        spreadsheet.setColumnWidth(1,5000);
        spreadsheet.setColumnWidth(2,5000);
        spreadsheet.setColumnWidth(3,4000);
        spreadsheet.setColumnWidth(4,4000);
        spreadsheet.setColumnWidth(5,3000);
        spreadsheet.setColumnWidth(6,6000);
        spreadsheet.setColumnWidth(7,8000);
        XSSFRow row = spreadsheet.createRow((short)0);

        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("电话");
        row.createCell(2).setCellValue("QQ");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("学院");
        row.createCell(5).setCellValue("状态");
        row.createCell(6).setCellValue("报名时间");
        row.createCell(7).setCellValue("面试安排");

        for(int i = 0;i<students.size();i++){
            Student student = students.get(i);
            row = spreadsheet.createRow(i+1);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getPhone());
            row.createCell(2).setCellValue(student.getQq());
            row.createCell(3).setCellValue(student.getMajorClass());
            row.createCell(4).setCellValue(student.getCollege());
            row.createCell(5).setCellValue(StudentState.getState(student.getState()));
            row.createCell(6).setCellValue(DateUtils.toString(student.getSignupTime(),"YYYY-MM-dd HH:mm:ss"));
            row.createCell(7).setCellValue(student.getInterviewTime());
        }
        xssfWorkbook.write(resp.getOutputStream());

    }

}
