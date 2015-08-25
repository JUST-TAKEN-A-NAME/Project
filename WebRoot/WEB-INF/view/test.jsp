<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="${_cssUrl }/font-awesome.css">
<link rel="stylesheet" href="${_cssUrl }/bootstrap.css">
<link rel="stylesheet" href="${_cssUrl }/wysiwyg-editor.css">
<link rel="stylesheet" href="${_cssUrl }/yulu.css">
<link rel="stylesheet" href="${_cssUrl }/flat-ui.css">
<title>6666</title>
</head>
<body>
		
		${bolgInfo.bolgTitle}
		<!-- <div class="headBg_two"></div> -->
		<div class="context_box">
		${bolgInfo.bolgSummary}
		</div>
</body>
</html>