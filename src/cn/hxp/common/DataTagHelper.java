package cn.hxp.common;

import cn.hxp.common.entity.KeyValueEntity;
import cn.hxp.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTagHelper {

	// 日志处理
	private static final Logger logger = LoggerFactory.getLogger(DataTagHelper.class);
	
	// 字典缓存名称
	public static final String DICT_NAME = "dict";
	
	
	public static String transDataFromCache(HttpServletRequest request, String dataSource, String dataKey, String keyColumn, String valueColumn) {
		
		List<Object> dataList = DataTagHelper.getDataFromCache(request, dataSource);
		
        int dataListSize = 0;
        
        if (dataKey == null || dataList == null || (dataListSize = dataList.size()) <= 0) {
        	return dataKey;
        }

        for (int i = 0; i < dataListSize; i++) {
        	Object object = dataList.get(i);

        	if (object instanceof KeyValueEntity) {
        		KeyValueEntity keyValueEntity = (KeyValueEntity)object;
        		
            	return keyValueEntity.getValue();
        	} else {
        		try {
	        		Object key = DataTagHelper.getVariableValue(object, keyColumn);

	        		if (key != null && dataKey.equals(key.toString())) {
	        			
	        			Object value = DataTagHelper.getVariableValue(object, valueColumn);
		        		if (value != null) {
		        			return value.toString();
		        		}
	        		}
	        		
        		} catch (Exception e) {
        			logger.info("Exception: ", e);
        		}
        	}

        }

		return dataKey;
	}
	

	@SuppressWarnings("unchecked")
	public static List<Object> getDataFromCache(HttpServletRequest request, String dataSource) {
		if (dataSource == null) {
        	return null;
        }
		
		Object object = request.getAttribute(dataSource);
		
		if (object == null) {
			object = request.getSession().getAttribute(dataSource);
			
			if (object == null) {
				object = request.getSession().getServletContext().getAttribute(dataSource);
			}
		}
		
		return (List<Object>)object;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> getDataFromCache(ServletContext application, String dataSource) {
		return (List<Object>)application.getAttribute(dataSource);
	}
	
	/**
	 * 获取变量值
	 * 
	 * @param obj
	 * @param variableName
	 * @return
	 * @throws Exception
	 */
	public static Object getVariableValue(Object obj, String variableName) throws Exception {
		
		if (StringUtils.checkIsEmpty(variableName)) {
			return null;
		}
		
		Class<?> entityClass = obj.getClass();

		String methodName = "get" + Character.toUpperCase(variableName.charAt(0)) + variableName.substring(1);
		Method method = entityClass.getMethod(methodName, new Class[] {});

		return method.invoke(obj, new Object[] {});
	}
	
	
	
}
