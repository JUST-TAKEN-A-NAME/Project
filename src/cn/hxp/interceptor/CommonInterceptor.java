/**
 * 
 */
package cn.hxp.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import cn.hxp.entity.THxpUser;
import cn.hxp.service.THxpUserBiz;
import cn.hxp.utils.CookieUtil;
import cn.hxp.utils.RequestUtil;

/**
 * @author tfj 2014-8-1
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);
	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Resource
	private THxpUserBiz userBiz;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		if ("GET".equalsIgnoreCase(request.getMethod())) {
//			RequestUtil.saveRequest();
//		}
		//定义map存储结果集合
		Map<String,String> falg = new HashMap<String, String>();
		
		//从session中查找userID，判断是否已登录
		
		String userId = (String) request.getSession().getAttribute("userId");
		
		if(!"".equals(userId) && userId != null){
			return true;
		}
		
		//查找cookie，判断用户是否设置了自动登录
		Cookie cookies[] = request.getCookies();
		String cookieValue = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (CookieUtil.cookieDomainName.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		if(cookieValue == null){
			RequestUtil.saveRequest();
			System.out.println(RequestUtil.getSavedRequest());
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(
					request, response);
			return false;
		}
		
		// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
		String cookieValueAfterDecode = new String(Base64.decode(cookieValue),"utf-8");
		String cookieValues[] = cookieValueAfterDecode.split(":");
		
		//从数据库更加用户名查找用户
		THxpUser user = userBiz.login(cookieValues[0],cookieValues[1]);
		if(user == null){
			return false;
		}else{
			falg = CookieUtil.readCookieAndLogon(request, response,user,cookieValues);
		}
		if (falg.get("result").equals("3")) {
			request.setAttribute("msg", "登录成功！");
			return true;
		} else{
			RequestUtil.saveRequest();
			System.out.println(RequestUtil.getSavedRequest());
			request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(
					request, response);
			return false;
		}
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
