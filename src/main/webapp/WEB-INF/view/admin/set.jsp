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
			<p class="am-text-success">${remind }</p>
			<p class="am-text-warning">${warning }</p>
		</div>
	</div>
	<hr>
	<div class="am-g">
		<div class="am-u-sm-12">
			<h2>报名状态：</h2>
			<c:if test="${canSignup}">
                已经开启报名
                <a href="?o=closeSignup" class="am-btn am-btn-danger">关闭报名</a>
			</c:if>
            <c:if test="${!canSignup}">
                已经关闭报名
                <a href="?o=openSignup" class="am-btn am-btn-success">开启报名</a>
                <h1 class="am-text-danger">报名已经关闭，学生将无法报名！</h1>
			</c:if>
		</div>
	</div>
	<hr>
	<div class="am-g">
		<div class="am-u-sm-12">
			<h2>面试打分项设置：</h2>
			<p class="am-text-warning">注意名字不要设置成一样的，我后台就不限制了,并且开始面试后就不要再设置这个了，要不然所有学生的分数标准就不一样了</p>
		</div>
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>名字</th>
							<th>类型</th>
							<th>满分</th>
							<th>更新</th>
							<th>删除</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${interviewItems }">
						<tr class="am-link-muted">
							<form method="post">
								<input type="hidden" name="id" value="${item._id }">
								<input type="hidden" name="operation" value="interviewItem">
								<td><input type="text" required="required" name="name" value="${item.name }"></td>
								<td><select name="type" required="required">
										<c:choose>
											<c:when test="${item.type == 'number' }">
												<option value="number" selected="selected">number</option>
												<option value="text">text</option>
												<option value="textarea">textarea</option>
												
											</c:when>
											<c:when test="${item.type == 'text' }">
												<option value="number">number</option>
												<option value="text" selected="selected">text</option>
												<option value="textarea">textarea</option>
												
											</c:when>
											<c:when test="${item.type == 'textarea' }">
												<option value="number">number</option>
												<option value="text">text</option>
												<option value="textarea" selected="selected">textarea</option>
											</c:when>
										</c:choose></td>
								<td><input type="number" required="required" name="perfectScore"
									value="${item.perfectScore }"></td>
								<td><button type="submit"
										class="am-btn am-btn-xs am-btn-warning">更新</button></td>
								<td>
								<a class="am-btn am-btn-xs am-btn-danger" href="?o=deleteInterviewItem&id=${item._id}" style="color:#FFF;">删除</a>
								
								</td>
							</form>
						</tr>

					</c:forEach>
					<tr class="am-link-muted">
							<form method="post" action="set">
								<input type="hidden" name="operation" value="interviewItem">
								<td><input type="text" required="required" name="name" ></td>
								<td><select name="type">
									<option value="number">number</option>
									<option value="text">text</option>
									<option value="textarea">textarea</option>
								</td>
								<td><input type="number" required="required" name="perfectScore" value="0"></td>
								<td><button type="submit"
										class="am-btn am-btn-xs am-btn-success">添加</button></td>
							</form>
						</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- am-g -->
	<hr>

</body>
</html>