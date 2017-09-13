<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<title>title</title>
</head>
<body>
    <jsp:include page="common/header.jsp"></jsp:include>
	<div class="height50"></div>
	<div class="am-g">
		<div class="am-u-sm12 am-u-md-8 am-u-lg-6 am-u-md-centered">
			<form class="am-form" method="post">
				<fieldset>
					<legend>更改学生</legend>
					<p class="am-text-warning">${warning }</p>
					<p class="am-text-success">${remind }</p>
					<input type="hidden" name="id" value="${student._id }">
					<div class="am-form-group">
						<label for="account-input">手机号</label> <input type="text" id="account-input" name="phone" value="${student.phone }">
					</div>
					<div class="am-form-group">
                    	<label for="password-input">姓名</label> <input type="text" id="password-input" name="name" value="${student.name }">
                    </div>
					<div class="am-form-group">
						<label for="password-input">班级</label> <input type="password" id="password-input" name="majorClass" value="${student.majorClass }">
					</div>
					<div class="am-form-group">
						<label for="vCode-input">QQ</label> <input type="text" id="vCode-input" name="qq" value="${student.qq }">
					</div>
					<div class="am-form-group">
						<label for="vCode-input">college</label> <input type="text" id="vCode-input" name="college" value="${student.college }">
					</div>
					<p>
						<button type="submit" class="am-btn am-btn-success">提交</button>
					</p>
				</fieldset>
			</form>
		</div><!-- am-u -->
	</div><!-- am-g -->
</body>
</html>