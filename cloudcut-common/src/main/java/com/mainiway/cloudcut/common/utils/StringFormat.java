package com.mainiway.cloudcut.common.utils;
 
/**
 * ***************************************************************************
 *模块名 : StringFormat
 *文件名 : StringFormat.java
 *创建时间 : 2016年8月12日
 *实现功能 : 
 *作者 : zhouziqin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年8月12日 v0.0.1 zhouziqin 创建
 ****************************************************************************
 */
public class StringFormat {
	public static String camelCase(String str)  
	{  
  		String [] strs = str.toLowerCase().split("_");
 		StringBuffer sb = new StringBuffer();
 		sb.append(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			 sb.append((char)(strs[i].charAt(0)-32)).append(strs[i].substring(1));
		}
		return sb.toString();		
	}   
 
}
