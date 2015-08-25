package cn.hxp.tags;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class DataFormatTag extends TagSupport {
	
	//日期时区名称
	public static String zoneName = "GMT+8";
	
	//数据类型
	private String dataType;
	
	//日期格式化模型
	private String formatPattern;
	
	//格式化数据
	private Object formatData;
	
	
	public int doStartTag() throws JspException	{
		return SKIP_BODY;
	}
	
	
	public int doEndTag() throws JspException {
		
		JspWriter jspWriter = pageContext.getOut();
		
		if(formatData == null){
			return EVAL_PAGE;
		}
		
		String result = formatData.toString();
		
		if(dataType == null || dataType == "DATA") {
			result = formatDate((Date)formatData, formatPattern);
		}
		
		try {
			jspWriter.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return EVAL_PAGE;
	}
	
	
	
	
	private String formatDate(Date date,final String formatStr) {
		if(date == null){
			return null;
		}
		
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		if(formatStr != null){
			pattern = formatStr;
		}
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone(zoneName));
			return simpleDateFormat.format(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	public String getFormatPattern() {
		return formatPattern;
	}


	public void setFormatPattern(String formatPattern) {
		this.formatPattern = formatPattern;
	}


	public Object getFormatData() {
		return formatData;
	}


	public void setFormatData(Object formatData) {
		this.formatData = formatData;
	}
	
	
	
	
	
	
}
