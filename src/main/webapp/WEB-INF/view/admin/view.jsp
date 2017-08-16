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
	<form class="am-form">
		<div class="am-g">
			<div class="am-u-sm-2">
				<div class="am-form-group">
					<select name="state" id="state-select">
						<c:choose>
							<c:when test="${state == 0 }">
								<option value="">全部</option>
								<option value="0" selected>未面试</option>
								<option value="1">面试中</option>
								<option value="2">面试成功</option>
								<option value="3">面试失败</option>
								<option value="4">已经删除</option>
							</c:when>
							<c:when test="${state == 1 }">
								<option value="">全部</option>
								<option value="0">已报名</option>
								<option value="1" selected>面试中</option>
								<option value="2">面试成功</option>
								<option value="3">面试失败</option>
								<option value="4">已经删除</option>
							</c:when>
							<c:when test="${state == 2 }">
								<option value="">全部</option>
								<option value="0">已报名</option>
								<option value="1">面试中</option>
								<option value="2" selected>面试成功</option>
								<option value="3">面试失败</option>
								<option value="4">已经删除</option>
							</c:when>
							<c:when test="${state == 3 }">
								<option value="">全部</option>
								<option value="0">已报名</option>
								<option value="1">面试中</option>
								<option value="2">面试成功</option>
								<option value="3" selected>面试失败</option>
								<option value="4">已经删除</option>
							</c:when>
							<c:when test="${state == 4 }">
								<option value="">全部</option>
								<option value="0">已报名</option>
								<option value="1">面试中</option>
								<option value="2">面试成功</option>
								<option value="3">面试失败</option>
								<option value="4" selected>已经删除</option>
							</c:when>
							<c:otherwise>
								<option value="">全部</option>
								<option value="0">未面试</option>
								<option value="1">面试中</option>
								<option value="2">面试成功</option>
								<option value="3">面试失败</option>
								<option value="4">已经删除</option>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
			</div>
			<div class="am-u-sm-8 am-u-lg-9">
				<div class="am-form-group">
					<input name="search" type="text" placeholder="输入姓名、电话、QQ、班级、院系搜索"
						value="${search }">
				</div>
			</div>
			<div class="am-u-sm-2 am-u-lg-1">
				<button type="submit" class="am-btn am-btn-success">查找</button>
			</div>
		</div>
	</form>
	<div class="am-g">
		<div class="am-u-sm-6">
			<span>共找到</span><span class="am-text-warning">${pc.count }</span><span>个结果,当前</span><span
				class="am-text-warning">${pc.currPage } / ${pc.pageNum }</span><span>页</span>
		</div>
	</div>
	<!--  am-g -->
	<div class="height20"></div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>选择</th>
							<th>姓名</th>
							<th>电话</th>
							<th>QQ</th>
							<th>班级</th>
							<th>学院</th>
							<th>状态</th>
							<th>报名时间</th>
							<th>面试结果</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${pc.data }">
						<tr class="am-link-muted">
							<th><input type="checkbox" name="choose"
								value="${item._id }"></th>
							<th>${item.name }</th>
							<th>${item.phone }</th>
							<th>${item.qq }</th>
							<th>${item.majorClass }</th>
							<th>${item.college }</th>
							<th>
								<c:choose>
									<c:when test="${item.state == 0 }">
										未面试
									</c:when>
									<c:when test="${item.state == 1 }">
										面试中
									</c:when>
									<c:when test="${item.state == 2 }">
										面试成功
									</c:when>
									<c:when test="${item.state == 3 }">
										面试失败
									</c:when>
									<c:when test="${item.state == 4 }">
										已经删除
									</c:when>
									<c:otherwise>
										未知
									</c:otherwise>
								</c:choose>
							</th>
							<th>${item.signupTime }</th>
							<th><a href="interviewResult?id=${item._id }" style="color:#FFF;" class="am-btn am-btn-primary am-btn-xs">面试结果</a></th>
						</tr>
					</c:forEach>
					<tr>
						<th>
							<button type="button" class="am-btn am-btn-success am-btn-xs">全选</button>
						</th>
						<th>
							<button type="button" class="am-btn am-btn-success am-btn-xs">面试</button>
						</th>
						<th>
							<button type="button" class="am-btn am-btn-danger am-btn-xs">删除</button>
						</th>

					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- am-g -->
	<!-- pagecut -->
	<div class="am-g">
		<div class="am-u-sm-12 am-center">
			<ul class="am-pagination">
				<li><a href="?state=${state }&page=${pc.prePage }&search=${search }">&laquo;</a></li>
				<c:if test="${pc.currPage >3 }">
					<li><a href="?state=${state }&page=1&search=${search }">1</a></li>
				</c:if>
				<c:if test="${pc.currPage > 4 }">
					<li><a class="disabled">...</a></li>
				</c:if>
				<c:forEach begin="${pc.currPage > 2 ? pc.currPage-2 :1 }"
					end="${pc.currPage +2 < pc.pageNum ? pc.currPage +2 :pc.pageNum }"
					var="item">
					<c:choose>
						<c:when test="${item == pc.currPage }">
							<li class="am-active"><a
								href="?state=${state }&page=${item }&search=${search }">${item }</a></li>
						</c:when>
						<c:when test="${item > 0 }">
							<li><a href="?state=${state }&page=${item }&search=${search }">${item }</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${pc.currPage + 3 < pc.pageNum }">
					<li><a class="disabled">...</a></li>
				</c:if>
				<c:if test="${pc.currPage +2 < pc.pageNum }">
					<li><a href="?state=${state }&page=${pc.pageNum }&search=${search }">${pc.pageNum }</a></li>
				</c:if>
				<li><a href="?state=${state }&page=${pc.nextPage }&search=${search }">&raquo;</a></li>
			</ul>
		</div>
	</div>
	<!-- am-g pagecut -->
</body>
</html>