package com.chinasoft.util.code;

public class StatusCode {
	// 公共：0~99
	public static final String STATUS_SUCCESS = "0";						// 处理成功

	public static final String STATUS_ERROR = "2";							// 处理错误：针对服务器内部错误，通过此错误码统一捕获unchecked exception
	
	@Deprecated
	public static final String STATUS_SYSTEM_BIZ = "-1";					// 系统繁忙，请稍后再试

	// 通用:1001~1999
	public static final String STATUS_PARAM_FORM_ERROR = "1001";			// 参数格式不正确
	public static final String STATUS_PARAM_EMPTY = "1002";			 		// 输入参数为空
	public static final String STATUS_COMMON_VALIDATION_SUCCESS = "1003";	// 参数公共校验成功
	public static final String STATUS_JSON_PARSE_EXCEPTION = "1004";		// JSON解析失败
	public static final String STATUS_YMMETRIC_KEY_INVALID = "1005";		// 对称秘钥为空或不合法，此时需要重新进行秘钥交换
	public static final String STATUS_SESSION_INVALID = "1006";				// 当前处于未登录状态，无权限执行该请求​，此时需要重新登录
	public static final String STATUS_SESSION_KICKOUT = "1007";		        // 账号被剔出,请重新登录
	public static final String STATUS_BUSINESS_ERROR = "1008";		        // 业务异常
	public static final String STATUS_MOBILE_NUMBER_INVALID = "1101";		// 手机号为空或格式不合法
	public static final String STATUS_LONGITUDE_INVALID = "1102";			// 经度为空或格式不合法
	public static final String STATUS_LATITUDE_INVALID = "1103";			// 纬度为空或格式不合法
	public static final String STATUS_FILE_EMPTY = "1201";					// 上传文件为空
	public static final String STATUS_FILE_TOO_LARGE = "1202";				// 上传文件过大
	public static final String STATUS_FILE_WRITE_ERROR = "1203";			// 写文件异常
	public static final String STATUS_FILE_DOWNLOAD_ERROR = "1204";			// 文件下载失败
	public static final String STATUS_DB_EXCEPTION = "1301";				// 数据库访问异常

	// 账户:2001~2999
	public static final String STATUS_USER_VALID_FAIL = "2001";					// 用户认证失败
	public static final String STATUS_USER_PWD_ERROR = "2002";					// 密码错误
	public static final String STATUS_USER_FREEZE = "2003";						// 账号已冻结
	public static final String STATUS_USER_ORDER_EXIST_UNCOMPLETED = "2004";	// 存在未完成的订单
	public static final String STATUS_USER_CAR_UNMATCHED = "2005";				// 非本车司机
	public static final String STATUS_USER_WALLET_EXIST = "2006";				// 钱包内存在余额
	public static final String STATUS_USER_LOGIN_MODEL_INVALID = "2007";		// 登入模式不合法
	public static final String STATUS_USER_NAME_INVALID = "2008";				// 用户名无效
	public static final String STATUS_USER_PWD_INVALID = "2009";				// 用户密码无效
	public static final String STATUS_USER_CLIENT_TYPE_INVALID = "2010";		// 客户端类型无效
	public static final String STATUS_USER_NOT_EXIST = "2011";					// 用户不存在
	public static final String STATUS_USER_STATE_STOP = "2012";					// 用户已被停用
	public static final String STATUS_USER_LOGIN_FAIL = "2013";					// 用户登陆失败
	public static final String STATUS_USER_LOGOUT_FAIL = "2014";				// 用户登出失败
	public static final String STATUS_USER_PASSWORD_SET_FAIL = "2015";			// 设置用户密码失败
	public static final String STATUS_USER_PROFILE_EDIT_FAIL = "2016";			// 修改用户资料失败
	public static final String STATUS_USER_DEFLIST_SET_FAIL = "2017";			// 设置常用地址失败
	public static final String STATUS_USER_PHONE_MODIFY_FAIL = "2018";			// 更新手机号失败
	public static final String STATUS_USER_HEAD_IMG_UPLOAD = "2019";			// 用户头像上传失败
	public static final String STATUS_USER_NICKNAME_NOT_BLANK = "2020";			// 用户昵称不能为空
	public static final String STATUS_USER_SEX_NOT_NULL = "2021";				// 用户性别不能为空
	public static final String STATUS_USER_NICKNAME_LENGTH_OUT_RANGE = "2022";	// 用户昵称长度超出范围
	public static final String STATUS_USER_SEX_OUT_RANGE = "2023";			    // 用户性别超出范围
	public static final String STATUS_USER_TOKEN_INVALID = "2024";	            // token失效
	public static final String STATUS_USER_OPERATE_UNAUTHORIZED = "2025";	    // 无权限访问
	public static final String STATUS_USER_SMS_CAPTCHA_ERROR = "2101";			// 验证码错误
	public static final String STATUS_USER_SMS_SEND_FAIL = "2102";				// 短信发送失败
	public static final String STATUS_USER_SMS_CAPTCHA_EXIST = "2103";			// 已发送过验证码
	public static final String STATUS_USER_SMS_CAPTCHA_SESSION_EMPTY = "2104";	// 没有获取验证码，此时需要重新进行验证码发送
	public static final String STATUS_USER_SMS_CAPTCHA_EXPIRED = "2105";		// 验证码过期，此时需要重新进行验证码发送
	public static final String STATUS_USER_USERNAME_EXIST = "2106";				// 用户名已存在

	// 支付:6001~6999
	public static final String STATUS_PAY_WXPAY_GET_PARAM_FAIL = "6001";	// 获取微信支付参数失败
	public static final String STATUS_PAY_ALIPAY_GET_PARAM_FAIL = "6002";	// 获取支付宝支付参数失败

	public static final String STATUS_VERSION_CODE_REPEAT = "3001"; // 版本号重复
}