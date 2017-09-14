<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="common/head.jsp"></jsp:include>
<title>报名成功</title>
</head>
<body>
	<div class="height50"></div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<h3 class="am-text-success">${remind }</h3>
		</div><!-- am-u -->
		<div class="am-u-sm-12">
			<h3 class="am-text-default">你可以:</h3>
		</div><!-- am-u -->
		<div style="height:30px;"></div>
		<div class="am-u-sm-12">
			<a href="interviewTime?search=${name }">查看我的面试安排</a>
		</div><!-- am-u -->
		<div style="height:20px;"></div>
		<div class="am-u-sm-12">
			<p>或者</p>
		</div><!-- am-u -->
		<div style="height:30px;"></div>
		<div class="am-u-sm-12">
			<h3 class="page8-title">点击二维码加群</h3>
			<a target="_blank"
                   href="https://jq.qq.com/?_wv=1027&k=5UqudiN">
                    <img src="https://dev-1251811540.costj.myqcloud.com/signup/images/qz-code.png" alt="" width="220px">
            </a>
		</div><!-- am-u -->
	</div><!-- am-g -->
</body>
</html>