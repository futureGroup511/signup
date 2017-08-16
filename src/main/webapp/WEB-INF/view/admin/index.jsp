<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<script src="${staticUrl }plugins/echarts/echarts.js"></script>
<title>title</title>
</head>
<body>
<jsp:include page="common/header.jsp"></jsp:include>

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
        	            data:[30, 12, 15, 67, 32, 11, 99],
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
                        {value:235, name:'未面试235'},
                        {value:274, name:'正在面试274'},
                        {value:310, name:'面试成功310'},
                        {value:335, name:'面试失败335'}
                    ]
                }
            ]
        }

        // 使用刚指定的配置项和数据显示图表。
        signup_chart.setOption(signup_chart_option);
        interview_chart.setOption(interview_operation);
        
    </script>
</body>
</html>