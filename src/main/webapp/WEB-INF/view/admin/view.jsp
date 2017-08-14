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
		<form class="am-form">
			<div class="am-u-sm-10 am-u-lg-11">
				<div class="am-form-group">
					<input name="search" type="text" placeholder="输入班级、姓名、电话号搜索" value="${search }">
				</div>
			</div>
			<div class="am-u-sm-2 am-u-lg-1">
				<button type="submit" class="am-btn am-btn-success">查找</button>
			</div>
		</form>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<span>共找到</span><span class="am-text-warning">${pc.count }</span><span>个结果,当前</span><span class="am-text-warning">${pc.currPage } / ${pc.pageNum }</span><span>页</span>
		</div>
	</div><!--  am-g -->
	<div class="am-g">
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
			  <table class="am-table am-table-bordered am-table-striped am-text-nowrap">
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
				<c:forEach var="item" items="${pc.data }">
					<tr class="am-link-muted">
					    <th>${item.name }</th>
					    <th>${item.phone }</th>
					    <th>${item.qq }</th>
					    <th>${item.majorClass }</th>
					    <th>${item.college }</th>
					    <th>${item.state }</th>
					    <th>${item.signupTime }</th>
				  	</tr>
				</c:forEach>
			  </table>
			</div>
		</div>
	</div><!-- am-g -->
	<!-- pagecut -->
	<div class="am-g">
		<div class="am-u-sm-12 am-center">
			<ul class="am-pagination">
				<li><a href="?page=${pc.prePage }&search=${search }">&laquo;</a></li>
				<c:if test="${pc.currPage >3 }">
					<li><a href="?page=1&search=${search }">1</a></li>
				</c:if>
				<c:if test="${pc.currPage > 4 }">
					<li><a class="disabled">...</a></li>
				</c:if>
				<c:forEach begin="${pc.currPage > 2 ? pc.currPage-2 :1 }" end="${pc.currPage +2 < pc.pageNum ? pc.currPage +2 :pc.pageNum }" var="item">
					<c:choose>
						<c:when test="${item == pc.currPage }">
							<li class="am-active"><a href="?page=${item }&search=${search }">${item }</a></li>
						</c:when>
						<c:when test="${item > 0 }">
							<li><a href="?page=${item }&search=${search }">${item }</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${pc.currPage + 3 < pc.pageNum }">
					<li><a class="disabled">...</a></li>
				</c:if>
				<c:if test="${pc.currPage +2 < pc.pageNum }">
					<li><a href="?page=${pc.pageNum }&search=${search }">${pc.pageNum }</a></li>
				</c:if>
			  	<li><a href="?page=${pc.nextPage }&search=${search }">&raquo;</a></li>
			  </ul>
		</div>
	</div><!-- am-g pagecut -->
</body>
</html>