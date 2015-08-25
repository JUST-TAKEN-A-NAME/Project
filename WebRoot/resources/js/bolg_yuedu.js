$(function(){
	window.onload = function(){
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
	};
	
	
	
	$("#postComment").click(function(){
		$(this).attr("disabled","disabled")
		postComment();
		$(this).removeAttr("disabled");
	})
	
	
});


function optenReplyBox(id){
    //先根据判断回复框是否存在，再选择是增加还是下下拉
    //#reply_box 是留言框根据留言ID 命名的 
    var replybox = $("#reply_msg_lzl_reply");
    if(replybox.length && replybox.length>0){
        if(replybox.find("label").html() == $("#"+id).find("span.user_name").text()){
            if (replybox.css("display") == "none") {
                replybox.slideDown("fast");
            }else{
                replybox.slideUp("fast");
            }
            replybox.find(".huifubox").focus();
            return false;
        }
    }
    replybox.remove();
    var html = '<div class="reply_msg_lzl_reply" id="reply_msg_lzl_reply">'+
                    '<div class="reply_box">'+
                        '<div class="huifubox" contenteditable="true">'+
                            '我 回复 <label>'+$("#"+id).find("span.user_name").text()+'</label>：'+
                        '</div>'+
                        '<div class="reply_img_btn">'+
                            '<span class="inse_smile">'+
                                //'<i class="fa fa-smile-o" title="插入表情"></i></span>'+
                            '<span style="line-height:30px;">你还可以输入100字...</span>'+
                            '<a class="replybtn" href="javascript:;">发布</a><a class="removeReplyBox" href="javascript:slideUpReplyBox();">取消</a>'+
                        '</div>'+
                    '</div>'+
                '</div>';
    $("#"+id).append(html);
    $("#reply_msg_lzl_reply").slideDown("fast");
    $("#reply_msg_lzl_reply .huifubox").focus();
    
}


function slideUpReplyBox(){
    $(".reply_msg_lzl_reply").slideUp();
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
				    	'<div class="user_info">'+
				    	'<span class="user_name">'+data.userName+'</span>'+
				    	'<span class="border_left">'+data.commentDate+'</span>'+
				    	'<span class="border_left"><a href="javascript:optenReplyBox('+data.commentId+');">回复</a></span>'+
				    	'</div>'+
				     	'<div class="reply_msg"><p>'+textareaVal+'</p></div>'+
				 	'</div>'+
				 '</div>';
				
				
				$(".yuedu_reply").append(html);
			} else {
				alert(data.msg);
			}
		}
	});
}


function loadComment(){
	var bolgId = $("#bolgId").val();
	var webPath = "/" + window.location.pathname.split("/")[1];
	$.ajax({
		type: 'POST',
		url: webPath + "/pinglun/loadComment?bolgId=" + bolgId,
		success: function(data) {
			if (data.state == 666) {
				var pinglunList = data.list;
				var html;
				$.each(pinglunList,function(i,item){
					 html = 
						'<div class="reply"><div class="user_head_img"><img src="'+item.userHeadImg+'"/></div>'+
							'<div class="reply_msg_box" id="'+item.commentId+'">'+
						    	'<div class="user_info">'+
						    	'<span class="user_name">'+item.userName+'</span>'+
						    	'<span class="border_left">发表于'+item.commentDate+'</span>'+
						    	'<span class="border_left"><a href="javascript:optenReplyBox('+item.commentId+');">回复</a></span>'+
						    	'</div>'+
						     	'<div class="reply_msg"><p>'+item.comment+'</p></div>'+
						 	'</div>'+
						 '</div>';
					 

					$(".yuedu_reply").append(html);
					 
				})
				
			} else {
				alert(data.msg);
			}
		}
	});
	
	
}
