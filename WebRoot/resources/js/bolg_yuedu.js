$(function(){
	
	initBolg();
	
	$("#postComment").click(function(){
		$(this).attr("disabled","disabled");
		postComment();
		$(this).removeAttr("disabled");
	});

	$(".xixi button").click(function(){
		if($(this).hasClass("a_left")){
			loadingStyle();
			setTimeout(returnGetComment($("#bolgId").val(),$("#prePage").val()),1000);
		}else if($(this).hasClass("a_right")){
			loadingStyle();
			setTimeout(returnGetComment($("#bolgId").val(),$("#nextPage").val()),1000);
			
		}
	});
	
});
function initBolg(){
	var webPath = "/" + window.location.pathname.split("/")[1];
	var bolgId = $("#bolgId").val();
	$.ajax({
		type: 'POST',
		url: webPath + "/bolg/getContentByBolgId?bolgId=" + bolgId,
		success: function(data) {
			if (data.state == 666) {
				var end = "<div class='end'>------------------------END------------------------</div>";
				$("#bolg_content").html(data.content + end);
			} else {
				alert(data.msg);
			}
		}
	});

	loadComment();
}


function returnGetComment(bolgId,currentPage){//这里设置一个返回一个不带参数的函数，用来解决setTimeOut中方法的带参数问题！！！
	return function(){
		ajaxGetComment(bolgId,currentPage);
	};
}

function ajaxGetComment(bolgId,currentPage){
	var webPath = "/" + window.location.pathname.split("/")[1];
	$.ajax({
		type: 'POST',
		data:{bolgId:bolgId,currentPage:currentPage},
		url: webPath + "/pinglun/loadComment",
		success: function(data) {
			resolveComment(data.list,data.page);
			$("html,body").animate({scrollTop:$(".reply_title").offset().top},700);
		}
	});
}

function openReplyBox(id){//这是回复评论  （父级）此处ID的值是评论表的主键
	var userId = $(".user_info").find("input[type='hidden']").val();
	var name = $("#"+id).find("span.user_name").text();
	resolveReplyBox(id,name,userId);
}


function openReplyBoxInLZL(id){//这是回复评论中的楼中楼评论  （子级 ） 此处ID的值是评论表Bereply的主键
	
	var div = $("#"+id);
	
	var comment_id = div.closest(".reply_msg_box").attr("id");//从当前开始向上匹配一个class=“xxx”的div 并获取ID
	var userId = div.find("input[type='hidden']").val();//获取div里面的隐藏域的值
	var name =div.find("span.huifuzhe").text();
	resolveReplyBox(comment_id,name,userId);
}



function resolveReplyBox(id,toUserName,userId){
    //先根据判断回复框是否存在，再选择是增加还是下来
    //#reply_box 是留言框根据留言ID 命名的 
    var replybox = $("#reply_msg_lzl_reply");
    if(replybox.length && replybox.length>0){
        if($("#lzl_reply_id").val() == id){
            if (replybox.css("display") == "none") {
                replybox.slideDown("fast");
                replybox.find(".huifubox").focus();
            }else{
                replybox.slideUp("fast");
            }
            return false;
        }else{
        	if(replybox.find(".huifubox").val()==""){
        	    replybox.remove();
        	}else{
        		if(confirm("确定要放弃已经在编辑的评论吗？")){
            	    replybox.remove();
            	}
        	}
        }
    }
    $("#lzl_reply_id").val(id);
    $("#lzl_reply_userid").val(userId);
    var html = '<div class="reply_msg_lzl_reply" id="reply_msg_lzl_reply">'+
                    '<div class="reply_box">'+
                        '<textarea onkeyup="checkLength(this)" class="huifubox"></textarea>'+
                        '<div class="reply_img_btn"><span id="inputMsg" style="display:none;float:left;">你还可以输入100个字节！</span>'+
                            '<a class="replybtn" href="javascript:ajaxReplyComment();">发布</a><a class="removeReplyBox" href="javascript:slideUpReplyBox();">取消</a>'+
                        '</div>'+
                    '</div>'+
                '</div>';
    $("#"+id).append(html);
    $("#reply_msg_lzl_reply").slideDown("fast");
    $("#reply_msg_lzl_reply .huifubox").focus();
    
}

