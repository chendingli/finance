package com.chinasoft.util.code;

// 针对运维端页面的错误码
public class StatusCodeForToms {
	
	// 页面返回状态提示：0~50
	public static final String STATE_SUCCESS = "0";							// 处理正常结束
	public static final String ERROR_ERROR_ADD = "1";						// 新增操作执行异常
	public static final String ERROR_ERROR_UPDATE = "2";					// 修改操作执行异常
	public static final String ERROR_ERROR_DELETE = "3";					// 删除操作执行异常
	
	// 对JSP页面错误提示：51~100
	public static final String STATE_STATISTICS_RESULT_TOO_LONG = "51";		// 统计页面，查询结果过大，需要缩小查询范围
	
	// 文件上传：100~150
	public static final String ERROR_STATE_SHOW_IN_VIEW_4 = "101";			// 上传文件内容为空
	public static final String ERROR_STATE_SHOW_IN_VIEW_5 = "102";			// 上传文件过大
	public static final String ERROR_STATE_SHOW_IN_VIEW_6 = "103";			// 上传读取失败
	public static final String ERROR_STATE_SHOW_IN_VIEW_7 = "104";			// 上传存储失败
	
	public static final String ERROR_STATE_SHOW_IN_VIEW_8 = "105";			// 下载失败
}
