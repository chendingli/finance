package com.chinasoft.util.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

public class AmapUtil {
	protected static Logger logger = Logger.getLogger(AmapUtil.class);
	
	private String key;
	
	// 返回输入地址address的经纬度信息, 格式是："经度,纬度"，都使用字符串存储
	public String getLonLat(String address) {
		String queryUrl = "http://restapi.amap.com/v3/geocode/geo?key=" + key + "&address="+address;
		String queryResult = getResponse(queryUrl);		// 高德接品返回的是JSON格式的字符串
		
		JSONObject jo = JSON.parseObject(queryResult);
		JSONArray ja = jo.getJSONArray("geocodes");
		return JSON.parseObject(ja.getString(0)).get("location").toString();
	}
	
	// 返回起始地startAddr与目的地endAddr之间的距离，单位：米
	public Long getDistance(String startLonLat, String endLonLat){
		Long result = new Long(0);
		String queryUrl = "http://restapi.amap.com/v3/distance?key=" + key + 
							"&origins=" + startLonLat + "&destination=" + endLonLat;
		String queryResult = getResponse(queryUrl);

		JSONObject jo = JSON.parseObject(queryResult);
		JSONArray ja = jo.getJSONArray("results");
		
		result = Long.parseLong(JSON.parseObject(ja.getString(0)).get("distance").toString());
		return result;
	}
	
	private static String getResponse(String serverUrl){
		// 用JAVA发起http请求，并返回json格式的结果
		StringBuffer result = new StringBuffer();
		try {
			URL url = new URL(serverUrl);
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while((line = in.readLine()) != null){
				result.append(line);
			}
			in.close();
			
		} catch (MalformedURLException e) {
			logger.error("解析高德地图返回值时的异常", e);
		} catch (IOException e) {
			logger.error("解析高德地图返回值时的输入输出流异常", e);
		}
		return result.toString();
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}