<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<%-- 
<script src="${staticUrl }plugins/echarts/echarts.js"></script>
 --%>
<title>title</title>
</head>
<body>
<jsp:include page="common/header.jsp"></jsp:include>
<h1>首页加载太慢，关闭了</h1>
<!-- 
<div class="am-g">
	<div class="am-u-sm-8">
		<div id="signup-chart" style="width:100%;height:500px;"></div>
	</div>
	<div class="am-u-sm-4">
		<div id="interview-chart" style="width:100%;height:500px;"></div>
	</div>
</div>
<hr>
<div class="height20"></div>
<div class="am-g">
    <div class="am-u-sm-12">
    	<h2>实时信息</h2>
    	<p>在线人数：${onlineNum}，报名人数：${signupNum}，总访问量：${visitNum}</p>
    </div>
    <div class="am-u-sm-4">
        <h4>报名日志：</h4>
        <table class="am-table am-table-bordered am-table-striped am-table-compact">
          <thead>
              <tr>
                <th>IP</th>
                <th>姓名</th>
                <th>时间</th>
              </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${signupLogs }">
			    <tr class="am-link-muted">
				   <th>${item.ip }</th>
				   <th>${item.name }</th>
				   <th>${item.signupTime }</th>
				</tr>
			</c:forEach>
          </tbody>
        </table>
    </div>
    <div class="am-u-sm-8">
        <h4>访问日志：</h4>

        <table class="am-table am-table-bordered am-table-striped am-table-compact">
          <thead>
          <tr>
            <th>IP</th>
            <th>电脑用户</th>
            <th>网址</th>
            <th>时间</th>
          </tr>
          </thead>
          <tbody>
                <c:forEach var="item" items="${visitLogs }">
					<tr class="am-link-muted">
					    <th>${item.ip }</th>
					    <th>${item.host }</th>
					    <th>${item.uri }</th>
					    <th>${item.visitTime }</th>
				  	</tr>
				</c:forEach>
          </tbody>
        </table>

    </div>
</div>
<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var signup_chart = echarts.init(document.getElementById('signup-chart'));
        var interview_chart = echarts.init(document.getElementById('interview-chart'));

        // 指定图表的配置项和数据
        var signup_chart_option = {
        	    title: {
        	        text: '一周报名人数'
        	    },
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['人数']
        	    },
        	    toolbox: {
        	        show: true,
        	        feature: {
        	            dataZoom: {
        	                yAxisIndex: 'none'
        	            },
        	            dataView: {readOnly: false},
        	            magicType: {type: ['line', 'bar']},
        	            restore: {},
        	            saveAsImage: {}
        	        }
        	    },
        	    xAxis:  {
        	        type: 'category',
        	        boundaryGap: false,
        	        data: ['周一','周二','周三','周四','周五','周六','周日']
        	    },
        	    yAxis: {
        	        type: 'value',
        	        axisLabel: {
        	            formatter: '{value}人'
        	        }
        	    },
        	    series: [
        	        {
        	            name:'人数',
        	            type:'line',
        	            data: ${weakCount},
        	            markPoint: {
        	               
        	            },
        	            markLine: {
        	                data: [
        	                    {type: 'average', name: '平均值'}
        	                ]
        	            }
        	        }
        	    ]
        	};

        var interview_operation = {
        	title: {
            	text: '面试情况'
            },
            series : [
                {
                    name: '面试情况',
                    type: 'pie',
                    radius: '55%',
                    data:[
                        {value:${NORMAL}, name:'未面试${NORMAL}'},
                        {value:${INTERVIEW}, name:'正在面试${INTERVIEW}'},
                        {value:${INTERVIEW_SUCCESS}, name:'面试成功${INTERVIEW_SUCCESS}'},
                        {value:${INTERVIEW_FAIL}, name:'面试失败${INTERVIEW_FAIL} '}
                    ]
                }
            ]
        }

        // 使用刚指定的配置项和数据显示图表。
        signup_chart.setOption(signup_chart_option);
        interview_chart.setOption(interview_operation);
        
    </script>
     -->
</body>
</html>