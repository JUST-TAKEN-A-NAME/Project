package cn.hxp.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;


/**
 * 校验码工具类
 * 
 * @author eiei
 * 
 */
public class ValidateHelper {

	public static void setImageValidateCode(HttpSession session, String validateCode, Date validateDate) {
		session.setAttribute(GlobalConstants.GLOBAL_SESSION_IMAGE_VALIDATE_CODE, validateCode);
		session.setAttribute(GlobalConstants.GLOBAL_SESSION_IMAGE_VALIDATE_DATE, validateDate);
	}
	
	public static void clearValidateCode(HttpSession session) {
		session.removeAttribute(GlobalConstants.GLOBAL_SESSION_IMAGE_VALIDATE_CODE);
		session.removeAttribute(GlobalConstants.GLOBAL_SESSION_IMAGE_VALIDATE_DATE);
	}

	public static void setImageValidateCode(HttpSession session, String validateCode) {
		setImageValidateCode(session, validateCode, new Date());
	}
	
	public static boolean matchImageValidateCode(HttpServletRequest request) {
		String validateCode = request.getParameter("captcha");
		
		if (StringUtils.isEmpty(validateCode)) {
			return false;
		}

		HttpSession session = request.getSession();
		String sessioValidateCode = (String) session.getAttribute(GlobalConstants.GLOBAL_SESSION_IMAGE_VALIDATE_CODE);
		
		// 是否为空的判断
		if (StringUtils.isEmpty(sessioValidateCode)) {
			return false;
		}
		
		if (!validateCode.equalsIgnoreCase(sessioValidateCode)) {
			return false;
		}
		
		return true;
	}
}
