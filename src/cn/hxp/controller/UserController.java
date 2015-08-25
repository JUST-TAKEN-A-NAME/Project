package cn.hxp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hxp.common.BaseController;
import cn.hxp.common.ValidateHelper;
import cn.hxp.entity.THxpUser;
import cn.hxp.service.THxpUserBiz;
import cn.hxp.utils.CookieUtil;
import cn.hxp.utils.JsonUtils;
import cn.hxp.utils.RequestUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private THxpUserBiz userBiz;

	@RequestMapping("beforlogin")
	public String beforlogin() {
		return "login";
	}

	@RequestMapping("beforRegister")
	public String beforregister() {
		return "register";
	}

	@RequestMapping("login")
	public void login() throws Exception {

		THxpUser user = null;
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		int ischeck = request.getParameter("checklogin") != null ? Integer
				.parseInt(request.getParameter("checklogin")) : 0;
		user = userBiz.login(username,userpwd);
		Map<String, String> map = new HashMap<String, String>();
		if (!ValidateHelper.matchImageValidateCode(request)) {
			map.put("result", "9");
			map.put("msg", "验证码输入错误!");
			ValidateHelper.clearValidateCode(session);
			JsonUtils.writeJson(response, map);
			return;
		}
		if (user == null) {
			map.put("result", "404");
			map.put("msg", "没有找到此用户！");
			JsonUtils.writeJson(response, map);
			return;
		}
		if (user.getUserLoginCount() == 0) {
			map.put("result", "500");
			map.put("msg", "你因连续输入三次错误密码账户已被锁定，请联系管理员！");
			JsonUtils.writeJson(response, map);
			return;
		}
		if (!"".equals(userpwd) && userpwd.equals(user.getUserPassword())) {
			user.setLastLogin(new Timestamp(System.currentTimeMillis()));
			user.setUserLoginCount(3);
			userBiz.updateByPrimaryKeySelective(user);
			session.setAttribute("userId", user.getUserId());
//			判断是否有保存登录之前页面
//			String last_page = RequestUtil.getSavedRequest();
//			if (!"".equals(last_page) && last_page != null) {
//				session.removeAttribute(RequestUtil.LAST_PAGE);
//				map.put("result", "9");
//				map.put("msg", "登录成功，即将为你跳转到登陆之前页面！");
//				JsonUtils.writeJson(response, map);
//				return;
//			}
			if (ischeck == 1) {
				CookieUtil.saveCookie(user, response);
			}
			map.put("result", "1");
			map.put("msg", "登录成功，即将为你跳转到首页！");
		} else {
			int logincount = user.getUserLoginCount() - 1;
			user.setUserLoginCount(logincount);
			userBiz.updateByPrimaryKeySelective(user);
			map.put("result", "0");
			map.put("msg", "账户或密码输入错误,你还有" + logincount + "次机会");
			if (logincount == 0) {
				map.put("msg", "账户已被锁定，请联系管理员！");
			}
			user.setUserLoginCount(logincount);
		}
		JsonUtils.writeJson(response, map);

	}

	@RequestMapping("register")
	public void register(THxpUser user) {
		Integer sucUserId = userBiz.insert(user);
		String userId = sucUserId.toString();
		Map<String, String> map = new HashMap<String, String>();
		if (sucUserId != 0) {
			session.setAttribute("userId", userId);
			map.put("result", "1");
		}
		JsonUtils.writeJson(response, map);
	}

	@RequestMapping("haha")
	public String haha() {

		return "register";
	}
	
	
	@RequestMapping("yulu")
	public String yulu() {
		return "yulu";
	}
	
	

	@RequestMapping("clearCookie")
	public void clearCookie() throws ServletException, IOException {
		CookieUtil.clearCookie(response);
		System.out.println("清除成功！");
		response.sendRedirect("beforregister");
	}

}