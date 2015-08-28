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
@RequestMapping("/pinglun")
public class PinglunController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PinglunController.class);
	
	@Resource
	public BolgPinglunBereplyBiz bolgPinglunBereplyBiz;
	
	@Resource
	public BolgPinglunBiz bolgPinglunBiz;
	
	@Resource
	public BolgUserBiz bolgUserBiz;
	
	
	@RequestMapping("/postPinglun")
	public void postPinglun(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		int userId = 111116873;
		int bolgId = 0;
		//if(session.getAttribute("userId")!=null && session.getAttribute("bolgId")!=null){
		if(session.getAttribute("bolgId")!=null){
			//userId = (int)session.getAttribute("userId");
			bolgId = Integer.parseInt(session.getAttribute("bolgId").toString());
			
			String msg = request.getParameter("msg");
			
			if(!StringUtils.checkIsEmpty(msg)){
				msg = HtmlUtils.htmlEscape(msg);
				BolgPinglun bolgPinglun = new BolgPinglun();
				bolgPinglun.setPinglunBolgId(bolgId);//bolgId
				bolgPinglun.setPinglunrenId(userId);//userId
				bolgPinglun.setPinglunContent(msg);//Msg
				Timestamp now = new Timestamp(new Date().getTime());
				bolgPinglun.setPinglunDate(now);//date
				bolgPinglun.setPinglunrenIp(IpAddrUtil.getUserIp(request));//userIp
				
				bolgPinglunBiz.createNewComment(bolgPinglun);
				
				int newBolgPinglunId = bolgPinglun.getPinglunId();
				
				HashMap<String, String> usermap = bolgUserBiz.selectImgandName(userId);

				map.put("userId", userId);
				map.put("userName", usermap.get("user_name"));
				map.put("userHeadImg", usermap.get("user_head_img"));
				map.put("commentId", newBolgPinglunId);
				map.put("commentDate", StringUtils.formateDate(now));
				map.put("totalCount", bolgPinglunBiz.selectCountByBolgId(bolgId));
				
				map.put("state", "666");
			}
			
		}else{
			map.put("state", "501");
			map.put("msg", "错误！");
		}
		JsonUtils.writeJson(response, map);
	}
	
	
	@RequestMapping("loadComment")
	public void loadComment(String bolgId,String currentPage) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
//		String bolgId = request.getParameter("bolgId");
		if(StringUtils.isNumeric(bolgId)){
			int bolgId_int = Integer.parseInt(bolgId);
			
			
			int totalCount = bolgPinglunBiz.selectCountByBolgId(bolgId_int);//评论总条数
			int totalPage = totalCount / 5 + ((totalCount % 5 == 0) ? 0 : 1);//默认值是5页！
			try {
				
				if(totalCount > 0){
					List<BolgPinglun> list = new ArrayList<BolgPinglun>();//查询结果容器
					PaginationeEntity entity = new PaginationeEntity();
					
					PageParameter page = PageHepler.checkPageNum(currentPage, totalPage);//判断，检查，纠正之后返回分页实体类！
					
					entity.setBolgId(bolgId);
					entity.setPage(page);
					list = bolgPinglunBiz.selectCommentByBolgIdPage(entity);
					SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
					List<BolgPinglunBereply> bereply_list = new ArrayList<BolgPinglunBereply>();//回复楼中楼的容器
					List<PinglunEntity> resultList = new ArrayList<PinglunEntity>();//最终结果容器
					HashMap<String,String> hashmap = new HashMap<String, String>();//翻译结果容器
					//下面进行循环插值
					
					for(BolgPinglun pinglun : list){
						PinglunEntity pinglungEntity = new PinglunEntity();//实例化结果容器实体类
						
						if(pinglun.getPinglunIsBereply() == GlobalConstants.GLOBAL_PINGLUN_IS_BE_REPLY){
							bereply_list = bolgPinglunBereplyBiz.selectBereplyComment(pinglun.getPinglunId());
							if(bereply_list.size()>0){
								for(BolgPinglunBereply bolgPinglunBereply : bereply_list){
									hashmap = bolgUserBiz.selectImgandName(bolgPinglunBereply.getFromUserId());
									bolgPinglunBereply.setFromUserName(hashmap.get("user_name"));
									bolgPinglunBereply.setFromUserHeadImg(hashmap.get("user_head_img"));
									bolgPinglunBereply.setBeReplyDateString(smp.format(bolgPinglunBereply.getBeReplyDate()));
									hashmap = bolgUserBiz.selectImgandName(bolgPinglunBereply.getToUserId());
									bolgPinglunBereply.setToUserName(hashmap.get("user_name"));
								}
								pinglungEntity.setBereplyList(bereply_list);
							}
						}
						hashmap = bolgUserBiz.selectImgandName(pinglun.getPinglunrenId());
						pinglungEntity.setComment(pinglun.getPinglunContent());
						pinglungEntity.setCommentDate(smp.format(pinglun.getPinglunDate()));
						pinglungEntity.setCommentId(pinglun.getPinglunId());
						pinglungEntity.setUserHeadImg(hashmap.get("user_head_img"));
						pinglungEntity.setUserId(pinglun.getPinglunrenId());
						pinglungEntity.setUserName(hashmap.get("user_name"));
						resultList.add(pinglungEntity);
						
						//hashmap.clear();
					}
					map.put("state", "666");
					map.put("list", resultList);
					map.put("page", page);
				}else{
					map.put("state", "404");
					map.put("msg", "还没有人评论！！");	
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				map.put("state", "503");
				map.put("msg", "评论加载错误.....");
				JsonUtils.writeJson(response, map);
				return;
			}
			
		}else{
			map.put("state", "404");
			map.put("msg", "未找到这篇博客的评论！！");
			SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			logger.error("时间:"+spf.format(new Date())+",IP:["+IpAddrUtil.getUserIp(request)+"]的请求中附带的必须参数为空！");
		}
		JsonUtils.writeJson(response, map);
	}
}