function slideUpReplyBox(){
    $(".reply_msg_lzl_reply").slideUp();
    $(".reply_msg_lzl_reply").remove();
}

function postComment(){

	var webPath = "/" + window.location.pathname.split("/")[1];
	var msg = $("#comment").val();
	if(msg == null || msg == ""){
		return false;
		
	}
	
	var textareaVal = $("#comment").val();
	$.ajax({
		type: 'POST',
		data:{msg:msg},
		url: webPath + "/pinglun/postPinglun?v=" + new Date().getTime(),
		success: function(data) {
			if (data.state == 666) {
				alert("发表成功！");
				
				var html = 
				'<div class="reply"><div class="user_head_img"><img src="'+data.userHeadImg+'"/></div>'+
					'<div class="reply_msg_box" id="'+data.commentId+'">'+
				    	'<div class="user_info"><input type="hidden" value="'+data.userId+'"/>'+
				    	'<span class="user_name">'+data.userName+'</span>'+
				    	'<span class="border_left">'+data.commentDate+'</span>'+
				    	'<span class="border_left"><a href="javascript:openReplyBox('+data.commentId+');">回复</a></span>'+
				    	'</div>'+
				     	'<div class="reply_msg"><p>'+textareaVal+'</p></div>'+
				 	'</div>'+
				 '</div>';
				if(data.totalCount != null){
					$(".title_bgimg").find("span.totalCount").text(data.totalCount);
				}
				$(".yuedu_reply").css("display","none");
				if($(".nocomment").length > 0){
					$(".yuedu_reply").html(html);
				}else{
					$(".yuedu_reply").append(html);
				}
				$(".yuedu_reply").fadeIn();
				$("textarea").val("");
				
			} else {
				alert(data.msg);
			}
		}
	});
}


function loadComment(){
	loadingStyle();
	var bolgId = $("#bolgId").val();
	var webPath = "/" + window.location.pathname.split("/")[1];
	$.ajax({
		type: 'POST',
		data:{bolgId:bolgId},
		url: webPath + "/pinglun/loadComment",
		success: function(data) {
			if (data.state == 666) {
				resolveComment(data.list,data.page);
				
			} else if(data.state == 404){
				var html = '<div class="nocomment"><h3>暂无评论</h3></div>';
				$(".yuedu_reply").html(html);
				$(".newindex_pagination_box").css("display","none");
				
			} else {
				
				alert(data.msg);
			}
		}
	});
	
	
}
function resolveComment(list,page){
	var html = "";
	$.each(list,function(i,item){
		html += 
			'<div class="reply"><div class="user_head_img"><img src="'+item.userHeadImg+'"/></div>'+
				'<div class="reply_msg_box" id="'+item.commentId+'">'+
			    	'<div class="user_info"><input type="hidden" value="'+item.userId+'"/><span class="user_name">'+item.userName+'</span>'+
			    	'<span class="border_left">发表于'+item.commentDate+'</span><span class="border_left"><a href="javascript:openReplyBox('+item.commentId+');">回复</a></span>'+
			    	'</div><div class="reply_msg"><p>'+item.comment+'</p></div><div class="lzl_bg">';
		if(item.bereplyList!=null){
			$.each(item.bereplyList,function(i,item2){
				html +='<div class="reply_msg_lzl" id="'+item2.beReplyId+'"><input type="hidden" value="'+item2.fromUserId+'"/><div class="reply_msg_lzl_info"><img src="'+item2.fromUserHeadImg+'"><span class="huifuzhe">'+item2.fromUserName+'</span>回复：'+
			        	'<span class="beihuifu">'+item2.toUserName+'</span><span class="border_left">发表于'+item2.beReplyDateString+'</span><span class="border_left">'+
			        	'<a href="javascript:openReplyBoxInLZL('+item2.beReplyId+');">回复</a></span></div><p>'+item2.beReplyContent+'</p></div>';
			});
		}
        html += 
			 	'</div></div></div>';
	});

	
	$(".yuedu_reply").css("display","none");
	$(".yuedu_reply").html(html);
	$(".yuedu_reply").fadeIn();
	
	changePageState(page.currentPage,page.totalPage);
	
	var page = '<input type="hidden" id="prePage" value="'+page.prePage+'"/><input type="hidden" id="nextPage" value="'+page.nextPage+'"/>'+
	'<span>共'+page.totalPage+'页</span>/<span>当前：第'+page.currentPage+'页</span>/<span>跳转到第<input type="text"/>页,<input type="button" value="GO"/></span>';
	$(".div_tiaozhuan").html(page);
	
}




