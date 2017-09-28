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
	<!-- am-g -->
	<div class="am-g">
		
	</div>
	<div class="am-g">
		<div class="am-u-lg-8">
			<img alt="" src="https://weilai-1251811540.cosbj.myqcloud.com/bishi1/${student.name }1.jpg" style="width:100%;">
			<img alt="" src="https://weilai-1251811540.cosbj.myqcloud.com/bishi1/${student.name }2.jpg" style="width:100%;">
		</div>
		<div class="am-u-lg-4">
		<div class="am-u-sm-12">
			<p>评分：</p>
			<p class="am-text-warning">${warning }</p>
		</div>
		<form class="am-form am-form-horizontal" method="post">
			<input type="hidden" name="id" value="${student._id }">
			<c:forEach var="item" items="${interviewItems }">
				<div class="am-u-sm-12">
					<div class="am-form-group">
						<c:choose>
							<c:when test="${item.type == 'number' }">
								<label for="interviewItem-${item._id }"
									class="am-form-label">${item.name }(${item.perfectScore })</label>
								<div class="am-u-sm-12">
									<input name="${item.name }" type="number"
										id="interviewItem-${item._id }" max="${item.perfectScore }"
										min="0" value="${item.score }" placeholder="输入本项评分">
								</div>
							</c:when>
							<c:when test="${item.type == 'text' }">
								<label for="interviewItem-${item._id }">${item.name }(无分数)</label>
								<div class="am-u-sm-12">
									<input name="${item.name }" type="text"
										id="interviewItem-${item._id }" value="${item.content }"
										placeholder="输入评论">
								</div>
							</c:when>
							<c:when test="${item.type == 'textarea' }">
								<label for="interviewItem-${item._id }" >${item.name }(无分数)</label>
								<div class="am-u-sm-12">
									<textarea name="${item.name }" id="interviewItem-${item._id }"
									rows="5" placeholder="输入评论">${item.content }</textarea>
								</div>
							</c:when>

						</c:choose>
					</div>
				</div>

			</c:forEach>
			<div class="am-u-sm-12">
				<div class="am-u-sm-10 am-u-sm-offset-2">
				<p>
					<button type="submit" class="am-btn am-btn-success">提交</button>
				</p>
			</div>
			</div>
		</form>
		</div>
	</div>
	<!--  am-g -->

</body>
</html>