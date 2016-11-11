package com.mainiway.cloudcut.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;



/**
 * ***************************************************************************
 *模块名 : TimeUtil
 *文件名 : TimeUtil.java
 *创建时间 : 2016年6月6日
 *实现功能 : 资讯管理 计算几分钟前  几小时前 几天前 几年前
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年6月6日 v0.0.1 zhangning 创建
 ****************************************************************************
 */
public class TimeUtil {
	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_WEEK = 604800000L;

	private static final String ONE_SECOND_AGO = "秒前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	public static final String FORMATE_24 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATE_DATE = "yyyy-MM-dd";

	/**
	 * ====================================================================
	 *函 数 名： @param off  如果为-1 是昨天，-2 是前天 以此类推
	 *函 数 名： @return
	 *功 能：
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年6月12日 v0.0.1 dell 创建
	====================================================================
	 */
	public static String getSpecifyDate(int off) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, off);
		return TimeUtil.date2String(cal.getTime(), FORMATE_DATE);
	}

	/**
	 * ====================================================================
	 *函 数 名： @param date
	 *函 数 名： @return yy-MM-dd HH:mm:ss
	 *功 能：
	----------------------------------------------------------------------
	 *修改记录 ：日期转String
	 *日 期  版本 修改人 修改内容
	 *2016年6月12日 v0.0.1 dell 创建
	====================================================================
	 */
	public static String date2String(Date date, String formate) {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.format(date);
	}

	public static Date String2Date(String date, String formate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		return sdf.parse(date);
	}

	/**
	 * ====================================================================
	 *函 数 名： @param date
	 *函 数 名： @return
	 *功 能：
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年6月8日 v0.0.1 zhouziqin 创建
	====================================================================
	 */
	public static String format(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_24);
		try {
			Date formateDate = sdf.parse(date);
			return format(formateDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 获取时间格式
	public static String formatToDate(String date, String formate) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formate);
		try {
			Date formateDate = sdf.parse(date);
			return format(formateDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String format(Date date) {
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}else if (delta < 60L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}else if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}else if (delta < 48L * ONE_HOUR) {
			return "昨天:" + date2String(date, "HH:mm:ss");
		}
		else if (delta < 12L * 4L * ONE_WEEK) {
			return date2String(date, "MM-dd HH:mm:ss");
		} else {
			return date2String(date, "yyyy-MM-dd HH:mm:ss");
		}
	}

	private static long toSeconds(long date) {
		return date / 1000L;
	}

	private static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	private static long toHours(long date) {
		return toMinutes(date) / 60L;
	}


	/**
	 * ====================================================================
	 *函 数 名： @param date
	 *函 数 名： @param overTime
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能： 计算剩余的超时时间，返回日期格式
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年8月9日 v0.0.1 liliangjun 创建
	====================================================================
	 */
	public static String formatOverran(String date,String overTime) throws Exception {
		if(StringUtils.isEmpty(overTime)||StringUtils.isEmpty(date)){
			return "";
		}else{
			if(!"0".equals(overTime)){
				Date time = String2Date(date,"yyyy-MM-dd HH:mm:ss");
				long delta = (Long.valueOf(overTime)*3600000) - (new Date().getTime() - time.getTime());

				int ss = 1000;
		        int mi = ss * 60;
		        int hh = mi * 60;
		        int dd = hh * 24;
		        long mm = dd * 30L;
		        long month = delta / mm;
		        long day = (delta - month * mm) / dd;
		        long hour = (delta - month * mm - day * dd ) / hh;
		        long minute = (delta - month * mm - day * dd - hour * hh) / mi;
		        String strmonth = month < 10 ? "0" + month : "" + month; //月
		        String strDay = day < 10 ? "0" + day : "" + day; //天
		        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
		        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟

				if(delta<0){
					return "-1";
				}else if (delta < 60L * ONE_MINUTE) {
					long minutes = toMinutes(delta);
					return (minutes <= 0 ? 1 : minutes) + "分钟";
				}else if (delta < 24L * ONE_HOUR) {
					return strHour+"小时"+strMinute+"分钟";
				}else if(delta < 720L * ONE_HOUR){
					//天
			    	return strDay+"天"+strHour+"小时"+strMinute+"分钟";
				}else if(delta < 8640L * ONE_HOUR){
					//月
			    	return strmonth+"月"+strDay+"天"+strHour+"小时";
				}else{
					//年
					double onehour=delta/3600000;//小时
			    	int oneday= (int)onehour/24;//天
			    	int mothes = oneday/30;//月
			    	int year = mothes/12;//年
			    	double newmothes = year%12;//
			    	double newday = newmothes%30;
			    	double newhour=newday%24;//小时
			    	return year+"年"+newmothes+"月"+newday+"天"+newhour+"小时";
				}
			}else{
				return "";
			}
		}
	}


	/**
	 * ====================================================================
	 *函 数 名： @param date
	 *函 数 名： @param overTime
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能： 计算超时时间的差值，即还有多长时间超时，返回long类型
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月18日 v0.0.1 liliangjun 创建
	====================================================================
	 */
	public static Long calculatedTimeout(String date,String timeout) throws Exception {
		if(StringUtils.isEmpty(timeout)||StringUtils.isEmpty(date)){
			return 0l;
		}else{
			Date time = String2Date(date,"yyyy-MM-dd HH:mm:ss");
			return  (Long.valueOf(timeout)*3600000) - (new Date().getTime() - time.getTime());
		}

	}


    public static void main(String[] args) throws Exception {
    	System.out.println(calculatedTimeout("2016-08-20 14:32:38","24"));
	}



}
