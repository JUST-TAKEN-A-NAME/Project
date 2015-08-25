<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="${_cssUrl }/newIndex.css">
    <link rel="stylesheet" href="${_cssUrl }/style.css">
    <script src="${_jsUrl }/jquery-1.10.2.min.js"></script>
    <title>博客</title>
</head>
<style>
</style>
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
                        <li><span id="8">java</span><label></label></li>
                        <li><span id="7">java</span><label></label></li>
                        <li><span id="6">java</span><label></label></li>
                        <li><span id="5">java</span><label></label></li>
                        <li><span id="4">java</span><label></label></li>
                        <li><span id="3">java</span><label></label></li>
                        <li><span id="2">java</span><label></label></li>
                        <li><span id="1">java</span><label></label></li>
                    </ul>
                </div>
                <div class="tags_box_foot">
                    <p><span id="choose_tags_msg">最多只能选取五个标签！</span><input type="button" id="confim_tags_btn" value="确定"></p>
                </div>
            </div>
        </div>
    <div style="position:absolute; z-index:1;width:100%;background: #E1E4EA;">
    <div class="head_menu">
        <nav>
            <ul class="first_menu">
                <li><a href="#">首页</a></li>
                <li><a href="#">博客</a></li>
                <li><a href="#">Articles<i class="fa fa-chevron-down"></i></a>
                    <ul>
                        <li><a href="javascript:;">Web Design</a></li>
                        <li><a href="javascript:;">User Experience</a></li>
                    </ul>
                </li>
                <li><a href="#">Inspiration</a></li>
            </ul>
        </nav>
    </div>
    <div class="search_box">
        <div class="search_box_div1"><input type="text" placeholder="输入关键字搜索。。。"/><input type="submit" value=""/></div>
        <div class="search_box_div2">
            <label>标签筛选：</label>
            <label class="prompt">你可以尝试添加标签筛选你要搜索的结果...</label>
        </div>
        <div class="search_box_div3" style="text-align:center;margin-bottom:0px;">
            <input type="button" id="choose_tags" value="点击添加标签"/>
        </div>
    </div>
    
    
    <div style="width:100%;padding:50px 0px;">
        <div class="context_box">
            <!--<input type="button" onclick="xixi()" value="woca"/>-->
            <div class="context_box_left">
                <div class="center_context_box">
                    <ul id="tiles">
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/1.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/7.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/2.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/3.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/4.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                    <div class="title_sanjiaoxing"></div>
                                </div>
                                <div class="context_img">
                                    <div class="img_sanjiaoxing"></div><img src="${_imagesUrl}/5.jpg"/>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="context_wenzhang_box">
                                <div class="context_title">
                                    <a>直到看到余佳文们，我不由得笑了，心里长长的松了一口气</a>
                                </div>
                                <div class="context_remark_box">
                                    <nav class="remark_info">
                                        <span title="作者"><i class="fa fa-user"></i>好想卤碗炸酱面</span>
                                        <span title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>
                                        <span title="博客类别"><i class="fa fa-folder"></i>技术博客</span>
                                        
                                    </nav>
                                    <div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，
                                    传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，
                                    七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div>
                                </div>
                                <div class="gotoread_btn_box">
                                    <a href="javascript:;">
                                        <span style="font-size:16px;">阅读全文</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="newindex_pagination_box">
        <div style="padding:10px; height:100px; width:100%;">
            <a href="javascript:;" class="a_left">
                <i class="fa fa-chevron-left"></i>上一页
            </a>
            <a href="javascript:;" class="a_right">
                下一页<i class="fa fa-chevron-right"></i>
            </a>
            <div class="div_tiaozhuan">
                <span>共3页</span>/<span>当前：第1页</span>/<span>跳转到第<input type="text"/>页,<input type="button" value="GO"/></span>
            </div>
        </div>
    </div>
    <div class="foot_box">
        <footer class="navy-bg">
            © 2015 MyFirstProject<span class="color"> | </span>Powered by <a href="###">HeXiaoPeng</a>
        </footer>
    </div>
    </div>
    </div>
</body>


  <script src="${_jsUrl }/jquery.wookmark.js"></script>
