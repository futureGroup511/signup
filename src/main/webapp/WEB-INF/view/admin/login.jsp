<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<title>title</title>
</head>
<body>
	<div class="height50"></div>
	<div class="am-g">
		<div class="am-u-sm12 am-u-md-8 am-u-lg-6 am-u-md-centered">
			<form class="am-form" method="post">
				<fieldset>
					<legend>登录-报名系统</legend>
					<p class="am-text-warning">${warning }</p>
					<div class="am-form-group">
						<label for="account-input">账号</label> <input type="text" id="account-input" name="account" placeholder="输入账号">
					</div>
					<div class="am-form-group">
						<label for="password-input">密码</label> <input type="text" id="password-input" name="password" placeholder="输入密码">
					</div>
					<div class="am-form-group">
						<label for="vCode-input">验证码</label> <input type="text" id="vCode-input" name="vCode" placeholder="输入验证码">
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