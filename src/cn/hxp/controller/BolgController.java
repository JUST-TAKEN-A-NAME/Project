package cn.hxp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import cn.hxp.common.BaseController;
import cn.hxp.common.FileHelper;
import cn.hxp.common.GlobalConstants;
import cn.hxp.entity.BolgContent;
import cn.hxp.entity.BolgInfo;
import cn.hxp.service.BolgContentBiz;
import cn.hxp.service.BolgInfoBiz;
import cn.hxp.utils.JsonUtils;
import cn.hxp.utils.StringUtils;



@Controller
@RequestMapping("/bolg")
public class BolgController extends BaseController{
	
	@Resource
	private BolgInfoBiz bolgInfoBiz;
	
	@Resource
	private BolgContentBiz bolgContentBiz;

	@RequestMapping("index")
	public String index(){
		return "bolg_index";
	}
	
	@RequestMapping("yuedu")
	public String yeudu(){
		HashMap<String,String> map = new HashMap<String, String>();
		
		String bolgId = request.getParameter("bolgId");
		
		//bolgId在这里测试，设一个固定值
		bolgId = "143";
		
		
		if(bolgId != null && !bolgId.trim().equals("")){
			if(!StringUtils.isNumeric(bolgId)){
				map.put("state", "501");
				map.put("msg", "错误！！！！");
				session.setAttribute("errMsg", map);
				return "err";
			}
			BolgInfo bolgInfo = new BolgInfo();
			
			
			bolgInfo = bolgInfoBiz.selectByPrimaryKey(Integer.parseInt(bolgId));
			request.setAttribute("bolgInfo", bolgInfo);
			
			// 用户浏览这篇博客，则把bolId放到session中，如果bolgId已存在则覆盖！
			session.setAttribute("bolgId", bolgId);
			return "bolg_yuedu";
		}
		return "";
	}