function changePageState(currentPage,totalPage){
	if(totalPage == 1){
		disabledLink($("#toNextPage"));
		disabledLink($("#toPrePage"));
	}else if(currentPage == totalPage){
		disabledLink($("#toNextPage"));
		enableLink($("#toPrePage"));
	}else if(currentPage == 1){
		disabledLink($("#toPrePage"));
		enableLink($("#toNextPage"));
	}else{
		enableLink($("#toPrePage"));
		enableLink($("#toNextPage"));
	}
}

//禁用a标签
function disabledLink(link){
	//增加disabledLink样式
	link.addClass("disabledLink");
	link.removeClass("buttonHover");
	//设置disabled属性
	link.attr("disabled", "disabled");
}

//恢复a标签
function enableLink(link){
	//删除disabledLink样式
	link.removeClass("disabledLink");
	link.addClass("buttonHover");
	//设置disabled属性
	link.removeAttr("disabled");
}


function loadingStyle(){
	var html ='<div class="loadbox"><div class="cssload-box-loading"></div><p>loading....</p></div>';
	$(".yuedu_reply").html(html);
}
function hideLoading(){
	$(".yuedu_reply").html("");
	clearTimeout(t);
}

//判断输入总字数！
function checkLength (textarea)
{
    var resultCount = text_count(textarea.value);
    var size = document.getElementById ("inputMsg");
    size.innerHTML = size.innerHTML.replace(/(\+|\-)?\d+/, 400 - resultCount);
    if(400 - resultCount < 10){
    	$("#inputMsg").css("display","block");	
    }else{
    	$("#inputMsg").css("display","none");
    }
}
 
function text_count (value)
{
    var text = value.split (""),len = 0;
    for ( var i = 0; i < text.length; i++)
    {
        var code = Number (text[i].charCodeAt (0));
        code > 127?len += 2:len++;
    }
    return len;
}


function ajaxReplyComment(){
	var toUserId = $("#lzl_reply_userid"),toUserIdVal = toUserId.val().trim();
	var commentId = $("#lzl_reply_id"),commentIdVal = commentId.val().trim();
	var comment = $(".huifubox"),commentVal = comment.val().trim();

	if(toUserIdVal == null || toUserIdVal == ""){
		alert("toUserIdVal 为空！");
		return false;
	}
	if(commentIdVal == null || commentIdVal == ""){
		alert("commentIdVal 为空！");
		return false;
	}
	if(commentVal == null || commentVal == ""){
		alert("说点什么吧！");
		return false;
	}

	var webPath = "/" + window.location.pathname.split("/")[1];
	$.ajax({
		type: 'POST',
		data:{toUserId:toUserIdVal,commentId:commentIdVal,comment:commentVal},
		url: webPath + "/pinglunbereply/postReplyComment?v=" + new Date().getTime(),
		success: function(data) {
			if (data.state == 666) {
				$("#lzl_reply_userid").val("");
				$("#lzl_reply_id").val("");
			    $("#reply_msg_lzl_reply").remove();
			    if(data.entity != null){
			    	var item2 = data.entity;
			    	var html ='<div class="reply_msg_lzl" id="'+item2.beReplyId+'" style="display:none;"><input type="hidden" value="'+item2.fromUserId+'"/><div class="reply_msg_lzl_info">'+
			    		'<img src="'+item2.fromUserHeadImg+'"><span class="huifuzhe">'+item2.fromUserName+'</span>回复：<span class="beihuifu">'+item2.toUserName+'</span>'+
			    		'<span class="border_left">发表于'+item2.beReplyDateString+'</span><span class="border_left"><a href="javascript:openReplyBoxInLZL('+item2.beReplyId+');">回复</a></span></div><p>'+item2.beReplyContent+'</p></div>';
			    	
			    	$("#"+commentIdVal).append(html);
			    	$("#"+commentIdVal+" div:last-child").fadeIn();
			    }
			} else {
				alert(data.msg);
			}
		}
	});
	
	
	
}




