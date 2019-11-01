package com.chinasoft.util.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

// 稍后放于taxi-util工程中
public class Encodes {
	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String part) {
		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}
}
