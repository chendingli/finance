package com.chinasoft.util.code;

import org.apache.log4j.Logger;

import java.util.Random;

public class CodeUtil {
	protected static Logger logger = Logger.getLogger(CodeUtil.class);
	
	/**
	 * 生成唯一码
	 * @param codeType : 唯一码类型
	 * @return
	 */
	public static String getCode(String codeType){
		String orderNumber = "";
		Random random = new Random();
		String temp = String.valueOf(random.nextInt(1000));
		String extraZero = "";
		// 获取3位长度随机数
		for (int i = 0; i < 3 - temp.length(); i++) {
			extraZero += "0";
		}
		temp = extraZero + temp;
		
		// 获取13位时间戳
		String timestamp = String.valueOf(System.currentTimeMillis());
		// 16位订单号
		orderNumber = timestamp + temp;
		logger.debug(orderNumber);
		return codeType + orderNumber;
	}
}
