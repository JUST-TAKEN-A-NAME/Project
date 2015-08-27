package cn.hxp.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统常量表
 * 
 * @author 
 *
 */
public class GlobalConstants {

		
	
		//用户在session的名称
		public static final String GLOBAL_SESSION_USER_ID ="USER_ID";
			
		// 图片验证码在session中的名称
		public static final String GLOBAL_SESSION_IMAGE_VALIDATE_CODE = "IMAGE_VALIDATE_CODE";
		
		// 验证码的生命周期key
		public static final String GLOBAL_SESSION_IMAGE_VALIDATE_DATE = "IMAGE_VALIDATE_DATE";
		
		// 图片校验码的有效时间（单位:秒）
		public static final int GLOBAL_SESSION_IMAGE_VALIDATE_VALID_DUR = 5 * 60;
		
		// 博客图片文件夹
		public static final String GLOBAL_BOLG_IMG_FOLDER = "E:/apache-tomcat-7.0.59/webapps/ProjectThree/UploadImg";
		
		// 博客图片上传缓冲区

		public static final String GLOBAL_BOLG_IMG_FOLDER_TEMP = "E:/apache-tomcat-7.0.59/webapps/ProjectThree/UploadImgTemp";
		
		// SESSION中的博客图片ID集合（）
		public static final List<String> GLOBAL_BOLG_IMG_ID_LIST = new ArrayList<String>();
		
		
		public static final int GLOBAL_PINGLUN_IS_BE_REPLY = 1;
		
		
		
		
		
		
		
		
		
 }