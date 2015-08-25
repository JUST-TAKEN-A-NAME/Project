$(function(){
    $("#sign_up_email").bind("blur",function(){layer.close(layer.tips());})
    $("#sign_up_username").bind("blur",function(){layer.close(layer.tips());})
    $("#sign_up_userpassword").bind("blur",function(){layer.close(layer.tips());})
    $("#captcha").bind("blur",function(){layer.close(layer.tips());})


    $(".submitbtn").click(function(){

        var szMail = $("#sign_up_email"), email_Value = $.trim(szMail.val());
        var mailReg=/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
        if(!mailReg.test(email_Value)){
            layer.tips('你输入的邮箱无效，请更正!', '#sign_up_email',{
                tips: 1,
                time:0
            });
            szMail.parent().removeClass("input-prepend");
            szMail.parent().addClass("wornginput");
            szMail.focus();
            return false;
        }else{
            layer.close(layer.tips());
            szMail.parent().addClass("input-prepend");
            szMail.parent().removeClass("wornginput");
        }

        var szName = $("#sign_up_username"), name_Value = $.trim(szName.val());
        var nameReg=/^[a-zA-Z0-9]{3,16}$/;
        if(!nameReg.test(name_Value)){
            layer.tips('请输入正确的用户名,用户名最短3个字符最长不超过16个字符!', '#sign_up_username',{
                tips: 1,
                time:0
            });
            szName.parent().removeClass("input-prepend");
            szName.parent().addClass("wornginput");
            szName.focus();
            return false;
        }else{
            layer.close(layer.tips());
            szName.parent().addClass("input-prepend");
            szName.parent().removeClass("wornginput");
        }

        var szPassWord = $("#sign_up_userpassword"), password_Value = $.trim(szPassWord.val());
        var passwordReg=/^[a-zA-Z0-9]{8,16}$/;
        if(!passwordReg.test(password_Value)){
            layer.tips('请输入正确的密码,密码最短8个字符最长不超过16个字符!', '#sign_up_userpassword',{
                tips: 1,
                time:0
            });
            szPassWord.parent().removeClass("input-prepend");
            szPassWord.parent().addClass("wornginput");
            szPassWord.focus();
            return false;
        }else{
            layer.close(layer.tips());
            szPassWord.parent().addClass("input-prepend");
            szPassWord.parent().removeClass("wornginput");
        }

        var captcha = $("#captcha"), captch_Vlue = captcha.val();
        if(captch_Vlue==""){
            layer.tips('请输入验证码!', '#captcha',{
                tips: 1,
                time:0
            });
            captcha.parent().removeClass("captcha");
            captcha.parent().addClass("worngcaptcha");
            captcha.focus();
            return false;
        }else{
            layer.close(layer.tips());
            captcha.parent().addClass("captcha");
            captcha.parent().removeClass("worngcaptcha");
        }

    });
})