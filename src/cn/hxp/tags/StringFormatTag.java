package cn.hxp.tags;

import cn.hxp.common.DataTagHelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public class StringFormatTag extends TagSupport {
	
	// 日志处理
	private static final Logger logger = LoggerFactory.getLogger(StringFormatTag.class);

	
	// 数据来源
	private String dataSource;

	// 翻译数值
	private String dataKey;
	
	// key列，dataSource为非dict时有效
	private String keyColumn;
	
	// value列，dataSource为非dict时有效
	private String valueColumn;
	
	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter jspWriter = pageContext.getOut();
		String result = dataKey;

		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			
		result = DataTagHelper.transDataFromCache(request, dataSource, dataKey, keyColumn, valueColumn);
		

		try {
			jspWriter.print(result);
		} catch (IOException e) {
			logger.info("Exception: ", e);
		}
		
		return EVAL_PAGE;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}


	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
	
	public String getKeyColumn() {
		return keyColumn;
	}

	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	public String getValueColumn() {
		return valueColumn;
	}

	public void setValueColumn(String valueColumn) {
		this.valueColumn = valueColumn;
	}
}
