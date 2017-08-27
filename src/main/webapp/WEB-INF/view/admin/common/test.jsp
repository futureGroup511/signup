<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
   function selectall()
   {
      var mycheckbox=document.getElementById('select1');
   var checkboxs=document.getElementsByName('aihao');
   for(var i=0;i<checkboxs.length;i++)
   {
       checkboxs[i].checked=mycheckbox.checked;
   }
   }
</script>
</head>
<body>
 爱好 <br />
 <input type="checkbox" name="aihao" value="1"/>足球
 <input type="checkbox" name="aihao" value="2"/>乒乓球
 <input type="checkbox" name="aihao" value="3"/>羽毛球
 <input type="checkbox" name="aihao" value="4"/>篮球球
 <input type="checkbox" name="aihao" value="5"/>网球 <br />
 全选<input type="checkbox" name="select" id="select1" onclick="selectall()"/>

</body>
</html>