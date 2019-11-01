package com.chinasoft.util.code;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 常量定义
 *
 * @apiNote 定义常量时,先查找有无常量,再向指定位置添加
 */
public class Const {
	/**
	 * 1.类型状态
	 */
	public static final int PAY_TYPE_BALANCEPAY = 0;			// 支付方式:0余额支付
	public static final int PAY_TYPE_WXPAY = 1;					// 支付方式:1微信
	public static final int PAY_TYPE_ALIPAY = 2;				// 支付方式:2支付宝
	
	public static final int SEX_UNKNOW = 1;						// 性别:1无区分
	public static final int SEX_MALE = 2;						// 性别:2男
	public static final int SEX_FEFEMALE = 3;					// 性别:3女
	
	public static final int CLIENT_TYPE_CUSTOMER= 1;			// 版本类型:1乘客
	public static final int CLIENT_TYPE_DRIVER= 2;				// 版本类型:2乘客
	
	public static final int CLIENT_TYPE_ANDROID = 1;			// 客户端类型：1android
	public static final int CLIENT_TYPE_IOS = 2;				// 客户端类型：2ios
	public static final int CLIENT_TYPE_TERMINAL = 3;			// 客户端类型：3终端
	
	public static final int CLIENT_TYPE_APP = 1;				// 登录身份区分:1APP端
	public static final int CLIENT_TYPE_EMPLOYEE = 2;			// 登录身份区分:2运维端人员
	
	public static final int LOG_OPER_TYPE_ADD = 1;				// 操作类型：1添加
	public static final int LOG_OPER_TYPE_MODIFY = 2;			// 操作类型：2修改
	public static final int LOG_OPER_TYPE_DELETE = 3;			// 操作类型：3删除
	public static final int LOG_OPER_TYPE_QUERY = 4;			// 操作类型：4查询
	public static final int LOG_OPER_TYPE_LOGIN = 5;			// 操作类型：5登录
	public static final int LOG_OPER_TYPE_LOGOUT = 6;			// 操作类型：6登出
	
	public static final Integer LOGIN_MODEL_PWD = 1;				// 登录方式:1密码登录
	public static final Integer LOGIN_MODEL_VALIDATE = 2;			// 登录方式:2验证码登录
	
	public static final Integer ACCOUNT_DISABLE = 0;				// 账号状态:0冻结
	public static final Integer ACCOUNT_ENABLE = 1;					// 账号状态:1启用
	
	public static final int PAY_STATUS_RUNNING = 1;					// 支付状态:1进行中
	public static final int PAY_STATUS_SUCCESS = 2;					// 支付状态:2成功
	public static final int PAY_STATUS_FAIL = 3;					// 支付状态:3失败
	
	public static final int TRADE_SUCCESS = 1;						// 交易状态:1支付成功
	public static final int TRADE_FINISHED = 2;						// 交易状态:2交易已完成
	public static final int TRADE_CLOSED = 3; 						// 交易状态:3交易关闭
	
	public static final int TIME_TYPE_RUNTIME = 1;					// 时间类型:1实时
	public static final int TIME_TYPE_RESERVATION = 2;				// 时间类型:2预约
	
	public static final int DRAW_TYPE_DAISHOULI= 0;					// 余额提现申请状态:0待受理
	public static final int DRAW_TYPE_YISHOULI= 1;					// 余额提现申请状态:1已受理
	public static final int DRAW_TYPE_BOHUI= 2 ;					// 余额提现申请状态:2驳回
	public static final int DRAW_TYPE_CHENGGONG= 3 ;				// 余额提现申请状态:3打款成功
	public static final int DRAW_TYPE_SHIBAI= 4 ;					// 余额提现申请状态:4打款失败
	
	public static final int REMITS_WAITING = 0 ;					// 余额提现受理记录状态:0 待打款
	public static final int REMITS_ALREADY= 1 ;						// 余额提现受理记录状态:1 已打款
	
	public static final int CUSTOMER_TYPE_TAXI = 1;					// 乘客类型: 1出租车乘客
	public static final int CUSTOMER_TYPE_BUS = 2;					// 乘客类型: 2公车乘客
	
	/**
	 * 3.Session字段
	 */
	public static final String CUSTOMER_VALID_CODE_SESSION = "CustomerValidCodeSession";	// APP用户登录时，发送验证码
	public static final String CUSTOMER_DETAIL_SESSION = "CustomerDetailSession";			// APP用户登录后，用于存储用户信息
	public static final String USER_LOGIN_SESSION = "UserLoginSession";						// 运维端用户登录后，用于存储用户登录时从页面传输的信息
	public static final String EMPLOY_DETAIL_SESSION = "EmployeeDetailSession";				// 运维端用户登录后，用于存储运维用户信息
	
