<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header id="header">
	<a class="nav-a ${navNow1 }" href="index">首页</a>
	<a class="nav-a ${navNow2 }" href="view">查看</a>
	<a class="nav-a ${navNow3 }" href="interview">面试</a>
	<a class="nav-a ${navNow3 }" href="statistics">成绩统计</a>
	<a class="nav-a ${navNow3 }" href="set">设置</a>\
	<a class="nav-a ${navNow3 }" href="register">注册新用户</a>\
	<a class="nav-a" style="float:right" href="#">管理员：${admin.name}</a>
</header>
<div class="height50"></div>
<div class="am-text-danger am-text-xxl">${globalDanger}</div>
