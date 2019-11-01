package com.chinasoft.util.code;

/**
 * 状态码字典工具
 *
 * @author HQ
 *
 */
public class StatusCodeUtil {
	public static String getStatusMsg(String statusCode) {
		
		switch (statusCode) {
			// 公共：0~99
			case StatusCode.STATUS_SUCCESS:					// 0
				return "处理成功";
			
			case StatusCode.STATUS_ERROR:
				return "处理错误";							// 2。备注：针对服务器内部错误，通过此错误码统一捕获unchecked exception
			
			case StatusCode.STATUS_SYSTEM_BIZ:				// -1
				return "系统繁忙，请稍后再试";
			
			// 通用:1001~1999
			case StatusCode.STATUS_PARAM_FORM_ERROR:		// 1001
				return "参数格式不正确";
			
			case StatusCode.STATUS_PARAM_EMPTY:				// 1002
				return "输入参数为空";
			
			case StatusCode.STATUS_COMMON_VALIDATION_SUCCESS:	// 1003
				return "参数公共校验成功";
			
			case StatusCode.STATUS_JSON_PARSE_EXCEPTION:		// 1004。备注：JSON解析失败）
				return "参数解析失败";
			
			case StatusCode.STATUS_YMMETRIC_KEY_INVALID:		// 1005。备注：此时需要重新进行秘钥交换
				return "对称秘钥为空或不合法";
			
			case StatusCode.STATUS_SESSION_INVALID:				// 1006。备注：此时需要重新登录
				return "当前处于未登录状态，无权限执行该请求​";
			
			case StatusCode.STATUS_SESSION_KICKOUT:				// 1007。备注：此时需要重新登录
				return "该账户已离线,请重新登录";
			
			case StatusCode.STATUS_MOBILE_NUMBER_INVALID:		// 1101
				return "手机号为空或格式不合法";
			
			case StatusCode.STATUS_LONGITUDE_INVALID:			// 1102
				return "经度为空或格式不合法";
			
			case StatusCode.STATUS_LATITUDE_INVALID:			// 1103
				return "纬度为空或格式不合法";
			
			case StatusCode.STATUS_FILE_EMPTY:					// 1201
				return "上传文件为空";
			
			case StatusCode.STATUS_FILE_TOO_LARGE:				// 1202
				return "上传文件过大";
			
			case StatusCode.STATUS_FILE_WRITE_ERROR:			// 1203
				return "写文件异常";
			
			case StatusCode.STATUS_FILE_DOWNLOAD_ERROR:			// 1204
				return "文件下载失败";
			
			case StatusCode.STATUS_DB_EXCEPTION:				// 1301
				return "数据库访问异常";
			
			// 账户:2001~2999
			case StatusCode.STATUS_USER_VALID_FAIL:				// 2001
				return "用户认证失败";
			
			case StatusCode.STATUS_USER_PWD_ERROR:				// 2002
				return "密码错误";
			
			case StatusCode.STATUS_USER_FREEZE:					// 2003
				return "账号已冻结";
			
			case StatusCode.STATUS_USER_ORDER_EXIST_UNCOMPLETED:	// 2004
				return "存在未完成的订单";
			
			case StatusCode.STATUS_USER_CAR_UNMATCHED:			// 2005
				return "非本车司机";
			
			case StatusCode.STATUS_USER_WALLET_EXIST:			// 2006
				return "钱包内存在余额";
			
			case StatusCode.STATUS_USER_LOGIN_MODEL_INVALID:	// 2007
				return "登入模式不合法";
			
			case StatusCode.STATUS_USER_NAME_INVALID:			// 2008
				return "用户名无效";
			
			case StatusCode.STATUS_USER_PWD_INVALID:			// 2009
				return "用户密码无效";
			
			case StatusCode.STATUS_USER_CLIENT_TYPE_INVALID:	// 2010
				return "客户端类型无效";
			
			case StatusCode.STATUS_USER_NOT_EXIST:				// 2011
				return "用户不存在";
			
			case StatusCode.STATUS_USER_STATE_STOP:				// 2012
				return "用户已被停用";
			
			case StatusCode.STATUS_USER_LOGIN_FAIL:				// 2013
				return " 用户登陆失败";
			
			case StatusCode.STATUS_USER_LOGOUT_FAIL:			// 2014
				return "用户登出失败";
			
			case StatusCode.STATUS_USER_PASSWORD_SET_FAIL:		// 2015
				return "设置用户密码失败";
			
			case StatusCode.STATUS_USER_PROFILE_EDIT_FAIL:		// 2016
				return "修改用户资料失败";
			
			case StatusCode.STATUS_USER_DEFLIST_SET_FAIL:		// 2017
				return "设置常用地址失败";
			
			case StatusCode.STATUS_USER_PHONE_MODIFY_FAIL:		// 2018
				return "更新手机号失败";
				
			case StatusCode.STATUS_USER_HEAD_IMG_UPLOAD:		// 2019
				return "用户头像上传失败";
			
			case StatusCode.STATUS_USER_NICKNAME_NOT_BLANK:		// 2020
				return "用户昵称不能为空";
			
			case StatusCode.STATUS_USER_SEX_NOT_NULL:			// 2021
				return " 用户性别不能为空";
			
			case StatusCode.STATUS_USER_NICKNAME_LENGTH_OUT_RANGE:	// 2022
				return "用户昵称长度超出范围32个字符";
			
			case StatusCode.STATUS_USER_SEX_OUT_RANGE:			// 2023
				return "用户性别超出范围";
			
			case StatusCode.STATUS_USER_SMS_CAPTCHA_ERROR:		// 2101
				return "验证码错误";
			
			case StatusCode.STATUS_USER_SMS_SEND_FAIL:			// 2102
				return "短信发送失败";
			
			case StatusCode.STATUS_USER_SMS_CAPTCHA_EXIST:		// 2103
				return "两次验证码发送间隔时间不得低于60秒，请稍后再试";
			
			case StatusCode.STATUS_USER_SMS_CAPTCHA_SESSION_EMPTY:
				return "没有获取验证码";							// 2104。备注：此时需要重新进行验证码发送
			
			case StatusCode.STATUS_USER_SMS_CAPTCHA_EXPIRED:
				return "验证码过期";								// 2105。备注：此时需要重新进行验证码发送

			// 支付:6001~6999
			case StatusCode.STATUS_PAY_WXPAY_GET_PARAM_FAIL:		// 6001
				return "获取微信支付参数失败";
			
			case StatusCode.STATUS_PAY_ALIPAY_GET_PARAM_FAIL:		// 6002
				return "获取支付宝支付参数失败";

			case StatusCode.STATUS_VERSION_CODE_REPEAT:		// 3001
				return "版本号重复";

			case StatusCode.STATUS_USER_USERNAME_EXIST: // 2106
				return "用户名已存在";

			default: return "未知错误";
		}
	}
}
