package cn.hxp.common;

import cn.hxp.service.impl.BolgTagImpl;
import cn.hxp.service.impl.BolgTypeImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * @author HeXiaoPeng
 * @date:2015-6-24 下午6:29:54
 * @version :
 *
 */
@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {
	
	public void init(ServletConfig config){
		
		ServletContext servletContext = config.getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		
		String contextPath = servletContext.getContextPath();
		String resourcesPath = contextPath + "/resources";
		
		servletContext.setAttribute("_Weburl", contextPath);
		servletContext.setAttribute("_cssUrl", resourcesPath + "/css");
		servletContext.setAttribute("_jsUrl", resourcesPath + "/js");
		servletContext.setAttribute("_imagesUrl", resourcesPath + "/images");
		System.out.println("初始化完成！");
		
		
		//System.out.println(BolgTagImpl.class.getName()); 为什么getBean（xxx.class.getName） 报mapper未找到。。
		BolgTagImpl bolgTagImpl = (BolgTagImpl)applicationContext.getBean("bolgTagImpl"); 
		CacheHelper.fillBolgTagList(servletContext, bolgTagImpl);
		

		BolgTypeImpl bolgTypeImpl = (BolgTypeImpl)applicationContext.getBean("bolgTypeImpl");
		CacheHelper.fillBolgTypeList(servletContext,bolgTypeImpl);
				
		
	}
}
