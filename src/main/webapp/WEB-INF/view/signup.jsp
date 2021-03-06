<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="x5-orientation" content="portrait">
    <meta name="screen-orientation" content="portrait">
    <meta name="full-screen" content="yes">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <title>在线报名-未来工作室</title>
    <link rel="shortcut icon" href="https://dev-1251811540.costj.myqcloud.com/signup/images/logo.png">
    <link rel="stylesheet" href="static/plugins/amazeui/css/amazeui.min.css">
</head>
<body>
	<div class="am-g">
		<div class="am-u-sm-12">
			<form class="am-form" id="signup-form" method="post">
			  <fieldset>
			    <legend>欢迎新同学报名</legend>
			    <p class="am-text-success">${remind }</p>
			    <p class="am-text-danger">${warning }</p>
			    <div class="am-form-group">
			      <label for="name-in">姓名</label>
			      <input type="text" class="" id="name-in" name="name" placeholder="你的姓名" required>
			    </div>
			    <div class="am-form-group">
			      <label for="majorClass-in">班级</label>
			      <input type="text" class="" id="majorClass-in" name="majorClass" placeholder="如:计科171" required>
			    </div>
			    <div class="am-form-group">
			      <label for="phone-in">手机号</label>
			      <input type="text" class="" id="phone-in" name="phone" placeholder="方便我们联系你" data-validation-message="输入地球上的手机号撒" pattern="^1[0-9]{10}$" required>
			    </div>
			    <div class="am-form-group">
			      <label for="qq-in">QQ号</label>
			      <input type="text" class="" id="qq-in" name="qq" placeholder="QQ" pattern="^\d{5,10}$" data-validation-message="QQ号码不正确?检查一下" required>
			    </div>
			  
			    <div class="am-form-group">
			      <label for="college-in">感兴趣的方向</label>
			      <select id="college-in" name="college">
			        <option value="Java Web">Java web</option>
			        <option value="前端开发">web前端</option>
			        <option value="UI设计">UI设计</option>
			      </select>
			      <span class="am-form-caret"></span>
			    </div>
			    <p><button id="signup-btn" style="width:100%;" type="submit" class="am-btn am-btn-success">报名</button></p>
			    <p><a href="interviewTime" style="display:block;width:100%;color:#FFF;" class="am-btn am-btn-primary">查看面试安排</a></p>
			  </fieldset>
			</form>
		</div>
	</div>


<script src="static/js/jquery-3.1.1.min.js"></script>
<script src="static/plugins/amazeui/js/amazeui.min.js"></script>
<script>
$(function() {
	  $('#signup-form').validator({
	    onValid: function(validity) {
	      $(validity.field).closest('.am-form-group').find('.am-alert').hide();
	    },
	    onInValid: function(validity) {
	      var $field = $(validity.field);
	      var $group = $field.closest('.am-form-group');
	      var $alert = $group.find('.am-alert');
	      // 使用自定义的提示信息 或 插件内置的提示信息
	      var msg = $field.data('validationMessage') || this.getValidationMessage(validity);

	      if (!$alert.length) {
	        $alert = $('<div class="am-alert am-alert-danger"></div>').hide().
	          appendTo($group);
	      }
	      $alert.html(msg).show();
	    }
	  });
	});
</script>
</body>
</html>
