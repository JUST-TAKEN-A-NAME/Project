package cn.hxp.common;


/**
 * 异常信息存储类
 * 
 * @author BrainstorM
 *
 */
public class WebHandleException extends BaseException {

	// 序列化
	private static final long serialVersionUID = -2147666713632365082L;
	
	/**
	 * 构造函数
	 * 
	 * @param code
	 */
	public WebHandleException(String code) {
		exceptionCode = code;
		excetionMessage = "网站内部错误";
	}

	/**
	 * 构造函数
	 * 
	 * @param code
	 * @param message
	 */
	public WebHandleException(String code, String message) {
		exceptionCode = code;
		excetionMessage = message;
	}

}
