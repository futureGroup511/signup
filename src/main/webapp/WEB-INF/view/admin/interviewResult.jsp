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
	<div class="height20"></div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<span class="am-text-success">${remind }</span>
			<span class="am-text-warning">${warning }</span>
		</div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<p>学生信息：</p>
		</div>
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>姓名</th>
							<th>电话</th>
							<th>QQ</th>
							<th>班级</th>
							<th>学院</th>
							<th>状态</th>
							<th>报名时间</th>
						</tr>
					</thead>
					<tr class="am-link-muted">
						<th>${student.name }</th>
						<th>${student.phone }</th>
						<th>${student.qq }</th>
						<th>${student.majorClass }</th>
						<th>${student.college }</th>
						<th>
								<c:choose>
									<c:when test="${student.state == 0 }">
										未面试
									</c:when>
									<c:when test="${student.state == 1 }">
										面试中
									</c:when>
									<c:when test="${student.state == 2 }">
										面试成功
									</c:when>
									<c:when test="${student.state == 3 }">
										面试失败
									</c:when>
									<c:when test="${student.state == 4 }">
										已经删除
									</c:when>
									<c:otherwise>
										未知
									</c:otherwise>
								</c:choose>
							</th>
						<th>${student.signupTime }</th>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!--  am-g -->
	<div class="am-g">
		<div class="am-u-sm-12">
			<p>评委评分：</p>
		</div>
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>评委姓名</th>
							<th>得分比</th>
							<th>得分</th>
							<th>总分</th>
							<th>评分细节</th>
						</tr>
					</thead>
					<tr>
							<th class="am-text-warning">平均分</th>
							<th class="am-text-warning">${averageScore.score / averageScore.perfectScore }</th>
							<th class="am-text-warning">${averageScore.score / averageScore.adminNum }</th>
							<th class="am-text-warning">${averageScore.perfectScore / averageScore.adminNum }</th>
						</tr>
					<c:forEach var="item" items="${adminInterviews }">
						<tr class="am-link-muted">
							<td>${item.adminName }</td>
							<td>${item.score / item.perfectScore }</td>
							<th>${item.score }</th>
							<td>${item.perfectScore }</td>
							<td>${item.commentItems }</td>
						</tr>						
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<a href="?o=pass&id=${student._id }" class="am-btn am-btn-success" style="display:inline-block;width:100%;color:#FFF;">通过面试</a>
		</div>
	</div>
	<div class="height20"></div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<a href="?o=refuse&id=${student._id }" class="am-btn am-btn-danger" style="display:inline-block;width:100%;color:#FFF;">面试失败</a>
		</div>
	</div>
</body>
</html>