package cn.hxp.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	/**
     * 
     * @author wangwei JSON工具类
     * @param <T>
     * 
     */

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
    /***
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list)
    {
        JSONArray jsonArray = JSONArray.fromObject(list);

        return jsonArray.toString();
    }
    
    /***
     * 将对象序列化为JSON文本
     * @param object
     * @return
     */
    public static String toJSONString(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return jsonArray.toString();
    }
    
    /***
     * 将JSON对象数组序列化为JSON文本
     * @param jsonArray
     * @return	
     */
    public static String toJSONString(JSONArray jsonArray)
    {
        return jsonArray.toString();
    }

    /***
     * 将JSON对象序列化为JSON文本
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject)
    {
        return jsonObject.toString();
    }
    
    
    
    public static void writeJson(HttpServletResponse response, Object object) {
		if (response.getContentType() == null) {
			response.setContentType("application/json;charset=UTF-8");
		}

		try {
			objectMapper.writeValue(response.getWriter(), object);
		} catch (IOException e) {
		}

	}
}
