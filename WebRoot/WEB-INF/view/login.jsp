<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String resources = basePath + "resources/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="${_cssUrl }/font-awesome.css">
    <link rel="stylesheet" href="${_cssUrl }/bootstrap.css">
    <script src="${_jsUrl }/jquery-1.10.2.min.js"></script>
    <script src="${_jsUrl }/layer.js"></script>
    <script src="${_jsUrl }/login.js"></script>
    <link rel="stylesheet" href="${_cssUrl }/login.css">
    <script type="text/javascript" src="${_jsUrl }/bootstrap.min.js"></script>
    <title>登录</title>
</head>
<style>
</style>
<body>
<div class="container" style=" position: relative; z-index: 5;">
    <div class="login-page">
        <h2 class="title">
            <span>
            <a class="active" data-pjax="true" href="beforlogin">登录</a>
            <b>/</b>
            <a class="" data-pjax="true" href="beforRegister">注册</a>
            </span>
        </h2>
        <div class="sign-up">
            <form id="sign_up" class="form-horizontal" accept-charset="UTF-8" method="post">
                <div class="input-prepend">
                    <span class="add-on"><i class="fa fa-user"></i></span>
                    <input type="text" name="userName" id="sign_up_username" value="" class="span2" placeholder="账户名或邮箱">
                </div>
                <div class="input-prepend">
                    <span class="add-on"><i class="fa fa-unlock-alt"></i></span>
                    <input type="password" name="userPassword" id="sign_up_userpassword" value="" class="span2" placeholder="密码">
                </div>
                <div class="captcha">
                    <input type="text" name="captcha" id="captcha" placeholder="验证码">
                    <img id="validateImage" class="validate-image" title="点击刷新验证码" />
                </div>
                <div class="input-prepend">
                	<p class="worng"></p>
                </div>
                <div class="input-prepend">
                	<button class="btn btn-primary btn-block submitbtn" type="button"> 登&nbsp;&nbsp;录 </button>
                </div>
                <div class="control-group text-left">
                	<input type="hidden" name="checklogin" value=""/>
                    <input type="checkbox" name="remember_me" id="remember_me" value="true" class="pull-left">记住我
                    <a href="/users/password/new" class="pull-right">忘记密码</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script>
	$(document).ready(function() {
		$("#validateImage").click(function() {
			  var webPath = "/" + window.location.pathname.split("/")[1];
			  var url = webPath + "/validate/getValidateCodeImage?v=" + (new Date().getMilliseconds());
			
			  $(this).attr("src", url);
		}).click();
	
		
	});
</script>
</html>