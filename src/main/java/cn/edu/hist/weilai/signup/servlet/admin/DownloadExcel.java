package cn.edu.hist.weilai.signup.servlet.admin;

import cn.edu.hist.weilai.signup.entity.Student;
import cn.edu.hist.weilai.signup.entity.StudentState;
import cn.edu.hist.weilai.signup.servlet.BaseServlet;
import cn.edu.hist.weilai.signup.utils.CheckUtils;
import cn.edu.hist.weilai.signup.utils.DateUtils;
import cn.edu.hist.weilai.signup.utils.PageCut;
import cn.edu.hist.weilai.signup.utils.TextUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/downloadExcel")
public class DownloadExcel extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if(! CheckUtils.hasNull(search)) {
            search = new String(search.getBytes("iso8859-1"));
        }
        String state = req.getParameter("state");
        int stateint = TextUtils.parseInt(state,-1);
        List<Student> students = studentService.queryAll(stateint,search);
        String filename = "学生信息-"+search+ "-"+ StudentState.getState(stateint)+".xlsx";
        resp.setHeader("Content-Disposition", "attachment;filename="+ URLDecoder.decode(filename));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = xssfWorkbook.createSheet("名单");
        spreadsheet.setColumnWidth(0,2000);
        spreadsheet.setColumnWidth(1,5000);
        spreadsheet.setColumnWidth(2,5000);
        spreadsheet.setColumnWidth(3,4000);
        spreadsheet.setColumnWidth(4,4000);
        spreadsheet.setColumnWidth(5,3000);
        spreadsheet.setColumnWidth(6,6000);
        XSSFRow row = spreadsheet.createRow((short)0);

        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("电话");
        row.createCell(2).setCellValue("QQ");
        row.createCell(3).setCellValue("班级");
        row.createCell(4).setCellValue("学院");
        row.createCell(5).setCellValue("状态");
        row.createCell(6).setCellValue("报名时间");

        for(int i = 0;i<students.size();i++){
            Student student = students.get(0);
            row = spreadsheet.createRow(i+1);
            row.createCell(0).setCellValue(student.getName());
            row.createCell(1).setCellValue(student.getPhone());
            row.createCell(2).setCellValue(student.getQq());
            row.createCell(3).setCellValue(student.getMajorClass());
            row.createCell(4).setCellValue(student.getCollege());
            row.createCell(5).setCellValue(StudentState.getState(student.getState()));
            row.createCell(6).setCellValue(DateUtils.toString(student.getSignupTime(),"YYYY-MM-dd HH:mm:ss"));
        }
        xssfWorkbook.write(resp.getOutputStream());
    }
}
