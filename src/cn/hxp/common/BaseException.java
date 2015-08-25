package cn.hxp.common;

/**
 * 异常信息存储类
 * 
 * @author BrainstorM
 *
 */
public class BaseException extends Exception {
	
	// 序列化
	protected static final long serialVersionUID = -5035523579171879123L;
	
	// 默认错误代码
	public static final String UNKNOWN_EXCEPTION_CODE = "-1";
	
	// 默认错误信息
	public static final String UNKNOWN_EXCEPTION_MESSAGE = "未知错误";
	
	// 错误代码
	protected String exceptionCode = UNKNOWN_EXCEPTION_CODE;
	
	// 错误信息
	protected String excetionMessage = UNKNOWN_EXCEPTION_MESSAGE;
	
	
	/**
	 * 构造函数
	 *
	 */
	public BaseException() {
	}
	
	
	/**
	 * 构造函数
	 * 
	 * @param code
	 * @param message
	 */
	public BaseException(String code, String message) {
		exceptionCode = code;
		excetionMessage = message;
	}
	
	
	/**
	 * 构造函数
	 * 
	 * @param throwable
	 */
	public BaseException(Throwable throwable) {
		excetionMessage = throwable.getMessage();
	}
	
	
	/**
	 * 设置错误代码
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		exceptionCode = code;
	}
	
	
	/**
	 * 获取错误代码
	 * 
	 * @return
	 */
	public String getCode() {
		return exceptionCode;
	}
	
	
	/**
	 * 设置错误信息
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		excetionMessage = message;
	}
	
	
	/**
	 * 获取错误信息
	 */
	public String getMessage() {
		return excetionMessage;
	}

}
