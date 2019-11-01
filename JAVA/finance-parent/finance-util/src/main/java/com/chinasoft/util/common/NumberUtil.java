package com.chinasoft.util.common;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class NumberUtil {

	public static int parseInt(String sInt, int iDef) {
		if (StringUtils.isEmpty(sInt)) {
			return iDef;
		}
		
		try {
			return Integer.parseInt(sInt);
		} catch (Exception ex) {
			return iDef;
		}
	}

	public static double parseDouble(String sDouble, double iDef) {
		
		if (StringUtils.isEmpty(sDouble)) {
			return iDef;
		}
		try {
			return Integer.parseInt(sDouble);
		} catch (Exception ex) {
			return iDef;
		}
	}

	public static double divide(Double number, Double divisor) {
		return new BigDecimal(number).divide(new BigDecimal(divisor), 3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
