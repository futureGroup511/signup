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
			<p class="am-text-success">只统计状态为：面试中、面试成功、面试失败的学生</p>
		</div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<span class="am-text-success">${remind }</span>
			<span class="am-text-warning">${warning }</span>
		</div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>学生排名</th>
							<th>姓名</th>
							<th>状态</th>
							<th>得分率</th>
							<th>分数</th>
							<th>查看详细评分</th>
						</tr>
					</thead>

					<c:forEach var="item" items="${statistics }" varStatus="status">
						<tr class="am-link-muted">
							<td>${status.index +1 }</td>
							<td>${item.studentName }</td>
							<td>
							<c:choose>
                                <c:when test="${item.studentState == '面试成功'}">
                                    <span class="am-text-success">${item.studentState}</span>
                                </c:when>
                                <c:when test="${item.studentState == '面试失败'}">
                                    <span class="am-text-danger">${item.studentState}</span>
                                </c:when>
                                <c:otherwise>
                                    <span>${item.studentState}</span>
                                </c:otherwise>
                            </c:choose>
                            </td>
							<td>${item.scoreOfPerfect }</td>
							<td>${item.score }</td>
							<td><a class="am-btn am-btn-xs am-btn-success" style="color:#FFF;" href="interviewResult?id=${item.student_id }">详细评分</a></td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
	</div>
	<!--  am-g -->
	
</body>
</html>