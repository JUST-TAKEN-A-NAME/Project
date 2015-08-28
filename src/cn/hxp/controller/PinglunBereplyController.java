package cn.hxp.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import cn.hxp.common.BaseController;
import cn.hxp.common.GlobalConstants;
import cn.hxp.common.PageHepler;
import cn.hxp.common.entity.PageParameter;
import cn.hxp.common.entity.PaginationeEntity;
import cn.hxp.common.entity.PinglunEntity;
import cn.hxp.entity.BolgPinglun;
import cn.hxp.entity.BolgPinglunBereply;
import cn.hxp.service.BolgPinglunBereplyBiz;
import cn.hxp.service.BolgPinglunBiz;
import cn.hxp.service.BolgUserBiz;
import cn.hxp.utils.IpAddrUtil;
import cn.hxp.utils.JsonUtils;
import cn.hxp.utils.StringUtils;

@Controller
@RequestMapping("/pinglunbereply")
public class PinglunBereplyController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PinglunBereplyController.class);
	
	
	@Resource
	public BolgPinglunBiz bolgPinglunBiz;
	
	@Resource
	public BolgUserBiz bolgUserBiz;
	
	@Resource
	public BolgPinglunBereplyBiz bolgPinglunBereplyBiz;
	
	
	@RequestMapping("/postReplyComment")
	public void postReplyComment(String toUserId,String commentId,String comment){
		HashMap<String, Object> map = new HashMap<String, Object>();

		int touserId_int = 0;
		int commentId_int = 0;
		int fromuserId = 111116874;
		
		
		//防止隐藏域的值被篡改，后台再次验证
		if(StringUtils.isNumeric(toUserId) && StringUtils.isNumeric(commentId)){
			touserId_int = Integer.parseInt(toUserId);
			commentId_int = Integer.parseInt(commentId);
			if(bolgUserBiz.selectUsertoConfim(touserId_int)!=1){
				map.put("state", "503");
				map.put("msg", "错误！");
				JsonUtils.writeJson(response, map);
				return;
			}
			if(bolgPinglunBiz.selectPingluntoConfim(commentId_int)!=1){
				map.put("state", "503");
				map.put("msg", "错误！");
				JsonUtils.writeJson(response, map);
				return;
			}
		}else{
			map.put("state", "503");
			map.put("msg", "错误！");
			JsonUtils.writeJson(response, map);
			return;
		}
		
		
		if(StringUtils.checkIsEmpty(comment)){
			map.put("state", "503");
			map.put("msg", "请输入评论内容！！");
			JsonUtils.writeJson(response, map);
			return;
		}else{
			comment = HtmlUtils.htmlEscape(comment);
		}
		
		BolgPinglunBereply entity = new BolgPinglunBereply();
		
		Timestamp now = new Timestamp(new Date().getTime());
		entity.setBeReplyDate(now);
		
		entity.setBeReplyContent(comment);
		entity.setBolgPinglunId(commentId_int);
		entity.setFromUserId(fromuserId);
		entity.setFromUserIp(IpAddrUtil.getUserIp(request));
		entity.setToUserId(touserId_int);
		
		int i = bolgPinglunBereplyBiz.newPinglunBereply(entity);
		if(i>0){
			BolgPinglun bolgPinglun = new BolgPinglun();
			bolgPinglun.setPinglunId(commentId_int);
			bolgPinglun.setPinglunIsBereply(GlobalConstants.GLOBAL_PINGLUN_IS_BE_REPLY);
			int y = bolgPinglunBiz.pinglunIsBeReply(bolgPinglun);
			if(y < 0){
				bolgPinglunBereplyBiz.deleteByPrimaryKey(entity.getBeReplyId());
				map.put("state", "503");
				map.put("msg", "错误");
				JsonUtils.writeJson(response, map);
				return;
			}
		}

		HashMap<String,String> hashmap = new HashMap<String, String>();//翻译结果容器
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hashmap = bolgUserBiz.selectImgandName(entity.getFromUserId());
		entity.setFromUserName(hashmap.get("user_name"));
		entity.setFromUserHeadImg(hashmap.get("user_head_img"));
		entity.setBeReplyDateString(sdf.format(entity.getBeReplyDate()));
		hashmap = bolgUserBiz.selectImgandName(entity.getToUserId());
		entity.setToUserName(hashmap.get("user_name"));
		
		map.put("state", "666");
		map.put("msg", "评论成功！！");
		map.put("entity", entity);
		JsonUtils.writeJson(response, map);
		
	}
	
	
}
