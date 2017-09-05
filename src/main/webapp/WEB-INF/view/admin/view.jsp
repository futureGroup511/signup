<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<title>title</title>
<script src="${staticUrl}js/jquery-3.1.1.min.js"></script>
<script>
	function changeState(id,toState) {
		var url = "changeState";
		var postData = {
		    'id':id,
		    'toState':toState
		}
		$.post(url,postData,function(data,state){
		    console.log(data)
			try{
				var resp = eval('(' + data + ')');
			}catch(e){
				alert("服务端错误！");
				location.reload();
				return;
			}
			if(resp.result == 0){
				//alert("成功！");
				location.reload()
				return
			}else{
				alert(resp.message);
				location.reload()
				return;
			}
		});
	}
	function changeMarks(id,marks){
	    console.log(id)
	    console.log(marks)
        var url = "changeMarks";
		var postData = {
		    'id':id,
		    'marks':marks
		}
		$.post(url,postData,function(data,state){
		    console.log(data)
			try{
				var resp = eval('(' + data + ')');
			}catch(e){
				alert("服务端错误！");
				location.reload();
				return;
			}
			if(resp.result == 0){
                //alert("成功！");
				location.reload()
				return
			}else{
				alert(resp.message);
				location.reload()
				return;
			}
		});
	}
</script>
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
	    <div class="am-u-sm-12">
	        <span class="am-text-success">${remind}</span>
	    </div>
	</div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<span>共找到</span><span class="am-text-warning">${pc.count }</span><span>个结果,当前</span><span
				class="am-text-warning">${pc.currPage } / ${pc.pageNum }</span><span>页</span>
			<a target="_blank" href="downloadExcel?state=${state}&search=${search}">,下载<span class="am-text-warning">${pc.count }</span>个结果的Excel</a>
		</div>
	</div>
	<!--  am-g -->
	<div class="height20"></div>
	<div class="am-g">
		<div class="am-u-sm-12">
			<div class="am-scrollable-horizontal">
				<table
					class="am-table am-table-compact am-table-bordered am-table-striped am-text-nowrap">
					<thead>
						<tr>
							<th>姓名</th>
							<th>电话</th>
							<th>QQ</th>
							<th>班级</th>
							<th>学院</th>
							<th>状态</th>
							<th>报名时间</th>
							<th>面试结果</th>
							<th>面试Ta</th>
							<th>取消面试</th>
							<th>删除</th>
							<th>备注</th>
						</tr>
					</thead>
					<c:forEach var="item" items="${pc.data }">
						<tr>
							<td>${item.name }</td>
							<td>${item.phone }</td>
							<td>${item.qq }</td>
							<td>${item.majorClass }</td>
							<td>${item.college }</td>
							<td><c:choose>
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
								</c:choose></td>
							<td>${item.signupTime }</td>
							<td><a href="interviewResult?id=${item._id }"
								style="color: #FFF;" class="am-btn am-btn-primary am-btn-xs">面试结果</a></td>
							<c:choose>
                            	<c:when test="${item.state == 0 }">
                            		<th><button class="am-btn am-btn-xs am-btn-success" onclick="changeState('${item._id}', 1)">面试Ta</button></th>
                            	    <td></td>
                            	    <th><button class="am-btn am-btn-xs am-btn-danger" onclick="changeState('${item._id}',4)">删除</button></th>
                            	</c:when>
                            	<c:when test="${item.state == 1 }">
                            	    <td></td>
                                    <th><button class="am-btn am-btn-xs am-btn-warning" onclick="changeState('${item._id}', 0)">取消面试</button></th>
                                    <th><button class="am-btn am-btn-xs am-btn-danger" onclick="changeState('${item._id}',4)">删除</button></th>
                            	</c:when>
                                <c:when test="${item.state == 4 }">
                            	    <td></td>
                                    <th></th>
                                    <th><button class="am-btn am-btn-xs am-btn-success" onclick="changeState('${item._id}',0)">取消删除至未面试</button></th>
                            	</c:when>

                            	<c:otherwise>
                                    <td></td>
                                    <td></td>
                                    <td><button class="am-btn am-btn-xs am-btn-danger" onclick="changeState('${item._id}',4)">删除</button>
                                    <button class="am-btn am-btn-xs am-btn-danger" onclick="changeState('${item._id}',1)">取消面试成绩</button></td>
                            	</c:otherwise>
                            </c:choose>
                            <td>
                                <input onchange="changeMarks('${item._id}',this.value)" value="${item.marks}" /><span>当前值：${item.marks}</span>
                            </td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
	<!-- am-g -->
	<!-- pagecut -->
	<div class="am-g">
		<div class="am-u-sm-12 am-center">
			<ul class="am-pagination">
				<li><a
					href="?state=${state }&page=${pc.prePage }&search=${search }">&laquo;</a></li>
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
							<li><a
								href="?state=${state }&page=${item }&search=${search }">${item }</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<c:if test="${pc.currPage + 3 < pc.pageNum }">
					<li><a class="disabled">...</a></li>
				</c:if>
				<c:if test="${pc.currPage +2 < pc.pageNum }">
					<li><a
						href="?state=${state }&page=${pc.pageNum }&search=${search }">${pc.pageNum }</a></li>
				</c:if>
				<li><a
					href="?state=${state }&page=${pc.nextPage }&search=${search }">&raquo;</a></li>
			</ul>
		</div>
	</div>
	<!-- am-g pagecut -->
</body>
</html>