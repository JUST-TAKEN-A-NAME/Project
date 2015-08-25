<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/imports.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="${_cssUrl }/font-awesome.css">
<link rel="stylesheet" href="${_cssUrl }/bootstrap.css">
<link rel="stylesheet" href="${_cssUrl }/flat-ui.css">
<link rel="stylesheet" href="${_cssUrl }/wysiwyg-editor.css">
<link rel="stylesheet" href="${_cssUrl }/style.css">
<link rel="stylesheet" href="${_cssUrl }/yulu.css">
<title>6666</title>
</head>
<body>
	<div style="position:relative;">
		<div class="suspend_choose_box">
	        <div class="suspend_tags_box">
	            <div class="tags_box_head">
	                <span style="float:left;">请选择标签：</span>
	                <span style="float:right;"><i id="close_choose_tags" class="fa fa-close"></i></span>
	            </div>
	            <div class="tags_box">
	                <ul class="tags_box_ul">
	                	<c:forEach items="${bolgTagList}" var="bolgTag">
	                		<li><span id="${bolgTag.tagId}">${bolgTag.tagName}</span><label></label></li>
	                	</c:forEach>
	                </ul>
	            </div>
	            <div class="tags_box_foot">
	                <p><span id="choose_tags_msg">最多只能选取五个标签！</span><input type="button" id="confim_tags_btn" value="确定"></p>
	            </div>
	        </div>
	    </div>
	
	
	    <div style="position:absolute; z-index:1;width:100%;">
	    <input type="hidden" name="bolgId" id="bolgId" value="${bolgId}"/>
		<div class="head_menu">
	        <nav>
	            <ul class="first_menu">
	                <li><a href="#">首页</a></li>
	                <li><a href="#">博客</a></li>
	                <li><a href="#">留言<!-- <i class="fa fa-chevron-down"></i> --></a>
	                    <!-- <ul>
	                        <li><a href="javascript:;">Web Design</a></li>
	                        <li><a href="javascript:;">User Experience</a></li>
	                    </ul> -->
	                </li>
	                <li><a href="#">关于</a></li>
	            </ul>
	        </nav>
	    </div>
		<div class="context_box">
			<div class="zwcontext">
			
		        <div style="width:100%;">
					<label style="font-size: 18px;">博客信息:
			       	<span style="font-size:12px;color: #999999;margin-left: 5px;">选择你的博客类型并且输入标题</span></label>
					<div class="zwtitle">
						<div class="is_box_select" style="float:left;">
							<select id="bolgType" data-toggle="select" class="form-control select select-inverse mrs mbm">
								<option value="">--请选择--</option>
								<c:forEach items="${bolgTypeList}" var="bolgType">
									<option value="${bolgType.typeId}">${bolgType.typeName}</option>
								</c:forEach>
							</select>
						</div>
						<input type="text" id="caonima" value="测试！！测试！！" class="textinput" placeholder="在此输入标题" />
					</div>
					<div style="">
			        	<label style="font-size: 18px;">博客简介:
			        	<span style="font-size:12px;color: #999999;margin-left: 5px;">可以用150以内的字数描述这篇博客</span></label>
		        		<textarea id="summary" style="margin: 0px; width: 100%; height: 60px;resize: none;border-radius: 4px;border: 1px solid #dddddd;    border-top: 1px solid #ebebeb;    border-bottom: 1px solid #b7b7b7;box-shadow: 0 1px 1px #d3d1d1;outline: none;font-size: 14px;height: 60px;padding: 10px 15px;">测试！！测试！！测试！！测试！！</textarea>
					</div>
		        </div>
		        
		        
		         <div style="width:100%; margin-top:30px;">
		         	<label style="font-size: 18px;">博客正文:
		        	<span style="font-size:12px;color: #999999;margin-left: 5px;">在下面输入博客正文</span></label>
					<textarea id="editor1" name="editor" placeholder="在此输入正文吧...">测试！！测试！！
					测试！！测试！！</textarea>
				</div>
				
				
				<div class="confom_tags_box">
		            <label style="font-size:18px;">博客标签：</label>
		            <label class="prompt">请给这篇博客选择标签...点击<a href="javascript:open_choose_tags();">添加</a></label>
		        </div>
		        
		        
		        
				<div class="buttondiv">
					<div class="backbtn">
						<button onclick="cancel()">取 消</button>
					</div>
					<div class="cofimbtn">
						<button class="ortherbtn">保 存 为 草 稿</button>
						<button class="ortherbtn">预 览</button>
						<button id="fabu" class="submitbtn">发 布</button>
					</div>
				</div>
			</div>
		</div>
		<div class="insertCode_box" style="display: none;">
			<div class="is_box_on">
				<div class="is_box_head">
					插入代码<i class="fa fa-times pull-right" onclick="close_insert()"></i>
				</div>
				<div class="is_box_content">
					<div class="is_box_select">
						<select data-toggle="select" class="form-control select select-inverse mrs mbm">
							<option value="1">JAVA</option>
							<option value="2">JavaScript</option>
							<option value="3">C</option>
							<option value="4">C/C++</option>
							<option value="5">C#</option>
							<option value="6">Python</option>
							<option value="7">XML</option>
						</select>
					</div>
					<div class="is_box_textarea">
						<textarea name="code" id="code" rows="10" cols="30"></textarea>
					</div>
				</div>
				<div class="is_box_foot">
					<div class="cofimbtn">
						<button class="submitbtn" onclick="is_commit()">确 定</button>
					</div>
					<div class="backbtn">
						<button onclick="close_insert()">取 消</button>
					</div>
				</div>
			</div>
		</div>
		<div class="foot_box">
	        <footer class="navy-bg">
	            © 2015 MyFirstProject<span class="color"> | </span>Powered by <a href="###">HeXiaoPeng</a>
	        </footer>
	    </div>
		</div>
		
		
		<div id="uploadFile_box" class="uploadFile_box">
			<div style="width:100%;padding: 10px;">
				<i id="removeImage" class="fa fa-close" style="display: none;"></i>
				<input type="file" style="display:none;" id="uploadImg" name="uploadImg" data-max_size="3145728"/>
				<div class="fileIcon" id="uploadimgbtn">
					<i class="fa fa-plus"></i>
				</div>
				
				<button id="confimbtn" class="confimbtn"> 确 认 </button>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="${_jsUrl }/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${_jsUrl }/wysiwyg.js"></script>
	<script type="text/javascript" src="${_jsUrl }/wysiwyg-editor.js"></script>
	<script type="text/javascript" src="${_jsUrl }/demo.js"></script>
	<script type="text/javascript" src="${_jsUrl }/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${_jsUrl }/application.js"></script>
	<script type="text/javascript" src="${_jsUrl }/flat-ui.js"></script>
	<script type="text/javascript" src="${_jsUrl }/bootstrap.min.js"></script>
</body>
</html>