<script>
    
    $(function(){
	    $(".box_left_ul li a").click(function(){
	        $(".box_left_ul li a").each(function(){
	            $(this).removeClass("active");
	        })
	        $(this).addClass("active");
	    });
        $("#close_choose_tags").click(function(){
            $(".suspend_tags_box").removeClass("suspend_tags_box_after");
            $(".suspend_choose_box").css("display","none");
        })
        $("#choose_tags").click(function(){
            $(".suspend_choose_box").css("display","block");
            $(".suspend_choose_box").css("height",document.body.scrollHeight+"px");
            $(".suspend_tags_box").addClass("suspend_tags_box_after");
        })
        $(".tags_box_ul li").click(function(){
            
            if($(this).hasClass("active")){
                $(this).removeClass("active");
            }else{
                if($(".tags_box_ul li.active").length > 4){
                    $("#choose_tags_msg").addClass("show_it");
                    return false;
                }else{
                    $("#choose_tags_msg").removeClass("show_it");
                }
                $(this).addClass("active");
            }
        })
        
        $("#confim_tags_btn").click(function(){
            if($(".tags_box_ul li.active").length > 0){
                var tags_map = "";
                $(".tags_box_ul li.active span").each(function(){
                    tags_map += '<span id="'+$(this).attr("id")+'">'+$(this).text()+'<i onclick="drop_tag('+$(this).attr("id")+')" class="fa fa-close"></i></span>';
                })
                $(".search_box_div2 span").each(function(){
                    $(this).remove();
                })
                $(".prompt").css("display","none");
                $(".search_box_div2").append(tags_map);
            }
            $(".suspend_tags_box").removeClass("suspend_tags_box_after");
            $(".suspend_choose_box").css("display","none");
        });
        
        if($(window).scrollTop() > 420 ){
            $(".bolg_type_box").addClass("bolg_type_box_fix");
        }
        $(window).scroll(function(){
            if($(window).scrollTop() > 420 ){
                $(".bolg_type_box").addClass("bolg_type_box_fix");
            }else{
                $(".bolg_type_box").removeClass("bolg_type_box_fix");
            }
        });
        
        var options = {
            autoResize: true, // This will auto-update the layout when the browser window is resized.
            container: $('.center_context_box'), // Optional, used for some extra CSS styling
            offset: 20, // Optional, the distance between grid items
            itemWidth: 410, // Optional, the width of a grid item
            fillEmptySpace: false, // Optional, fill the bottom of each column with widths of flexible height
            ignoreInactiveItems: false,
            comparator: function(a, b) {
                return $(a).hasClass('inactive') && !$(b).hasClass('inactive') ? 1 : -1;
            }
        };
        var handler = $('#tiles li'),filters = $('#filters li');
 
        handler.wookmark(options);
    })
    
    
    function drop_tag(id){
        $("#"+id).remove();
        if($(".search_box_div2 span").length < 1){
            $(".prompt").css("display","inline");
        }
    }
    
    
    
    function xixi(){
        var context = 
        '<li>'+
            '<div class="context_img"><img src="${_imagesUrl}/3.jpg"/></div>'+
            '<div class="context_remark_box">'+
                '<div class="context_title">'+
                    '<a>猪八戒厉害还是孙悟空厉害？</a>'+
                '</div>'+
                '<div class="context_js">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛尔，传说中的三忍之一，曾靠着杜蕾斯完成单杀，成为明教教主后改邪归正，统一三国，'+
                '传说他还有6个弟兄被唤作金刚小葫芦，个个铜头铁臂，肥而不腻，这便是羊村的起源他父亲被逼上梁山后与爱丽丝公主生了他，此子天赋惊人，'+
                '七岁便凝结出战环领悟瞬间爆炸，闻名阿拉德大陆。</div> '+
                '<nav class="remark_info">'+
                    '<span title="作者"><i class="fa fa-user"></i>好像卤碗炸酱面</span>'+
                    '<span class="leftborder" title="发表时间"><i class="fa fa-clock-o"></i>2015-7-13</span>'+
                    '<span class="leftborder" title="点赞人数"><i class="fa fa-comment"></i>1234</span>'+
                '</nav>'+
            '</div>'+
        '</li>';
                   
        applyLayout(context);
    }
    function applyLayout($newImages){
        
        var options = {
            autoResize: true, // This will auto-update the layout when the browser window is resized.
            container: $('.center_context_box'), // Optional, used for some extra CSS styling
            offset: 10, // Optional, the distance between grid items
            itemWidth: 410 // Optional, the width of a grid item
        };
        var $tiles = $('#tiles');
        $handler = $('li', $tiles);
    
        if ($handler.wookmarkInstance) {
            $handler.wookmarkInstance.clear();
        }

        // Create a new layout handler.
        $tiles.append($newImages);
        $handler = $('li', $tiles);
        $handler.wookmark(options);
    };
</script>
</html>

</body>
</html>