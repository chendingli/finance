package com.chinasoft.util.encrytion.des;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
DES加密介绍
DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。
。
注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
	protected static Logger logger = Logger.getLogger(DES.class);
	
	public DES() {
	}
	
	// 密码，长度要是8的倍数
//	public static String key = "9588028820109132";
	
	/**
	 * 加密
	 * @param datasource byte[]
	 * @param password String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String key) { 
		try{
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		}catch(Throwable e){
			logger.error("加密异常", e);
		}
		return null;
	}
	/**
	 * 解密
	 * @param src byte[]
	 * @param password String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(key.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
	
	// 16进制到bytes[]
	public static byte[] hexStringToBytes(String hexString) {  
		if (hexString == null || hexString.equals("")) {  
			return null;  
		}  
		hexString = hexString.toUpperCase();  
		int length = hexString.length() / 2;  
		char[] hexChars = hexString.toCharArray();  
		byte[] d = new byte[length];  
		for (int i = 0; i < length; i++) {  
			int pos = i * 2;  
			d[i] = (byte) (DES.charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
		}  
		return d;  
	} 
	private static byte charToByte(char c) {  
		return (byte) "0123456789ABCDEF".indexOf(c);  
	} 
	// base64字符串转byte[]
	public static byte[] base64String2ByteFun(String base64Str){
		return Base64.decodeBase64(base64Str);
	}
	
	// byte[]转base64
	public static String byte2Base64StringFun(byte[] b){
		return Base64.encodeBase64String(b);
	}
}