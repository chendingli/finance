package com.chinasoft.util.encrytion.sha;

import cn.hutool.crypto.SecureUtil;
import org.springframework.util.StringUtils;

// Sha不可逆加密，用于用户密码加密
public class ShaEncryptionUtil {

	public static String encryptionKey = "root";
	
	public static String encrypt(String source) {
		if(StringUtils.isEmpty(source) || StringUtils.isEmpty(encryptionKey)) {
			return null;
		}

		return new String(SecureUtil.hmacSha1(encryptionKey).digest(source));
	}

}
