package cn.hxp.common;


import cn.hxp.service.impl.BolgTagImpl;
import cn.hxp.service.impl.BolgTypeImpl;

import javax.servlet.ServletContext;


public class CacheHelper {

	
	public static boolean fillBolgTagList(ServletContext servletContext,BolgTagImpl bolgTagImpl){
		servletContext.setAttribute("bolgTagList", bolgTagImpl.findAll());
		return true;
	}
	
	public static boolean fillBolgTypeList(ServletContext servletContext,BolgTypeImpl bolgTypeImpl){
		servletContext.setAttribute("bolgTypeList", bolgTypeImpl.findAll());
		return true;
	}
	
}
