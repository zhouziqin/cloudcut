package com.mainiway.cloudcut.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ***************************************************************************
 *模块名 : LocalUtil
 *文件名 : LocalUtil.java
 *创建时间 : 2016年5月10日
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月10日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
public class LocalUtil {

	private static final String GBK="GBK";

	private static final String ISO="ISO-8859-1";

	private static final String UTF8="UTF-8";

	public static String ISO2GBK(String str){

		String result="";

		try {

			result=new String(str.getBytes(ISO),GBK);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static String UTF82GBK(String str){

		String result="";

		try {

			result=new String(str.getBytes(UTF8),GBK);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return result;
	}


	public static String UTF82ISO(String str){

//		try {
//
//			str = new String(str.getBytes(UTF8),ISO);
//
//		} catch (UnsupportedEncodingException e) {
//
//			e.printStackTrace();
//		}

		return str;
	}

	public static String ISO2UTF8(String str){


//		try {
//
//			str = new String(str.getBytes(ISO),UTF8);
//
//		} catch (UnsupportedEncodingException e) {
//
//			e.printStackTrace();
//		}

		return str;
	}

	public static String GBK2UTF8(String str){

		String result="";

		try {

			result=new String(str.getBytes(GBK),UTF8);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static String GBK2ISO(String str){

		String result="";

		try {

			result=new String(str.getBytes(GBK),ISO);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static String transRelation(String rel){

		 switch (rel) {

			case "include":

				return "|like";

			case "more":

				return "|>";

			case "moreEqual":

				return "|>=";

			case "equal":

				return "|=";

			case "less":

				return "|<";

			case "lessEqual":

				return "|<=";

			case ">":

				return "|>";

			case ">=":

				return "|>=";

			case "=":

				return "|=";

			case "<":

				return "|<";

			case "<=":

				return "|<=";

			default:

				return "";


		}

	}

	private static final Object lockObj = new Object();

	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	private static SimpleDateFormat getSdfInstance(final String pattern) {

		ThreadLocal<SimpleDateFormat> tlocal = sdfMap.get(pattern);

		if ( null == tlocal ) {

			synchronized (lockObj) {

				tlocal = sdfMap.get(pattern);

				if (null == tlocal) {

					tlocal = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {

							return new SimpleDateFormat(pattern);
						}
					};

					sdfMap.put(pattern, tlocal);
				}
			}
		}

		return tlocal.get();
	}

	public static String getSDFStr(Date date, String pattern) {
		return getSdfInstance(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdfInstance(pattern).parse(dateStr);
	}

	/**
	 * ====================================================================
	 *函 数 名： @return
	 *功 能： 生成验证码
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年5月17日 v0.0.1 liliangjun 创建
	====================================================================
	 */
	public static String generateVerCode(int length){
		String val = "";
		  Random random = new Random();
		  for (int i = 0; i < length; i++) {
		   // 输出字母还是数字
		   String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
		   // 字符串
		   if ("char".equalsIgnoreCase(charOrNum)) {
		    // 取得大写字母还是小写字母
			//int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
		    int choice = 97;//默认小写
		    val += (char) (choice + random.nextInt(26));
		   } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
		    val += String.valueOf(random.nextInt(10));
		   }
		  }
		  return val;
	}

	/**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getDiffrentFromLists(List<String> list1, List<String> list2) {
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> diff = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        return diff;
    }
    /**
	 * 按照参数format的格式，日期转字符串
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}
}
