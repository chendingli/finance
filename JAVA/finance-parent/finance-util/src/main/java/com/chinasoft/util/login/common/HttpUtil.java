package com.chinasoft.util.login.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasoft.util.common.JsonStringUtil;
import com.chinasoft.util.login.wx.AccessToken;
import com.chinasoft.util.login.wx.WxUserDetailVo;

/**
 * @desc:HTTP/HTTPS请求通用工具类
 */
public class HttpUtil {

	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 返回微信服务器响应的信息
	 * @throws Exception
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new FsTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
			throw new RuntimeException("链接异常" + ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
			throw new RuntimeException("https请求异常" + e);
		}
	}

	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret, String code, String wxOauth2Url) {
		Args.notNull(appid, "appid");
		Args.notNull(appsecret, "appsecre");
		AccessToken token = null;
		String requestUrl = wxOauth2Url.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
		// 发起GET请求获取凭证
		String httpsRequest = null;
		try {
			httpsRequest = httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			log.error("http 请求异常 cause " + e.getMessage());
		}
		
		token = JsonStringUtil.parseJsonWithClass(httpsRequest, AccessToken.class);
		
		return token;
	}

	/**
	 * 获取微信用户资料
	 * 
	 * @param appid
	 * @param appsecret
	 * @param code
	 * @return
	 */
	public static WxUserDetailVo getWxUserDetailVo(String accessToken, String openId, String wxUserDetailUrl) {
		Args.notNull(accessToken, "accessToken");
		Args.notNull(openId, "openId");
		WxUserDetailVo vo = null;
		String requestUrl = wxUserDetailUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 发起GET请求获取凭证
		String httpsRequest = null;
		try {
			httpsRequest = httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			log.error("http 请求异常 cause " + e);
		}
		vo = JsonStringUtil.parseJsonWithClass(httpsRequest, WxUserDetailVo.class);

		return vo;
	}

	/**
	 * 刷新微信accessToken
	 * 
	 * @param appid
	 * @param refreshToken
	 * @return
	 */
	public static AccessToken refreshAccessToken(String appid, String refreshToken, String wxRefreshTokenUrl) {
		Args.notNull(appid, "appid");
		Args.notNull(refreshToken, "refreshToken");
		AccessToken vo = null;
		String requestUrl = wxRefreshTokenUrl.replace("APPID", appid).replace("REFRESH_TOKEN", refreshToken);
		// 发起GET请求获取凭证
		String httpsRequest = null;
		try {
			httpsRequest = httpsRequest(requestUrl, "GET", null);
		} catch (Exception e) {
			log.error("http 请求异常 cause " + e);
		}
		vo = JsonStringUtil.parseJsonWithClass(httpsRequest, AccessToken.class);

		return vo;
	}
	
	/**
	 * 发送Http Get 请求
	 * @param requestUrl
	 * @return
	 * @throws Exception
	 */
	public static String httpGetRequest(String requestUrl) throws Exception  {
		// 打开浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(requestUrl);
		// 回车访问
		CloseableHttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			return string;
		}
		// 关流
		if (response != null) {
			response.close();
		}
		httpClient.close();
		
		return null;
	}

	//	http://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&key=7e4f6d07ee20e17d20847186ec5b5159
	public static void main(String[] args) {
	
	}
	
	public static String urlEncodeUTF8(String source) {
		Args.notNull(source, "source");
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		}
		return result;
	}
}