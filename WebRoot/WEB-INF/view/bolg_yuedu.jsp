<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="common/imports.jsp" %>
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
    <link rel="stylesheet" href="${_cssUrl }/yuedu.css">
    <link rel="stylesheet" href="${_cssUrl }/style.css">
    <script src="${_jsUrl }/jquery-1.10.2.min.js"></script>
    <script src="${_jsUrl }/bolg_yuedu.js"></script>
    <title>${bolgInfo.bolgTitle}</title>
</head>
<style>
</style>
<body>
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
<div class="yuedu_content_box">
    <!--正文标题及信息 start--->
    <div class="box_one">
    <div class="yuedu_context_title">
    	<input type="hidden" name="bolgId" value="${bolgId}" id="bolgId" />
        <p>${bolgInfo.bolgTitle}</p>
        <div class="info">
            <span title="创建时间"><i class="fa fa-clock-o"></i>发布于<d:format formatData="${bolgInfo.bolgCreatetime}" formatPattern="yyyy-MM-dd"/></span>
            <span title="浏览人数"><i class="fa fa-eye"></i>${bolgInfo.bolgBrowse}次浏览</span>
            <span title="评论人数"><i class="fa fa-comments"></i>${bolgInfo.bolgCommentCount}条评论</span>
            <span title="喜欢人数"><i class="fa fa-heart"></i>${bolgInfo.bolgLikesCount}人喜欢</span>
            <span class="tags_span" title="标签">
	            <i class="fa fa-tags"></i>
	            <c:set value="${fn:split(bolgInfo.bolgTags, ',')}" var="bolgInfoTags"></c:set>
	            <c:forEach var="tag" items="${bolgInfoTags}">
	            	<label><d:trans dataKey="${tag}" dataSource="bolgTagList" keyColumn="tagId" valueColumn="tagName"/></label>,
	            </c:forEach>
            </span>
		</div>
    </div>
    <!--正文标题及信息 end--->
    
    
    <!--正文 start--->
    <div class="yuedu_content">
    	
    	<div id="bolg_content"></div>
    </div>
    <!--正文 end--->
    
    <!--博客信息以及博客向导 start-->
    <div class="yuedu_tags_box">
        <div class="bolg_tags_types">
            <div class="post-nav-next">
                <span style="float:left;">分享到 : </span>
                <div class="bdsharebuttonbox bdshare-button-style0-32">
                    <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博" log="type:2060,action:click,area:share,page:daily-view,to:weibo"></a>
                    <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间" log="type:2060,action:click,area:share,page:daily-view,to:qzone"></a>
                    <a href="#" class="bds_tieba" data-cmd="tieba" title="分享到百度贴吧" log="type:2060,action:click,area:share,page:daily-view,to:tieba"></a>
                    <a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网" log="type:2060,action:click,area:share,page:daily-view,to:douban"></a>
                    <a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网" log="type:2060,action:click,area:share,page:daily-view,to:renren"></a>
                    <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信" log="type:2060,action:click,area:share,page:daily-view,to:weixin"></a>
                    <a class="bds_more" data-cmd="more">更多</a>
                </div>
            </div>
            <div class="post-nav-next">
                
                
            </div>
        </div>
        <div class="post-navigation">
			<div class="post-navigation-inner">
				<div class="post-nav-prev">
					<p>上一篇:</p>
					<h4><a href="javascript:;" title="">猪八戒 ，原名沉香，后改名猎狐者，生于赫顿玛于赫顿玛于赫顿玛尔。。。</a></h4>
				</div>
                <div class="post-nav-next">
					<p>下一篇:</p>
					<h4><a href="javascript:;" title="">The Life and Works of Dieter Rams</a></h4>
				</div>
                <div class="clear"></div>
			</div>
		</div>
    </div>
    <!--博客信息以及博客向导 end-->
    </div>
    <!----差看评论 start--->
    <div class="yuedu_reply_box">
        <div class="reply_title">
            <div class="title_bgimg"><input type="hidden" id="totalCount" value="${totalCount}"><h3>共有${totalCount}条评论：</h3></div>
        </div>
        <div class="reply_context_box">
            <div class="yuedu_reply">
               
            </div>
        </div>
        <div class="newindex_pagination_box">
        	<div class="xixi">
	            <button id="toPrePage" class="a_left">上一页</button><button id="toNextPage" class="a_right"> 下一页</button>
	            <div class="div_tiaozhuan"></div>
	        </div>
	    </div>
    </div>
    <!----差看评论 end--->
</div>


<!--输入评论 start-->
<div class="comment-respond_box">
	<input type="hidden" id="lzl_reply_id" value=""/><input type="hidden" id="lzl_reply_userid" value=""/>
    <div id="respond" class="comment-respond">
        <h3 id="reply-title" class="comment-reply-title">Leave a Reply 
            <small>
                <a rel="nofollow" id="cancel-comment-reply-link" href="javascript:;" style="display:none;">Cancel reply</a>
            </small>
        </h3>
        <div id="commentform" class="comment-form">
            <p class="comment-notes">杜绝无意义回复，请用社交账号登录之后，再发表评论！</p>
            <!--<p class="comment-form-author">
                <input id="author" name="author" type="text" placeholder="你的称呼" value="" size="30">
                <label for="author">Author</label> <span class="required">*</span>
            </p>
            <p class="comment-form-email">
                <input id="email" name="email" type="text" placeholder="你的邮箱" value="" size="30">
                <label for="email">Email</label><span class="required">*</span>
            </p>-->
            <p class="comment-form-comment">
                <textarea id="comment" name="comment" cols="45" rows="6"></textarea>
            </p>						
            <p class="form-submit"><input name="submit" type="button" id="postComment" class="submit" value="发表评论"></p>					
        </div>
    </div>
</div>
<!--输入评论 end-->

    <div class="foot_box">
        <footer class="navy-bg">
            © 2015 MyFirstProject<span class="color"> | </span>Powered by <a href="###">HeXiaoPeng</a>
        </footer>
    </div>
</body>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},
"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src=
'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</html>