	/**
	 * 4.缓存字段
	 */
	public static final String REDIS_GUARD_CUSTOMER_SESSION_CACHE = "guard:customer_session_cache";//customer的session缓存
	public static final String REDIS_GUARD_CUSTOMER_SESSION_KICKOUT_CACHE = "guard:customer_session_kickout_cache";//customer被踢的session缓存
	public static final String SYS_CONFIG = "sys_config";									// 系统配置
	public static final String WAIT_TO_ASSIGN_ORDER_LIST = "wait_to_assign_order_list";		// 待派单集合
	public static final String PAY_LOG_CONUPDATE_LOCK = "pay_log_conupdate_lock"; 			// 支付记录同步更新数据锁
	public static final String ORDER_PAY_INFO = "order_pay_info";							// 订单支付信息
	public static final String GENERATE_ORDER_CODE_KEY = "generate_order_code_key";			// 订单号生成码
	
	/**
	 * 5.数据库表名
	 */
	public static final String MONGODB_APP_HEAD_RESOURCES_TABLE = "app_head_pic_resources";	// MongoDB-App用户头像资源表
	public static final String MONGODB_CLIENT_RESOURCES_TABLE = "client_resources";			// MongoDB-客户端、设备端版本资源表
	
	/**
	 * 6.消息提示或内容
	 */
	public static final String CUSTOMER_CANCEL_ORDER = "取消行程";							// 订单取消
	public static final String JPUSH_TITELE_MESSAGE = "公共框架";								// 极光推送标题
	public static final String PUSH_TITTLE_ADMIN_PASS_BUS_ORDER = "订单审核通过";				// 极光推送标题
	public static final String PUSH_TITTLE_ADMIN_CANCEL_BUS_ORDER = "后台管理员取消订单";		// 极光推送标题
	public static final String PUSH_TITTLE_ADMIN_REFUSE_BUS_ORDER = "订单审核被驳回";			// 极光推送标题
	
	/**
	 * 7.默认初始值
	 */
	public static final String INIT_ORDER_CODE_KEY = "123456";								// 初始化订单的编号随机码
	public static final String DEFAULT_PASSWORD = "111111";									// 运维人员、司机初始密码
	
	/**
	 * 8.推送主题
	 */
	public static final String TOMS_NOTICE_RETURN = "TOMS_NOTICE_RETURN";					// 极光推送的主题(举例):xxxxx
	
	/**
	 * 8.唯一码
	 */
	public static final String CUSTOMER_CODE = "Cust_";								// 乘客
	
	/**
	 * 10.其他
	 */
	public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";						// 日期匹配格式:日期格式
	public static final String DATE_PATTERN_MONTH = "yyyy-MM";						// 日期匹配格式:月份格式
	public static final String DATE_PATTERN_YEAR = "yyyy";							// 日期匹配格式:年份格式
	
	public static final String DATE_RANGE_TYPE_ONE_DAY = "oneDay";					// 日期范围类型:一天
	public static final String DATE_RANGE_TYPE_ONE_WEEK = "oneWeek";				// 日期范围类型:一周
	public static final String DATE_RANGE_TYPE_ONE_MONTH = "oneMonth";				// 日期范围类型:一月
	public static final String DATE_RANGE_TYPE_ONE_YEAR = "oneYear";				// 日期范围类型:一年
	public static final String DATE_RANGE_TYPE_SE_DAY = "seDay";					// 日期范围类型:起始日期
	public static final String DATE_RANGE_TYPE_SE_MONTH = "seMonth";				// 日期范围类型:起始月份
	public static final String DATE_RANGE_TYPE_SE_YEAR = "seYear";					// 日期范围类型:起始年份
	public static final String SCORE_NUMBER = "5";									// 评分最大分值
	
	/**
	 * 
	 * 11. redis 锁参数
	 */
	public static final String LOCK_KEY = "lockkey";								// 锁的名称
	public static final int LOCK_EXPIRE = 1;										// 锁定时长（秒），建议10秒内
	public static final int LOCK_NUM = 2;		   				 					// 取锁重试试数，建议不大于3 
	public static final int LOCK_INTERVAL = 1000;									// 重试时长
	public static final boolean LOCK_FORCELOCK = false;								// 强制取锁，不建议
	
	public static Date getDateAdd(Date date, int expire, int idate) {  
		Calendar calendar = Calendar.getInstance();  
		if (null != date) {// 默认当前时间  
			calendar.setTime(date);  
		}  
		calendar.add(idate, expire);  
		return calendar.getTime();  
	}
	
	public static void main(String[] args) {
	Date date=	getDateAdd(null,1,Calendar.SECOND);
	SimpleDateFormat	 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 System.out.println("未来时间="+sdf.format(date));
		 System.out.println("当前时间="+sdf.format(new Date()));
		 System.out.println("未来时间="+date.getTime());
		 System.out.println("当前时间毫秒="+new Date().getTime());
		 System.out.println("相差时间毫秒="+(date.getTime()-new Date().getTime()));
		 
		System.out.println(TimeUnit.SECONDS);
	}
}