	@RequestMapping("getContentByBolgId")
	public void getContentByBolgId(){
		HashMap<String, String> map = new HashMap<String, String>();
		String bolgId = request.getParameter("bolgId");
		if(bolgId != null && !bolgId.trim().equals("")){
			BolgContent bolgContent = new BolgContent();
			bolgContent = bolgContentBiz.selectByBolgInfoId(Integer.parseInt(bolgId));
			if(bolgContent != null){
				map.put("state", "666");
				map.put("content", bolgContent.getBolgContent());
			}
		}else{
			map.put("state", "404");
			map.put("msg", "未找到资源.........");	
		}
		JsonUtils.writeJson(response, map);
	}
	
	
	
	
	
	
	@RequestMapping("writeBolg")
	public String writeBolg(){
		
		// 创建博客之前，情况session中缓存的img信息
		if(session.getAttribute("uploadImgIdList")!=null){
			session.removeAttribute("uploadImgIdList");
		}
		return "yulu";
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("add")
	public void add() {
		HashMap<String,String> resultMap = new HashMap<String, String>();
		
		String Content = request.getParameter("bolgContent");
		if(StringUtils.checkIsEmpty(Content)){
			resultMap.put("-99", "请输入正文！！");
			return;
		}
		
		String bolgTitle = request.getParameter("bolgTitle");
		if(StringUtils.checkIsEmpty(bolgTitle)){
			resultMap.put("-99", "请输入标题！！");
			return;
		}else{
			bolgTitle = HtmlUtils.htmlEscape(bolgTitle);
		}
		
		String tags = request.getParameter("bolgTags");
		if(StringUtils.checkIsEmpty(tags)){
			resultMap.put("-99", "请选择博客标签！！");
			return;
		}else{
			//移除出最后一个","号
			tags = StringUtils.removeLast(tags);
		}
		
		String summmary = request.getParameter("bolgSummary");
		if(StringUtils.checkIsEmpty(summmary)){
			resultMap.put("-99", "请描述博客简介！！");
			return;
		}
		
		
		int bolgType;
		
		String type = request.getParameter("bolgType");
		if(StringUtils.checkIsEmpty(type) || type.trim() == "0"){
			resultMap.put("-99", "请选择博客类型！！");
			return;
		}else{
			bolgType = Integer.parseInt(type);
			
		}
		
		//获取或者生成一个 bolgId
		String bolgId = "";
		if(session.getAttribute("bolgId")!=null){
			
			bolgId = session.getAttribute("bolgId").toString();
			
		}else{
			String userId = "";
			
			BolgInfo bolgInfo = new BolgInfo();
			bolgInfo.setBolgUserId(userId.toString());
			bolgInfoBiz.insert(bolgInfo);
			session.setAttribute("bolgId", bolgInfo.getBolgId());
		}
		
		
		
		//获取request传过来的 imgIds
		String imgIds = request.getParameter("UploadImgIds");
		if(!StringUtils.checkIsEmpty(imgIds)){
			//移除出最后一个","号
			imgIds = StringUtils.removeLast(imgIds);
		}
		
		
		// 筛选出并删除已上传保存在缓存文件夹中，但不存在以博客ID命名的文件夹中的图片
		if(session.getAttribute("uploadImgIdList") != null){
			
			
			//SESSION中缓存的imgid
			List<String> session_uploadImg_list = new ArrayList<String>();
			session_uploadImg_list = (List<String>) request.getSession().getAttribute("uploadImgIdList");
			

			//获取request中的imgsid分割之后装进list
			String[] request_img_id = imgIds.split(",");
			List<String> request_img_id_list = new ArrayList<String>();
			for(String s : request_img_id){
				request_img_id_list.add(s);
			}
			
			List<String> missingImg = new ArrayList<String>();
			
			//进行比较,删除多余的img
			for(String s : session_uploadImg_list){
				if(!request_img_id_list.contains(s)){
					missingImg.add(s);
				}
			}
			
			// 循环删除
			for(String s :  missingImg){
				FileHelper.delFile(GlobalConstants.GLOBAL_BOLG_IMG_FOLDER+"/"+bolgId+"/"+StringUtils.Stringinsert(s, ".", 3));
			}
			
		}
		
		// 填充数据
		BolgInfo bolgInfo = new BolgInfo();
		bolgInfo.setBolgId(Integer.parseInt(bolgId));
		bolgInfo.setBolgSummary(summmary);
		bolgInfo.setBolgTitle(bolgTitle);
		bolgInfo.setBolgType(bolgType);
		bolgInfo.setBolgTags(tags);
		
		bolgInfoBiz.update(bolgInfo);
		
		int bolg_newId = bolgInfo.getBolgId();
		
		//两次数据库操作，如果上面报错会自动回滚，try块报错，try会回滚，但上面已经执行的不会回滚，故在catcg块对上面进行删除操作！
		try {
			BolgContent bolgContent = new BolgContent();
			bolgContent.setBolgContent(Content);
			bolgContent.setBolgId(bolg_newId);
			
			bolgContentBiz.insert(bolgContent);
			
		} catch (Throwable e) {
			e.printStackTrace();
			bolgInfoBiz.deleteByPrimaryKey(bolg_newId);
			resultMap.put("403", "博客暂时发布失败...");
		}
		
		session.removeAttribute("uploadImgIdList");
		
		
	}
	
	
	@RequestMapping("show")
	public String show(int id) throws Exception {
		BolgInfo bolgInfo = new BolgInfo();
		bolgInfo = bolgInfoBiz.selectByPrimaryKey(id);
		session.setAttribute("bolgInfo", bolgInfo);
		return "test";
	}
	

	@RequestMapping("insertImg")
	public void insertImg() throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		String bolgId = "";
		String imgFolder = "";
		String userId = "";
		
		if(session.getAttribute("bolgId") != null){
			bolgId = session.getAttribute("bolgId").toString();
		}else{
			BolgInfo bolgInfo = new BolgInfo();
			bolgInfo.setBolgUserId(userId);
			bolgInfoBiz.insert(bolgInfo);
			
			if(bolgInfo.getBolgId() != null || !bolgInfo.getBolgId().equals("")){
				bolgId = bolgInfo.getBolgId().toString();
				session.setAttribute("bolgId", bolgId);
			}else{
				map.put("state", "403");
				map.put("msg", "未知错误,请稍后再试！");
				JsonUtils.writeJson(response, map);
				return;
			}
		}

		imgFolder = FileHelper.createFolderByBolgId(bolgId);
		FileHelper.bolgUploadImg(request, response, imgFolder, bolgId);
	}
	
	
	@RequestMapping("cancel")
	public void cancel(){
		HashMap<String,String> map = new HashMap<String, String>();
		
		
		String bolgId = request.getParameter("bolgId");
		int bolg_id = 0;
		
		// 取消编写博客时，删除加载时已经创建好的博客信息！
		if(bolgId != null && !bolgId.trim().equals("")){
			if(bolgId.equals(session.getAttribute("bolgId").toString())){
				bolg_id = Integer.parseInt(bolgId);
				bolgInfoBiz.deleteByPrimaryKey(bolg_id);
				session.removeAttribute("bolgId");
				session.removeAttribute("uploadImgIdList");
			}
		}
		map.put("result", "666");

		JsonUtils.writeJson(response, map);
	}
	
	
	
	
}
