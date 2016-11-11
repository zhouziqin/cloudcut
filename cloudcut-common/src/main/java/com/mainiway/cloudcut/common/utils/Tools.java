package com.mainiway.cloudcut.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 *
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 *
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 *
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return new String[0];
		}
		return str.split(splitRegex, -1);
	}


	/**
	 * 字符串转换为字符串List
	 *
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static List<String> str2StrList(String str, String splitRegex) {
		if (isEmpty(str)) {
			return new ArrayList<String>();
		}
		return Arrays.asList(str.split(splitRegex, -1));
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str 字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}


	/**
	 * 判断字符串是否为空，为空返回"" 不为空返回正常
	 *
	 * @return
	 * @author changyong 2015年9月10日 下午2:24:25
	 */
	public static String checkStr(String str) {
		return isEmpty(str) ? "-1" : str;
	}

	/**
	 * 判断字符串是否为空，为空返回指定的newStr 不为空返回正常
	 *
	 * @return
	 * @author changyong 2015年9月10日 下午2:24:25
	 */
	public static String checkStr(String str, String newStr) {
		return isEmpty(str) ? newStr : str;
	}
	
	

	
}
