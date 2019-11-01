package com.chinasoft.util.common;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

public class JsonStringUtil {
	private static Logger logger = Logger.getLogger(JsonStringUtil.class);
	
	// 定义一个json对象
	public static Gson gson = new Gson();
	
	// 将json数据转换成对应的类型数据
	public static <T> T parseJsonWithClass(String jsonData, Class<T> type) {
		T result = gson.fromJson(jsonData, type);
		return result;
	}
	
	// 将其他类型数据转换成json数据
	public static String parseToJson(Object jsonData) {
		String result = gson.toJson(jsonData);
		return result;
	}
}
