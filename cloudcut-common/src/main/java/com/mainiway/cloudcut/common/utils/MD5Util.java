package com.mainiway.cloudcut.common.utils;

import java.security.MessageDigest;

/**
 * ***************************************************************************
 *模块名 : MD5Util
 *文件名 : MD5Util.java
 *创建时间 : 2016年7月14日
 *实现功能 : MD5加密
 *作者 : tang
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月14日 v0.0.1 tang 创建
 ****************************************************************************
 */
public class MD5Util {
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public final static String MD5(String s) {
		try {
			byte[] inputBytes = s.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(inputBytes);
			byte[] md = md5.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

}
