package cn.hxp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtils {

	/*
	 * 检查字符串是否为空
	 */
	public static boolean checkIsEmpty(String str) {
		if (str == null || str.trim().length() <= 0)
			return true;

		return false;
	}

	
	/*
	 * 取出字符串最后一个
	 */
	public static String removeLast(String str) {
		return str.substring(0, str.length() - 1);
	}

	/*
	 * 判断是否是数字
	 */
	public static boolean isNumeric(String str) {
		if (checkIsEmpty(str))
			return false;

		return Pattern.compile("[0-9]*").matcher(str.trim()).matches();
	}

	// 向特定的地方插入
	public static String Stringinsert(String a, String b, int t) {
		return a.substring(0, a.length() - t) + b
				+ a.substring(a.length() - t, a.length());
	}

	// 日期格式转换
	public static String formateDate(Date date, final String formatStr) {

		if (date == null) {
			return "";
		}

		String pattern = "yyyy-MM-dd";

		if (!checkIsEmpty(formatStr)) {
			pattern = formatStr;
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	// 日期格式转换
	public static String formateDate(Date date) {
		if (date == null) {
			return "";
		}
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

}
