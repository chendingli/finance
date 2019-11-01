package com.chinasoft.util.common;

import java.math.BigDecimal;

public class DoubleUtil {

	// 保留3位小数
	public static final int THREE = 3;

	// 保留4位小数
	public static final int FOUR = 4;

	public static BigDecimal divide(Double number, Double divisor) {
		return new BigDecimal(number).divide(new BigDecimal(divisor), THREE, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal divide(BigDecimal number, BigDecimal divisor) {
		return number.divide(divisor, THREE, BigDecimal.ROUND_HALF_UP);
	}

	public static boolean existRate(double rate) {
		return new Double(rate).equals(new Double(0)) ? false : true;
	}

	public static BigDecimal computeRate(double rate, double subTotal, BigDecimal total) {
		return new BigDecimal(rate).multiply(new BigDecimal(subTotal).divide(total, 3, BigDecimal.ROUND_HALF_UP));
	}

	public static BigDecimal getBigDecimal(Double number) {
		BigDecimal value = new BigDecimal(number);
		value.setScale(THREE, BigDecimal.ROUND_HALF_UP);
		return value;
	}
}
