package com.chinasoft.util.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 阿里大鱼客户端工具
 * @author hcf
 * @version 1.0
 * @date：2017年8月23日 上午9:31:12
 */
public class SmsSender {
	protected static Logger logger = Logger.getLogger(SmsSender.class);
	
	// 根据不同的模式，使用不同的短信模板。
	// 目前只设置了发送短信验证码，但保留其他模式，作为拓展
	public static final String VALIDATION_CODE_MODE = "validationCodeMode";		// 发送验证码
	public static final String RESET_PWD_MODE = "resetPwdMode";					// 密码重置
	public static final String NEW_PWD_MODE = "newPwdMode";						// 发送默认的新密码
	
	private String accessKeyID;
	
	private String accessKeySecret;
	
	private String validationCodeTemplateid;
	
	private String sendValidFlag;
	
	public boolean querySendValidFlag() {
		logger.info("短信发送标志位判断：" + sendValidFlag);
		if("true".equals(sendValidFlag)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 发送短信
	 * @param signName 簽名
	 * @param templateCode 模板编号
	 * @param phone 手机号
	 * @param params 消息參數
	 * @return
	 * @throws Exception
	 */
	public ApiResponse sendSms(String signName, String templateCode, String phone, Map<String, String> params) throws Exception{
		ApiResponse resp = new ApiResponse();
		resp.setSuccess(false);
		
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi";					// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";		// 短信API产品域名（接口地址固定，无需修改）

		// 初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", this.accessKeyID,
				this.accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(templateCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为 "{\"name\":\"Tom\", \"code\":\"123\"}"
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//		request.setTemplateParam(JsonStringUtil.parseToJson(params));
		request.setTemplateParam(JSON.toJSONString(params));
		// 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			resp.setSuccess(true);
			resp.setCode(sendSmsResponse.getCode());
		} else {
			resp.setError(sendSmsResponse.getMessage());
			resp.setCode(sendSmsResponse.getCode());
		}
		return resp;
	}
	
	public boolean smsMsgPost(String content, String mobile, String mode) {
		
		if(!this.querySendValidFlag()) {
			logger.info("发送标志位设为false，不发送短信");
			return false;
		}
		
		String templateid = "";				// 模板ID
		String signName = "";				// 签名
		
		//短信模板：
		//验证码${code}，您正在登录，若非本人操作，请勿泄露。
		Map<String, String> params=new HashMap<>();
		params.put("code", content);
		
		switch (mode) {
			case SmsSender.VALIDATION_CODE_MODE: 
				templateid = validationCodeTemplateid;
				signName = "深圳服务外包服务平台";
				break;
			default: templateid = validationCodeTemplateid; break;
		}
		
		try {
			ApiResponse apiResponse= this.sendSms(signName, templateid, mobile, params);
			logger.info(apiResponse);
		} catch (Exception e) {
			logger.error("短信发送失败", e);
			return false;
		}
		return true;
	}
	
	public void setAccessKeyID(String accessKeyID) {
		this.accessKeyID = accessKeyID;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	
	public void setValidationCodeTemplateid(String validationCodeTemplateid) {
		this.validationCodeTemplateid = validationCodeTemplateid;
	}
	
	public void setSendValidFlag(String sendValidFlag) {
		this.sendValidFlag = sendValidFlag;
	}
	
	public static void main(String[] args) {
		//短信模板：
		//验证码${code}，您正在登录，若非本人操作，请勿泄露。
		Map<String, String> params=new HashMap<>();
		params.put("code", "1221");
		SmsSender aplc=new SmsSender();
		try {
			ApiResponse apiResponse= aplc.sendSms("阿里云短信测试专用", "SMS_86895306", "13631345821", params);
			System.err.println(apiResponse);
		} catch (Exception e) {
			logger.error("阿里云短信测试异常", e);
		}
	}

}
