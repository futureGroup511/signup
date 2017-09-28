<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${staticUrl}/plugins/amazeui/css/amazeui.min.css">
<title>面试安排</title>
</head>
<body>
	<div class="height50"></div>
	<div class="am-g">
		<div class="am-u-sm-12 am-u-md-centered">
            <p class="am-text-lg">未来小组面试安排</p>
            <p class="am-text-xs">为保障同学们信息  , 必须输入姓名全名方可查到</p>
		</div><!-- am-u -->
	</div>
	<div class="am-g">
		<form class="am-form">
        	    <div class="am-u-sm-8">
                    <input class="am-form-field am-radius" name="search" value="${search}" placeholder="输入姓名搜索" />
                </div><!-- am-u -->
                <div class="am-u-sm-4">
                        <button class="am-btn am-btn-success">搜索</button>
                </div><!-- am-u -->
        </form>
    </div>
    <div style="height:10px;"></div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <span>共找到<span class="am-text-warning">${size}</span>个结果</span>
        </div>
    </div>
    <div style="height:10px;"></div>
    <div class="am-g">
		<div class="am-u-sm-12 am-u-md-centered">
            <table class="am-table am-table-bordered am-table-striped am-table-compact">
              <thead>
              <tr>
                <th>姓名</th>
                <th>班级</th>
                <th>面试结果</th>
                <th>面试安排</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="item" items="${students}">
                  <tr>
                    <td>${item.name}</td>
                    <td>${item.majorClass}</td>
					<td>
						<c:choose>
									<c:when test="${item.state == 0 }">
										<span>未面试</span>
									</c:when>
									<c:when test="${item.state == 1 }">
										面试中
									</c:when>
									<c:when test="${item.state == 2 }">
										<span class="am-text-success">面试成功</span>
									</c:when>
									<c:when test="${item.state == 3 }">
										<span class="am-text-danger">面试失败</span>
									</c:when>
									<c:when test="${item.state == 4 }">
										<span class="am-text-warning">已经删除</span>
									</c:when>
									<c:otherwise>
										未知
									</c:otherwise>
						</c:choose>
					</td>
                    <td>${item.interviewTime}</td>
                  </tr>
              </c:forEach>
              </tbody>
            </table>
        </div><!-- am-u -->

	</div><!-- am-g -->
</body>
</html>