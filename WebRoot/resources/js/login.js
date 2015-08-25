$(function(){
	var index;//loading 层
	$(":text").attr("AUTOCOMPLETE","OFF");
	
	$("#sign_up_username").on({
        click:function(){
            layer.tips('请输入你的用户名!', '#sign_up_username',{
                tips: 1,
                time:0
            });
        },
        blur:function(){
            layer.close(layer.tips());
        }
    });
	
	
	
	$(".submitbtn").click(function() {
		var userName = $("#sign_up_username"), userName_val = $("#sign_up_username").val();
		if(userName_val == ""){
			layer.tips('请输入你的邮箱或用户名!', '#sign_up_username',{
	            tips: 1,
	            time:0
	        });
			userName.parent().removeClass("input-prepend");
			userName.parent().addClass("wornginput");
			userName.focus();
            return false;
		}else{
			layer.close(layer.tips());
			userName.parent().addClass("input-prepend");
			userName.parent().removeClass("wornginput");
		}
		var userPassword = $("#sign_up_userpassword"), userPasssword_val = $("#sign_up_userpassword").val();
		if(userPasssword_val == ""){
			layer.tips('请输入你的密码!', '#sign_up_userpassword',{
	            tips: 1,
	            time:0
	        });
			userPassword.parent().removeClass("input-prepend");
			userPassword.parent().addClass("wornginput");
			userPassword.focus();
            return false;
		}else{
			layer.close(layer.tips());
			userPassword.parent().addClass("input-prepend");
			userPassword.parent().removeClass("wornginput");
		}
		var vaptcha = $("#captcha"), captcha_val = $("#captcha").val();
		if(captcha_val == ""){
			layer.tips('请输入验证码!', '#captcha',{
	            tips: 1,
	            time:0
	        });
			vaptcha.parent().addClass("wornginput");
			vaptcha.focus();
            return false;
		}else{
			layer.close(layer.tips());
			vaptcha.parent().removeClass("wornginput");
		}

		var ischeck;
		if ($("#remember_me").attr("checked")) {
			ischeck=1;
		}
		
		$.ajax({
			type : 'POST',
			data : {
				username : userName_val,
				userpwd : userPasssword_val,
				captcha : captcha_val,
				checklogin : ischeck
			},
			url : "/ProjectThree/user/login?v=" + new Date().getTime(),
			success : function(data) {
				setTimeout("layer.close(layer.load())",500);
				data = eval('(' + data + ')');
				if(data.result != '1'){
					$(".worng").css('color','red');
					$(".worng").html("<i class='fa fa-frown-o'></i>"+data.msg);
					$("#validateImage").click();
				}else{
//					$(".worng").css('color','green');
//					$(".worng").html("<i class='fa fa-smile-o'></i>"+data.msg);
					setTimeout("layer.msg('登录成功，3秒后为你跳转~',{time:3000})",500);
				}
			},complete: function () {
		        $(".submitbtn").removeAttr("disabled");
		        $(".submitbtn").text("登录");
		    },beforeSend: function () {
		        // 禁用按钮防止重复提交
		        $(".submitbtn").text("登录中....");
		        $(".submitbtn").attr({ disabled: "disabled" });
		        index = layer.load(3, {
		            shade: [0.9,'#FFF'] //0.8透明度的白色背景
		        });
		    	
		    }
		});
	});
});

