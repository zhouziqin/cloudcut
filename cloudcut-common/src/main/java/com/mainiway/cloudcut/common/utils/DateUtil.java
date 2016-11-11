package com.mainiway.cloudcut.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat secFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 字符串日期格式化(传入日期字符串返回格式化后的日期字符串)
	 *
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @author laizw
	 */
	public static String dateStrFormat(String date) throws ParseException {
		Date tempDate=null;
		if(Tools.isEmpty(date))
			tempDate=new Date();
		else
		{
			tempDate=dayFormat.parse(date);
		    date=dayFormat.format(tempDate);
		}
		return date;
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


	public List<String> getDayListFromDayArea_1(String StartDate, String EndDate) {
		List<String> strDateList = new ArrayList<String>();
		// 首先判断传入参数是否错误

		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarStart.set(Integer.parseInt((StartDate.substring(0, 4))),
				Integer.parseInt((StartDate.substring(5, 7))) - 1,
				Integer.parseInt((StartDate.substring(8, 10))));
		calendarEnd.set(Integer.parseInt((EndDate.substring(0, 4))),
				Integer.parseInt((EndDate.substring(5, 7))) - 1,
				Integer.parseInt((EndDate.substring(8, 10))));
		if (calendarStart.after(calendarEnd)) {
			return strDateList;
		}
		if (StartDate.equals(EndDate)) {
			strDateList.add(EndDate);
			return strDateList;
		}
		strDateList.add(EndDate);
		for (int i = 0;; i++) {
			String strTmp = getPreDay(EndDate, 1);
			strDateList.add(strTmp);
			EndDate = strTmp;
			if (strTmp.equals(StartDate)) {
				break;
			}
		}

		return strDateList;
	}

	public List<String> getDayListFromDayArea_2(String StartDate, String EndDate) {
		List<String> strDateRtnList = new ArrayList<String>();
		List<String> strDateList = getDayListFromDayArea_1(StartDate, EndDate);
		for (; strDateList.size() > 0;) {
			strDateRtnList.add(strDateList.get(strDateList.size() - 1));
			strDateList.remove(strDateList.size() - 1);
		}
		return strDateRtnList;
	}

	/**
	 * 获得两个日期相差多少天
	 * @param smdate 开始日期,
	 * @param bdate	结束日期.
	 * @return int 天数
	 * @throws ParseException
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		Date ssmdate = dayFormat.parse(smdate);
		Date bbdate = dayFormat.parse(bdate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(ssmdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bbdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获得与传入日期相差天数的日期.(精确到天)
	 * @param strDate 传入日期
	 * @param xday 相差天数
	 * @return
	 */
	public static String getPreDay(String strDate, int xday) {
		int intyear;
		int intmonth;
		int intday;

		intyear = Integer.parseInt((strDate.substring(0, 4)));
		intmonth = Integer.parseInt((strDate.substring(5, 7))) - 1;
		intday = Integer.parseInt((strDate.substring(8, 10)));
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时?
		calendar.set(intyear, intmonth, intday);
		calendar.add(Calendar.DATE, -xday);
		String yestedayDate = dayFormat.format(calendar.getTime());
		return yestedayDate;
	}

	/**
	 * 
	 * @param strTimeParam1
	 * @param strTimeParam2
	 * @return
	 */
	public static String CheckDayMax(String strTimeParam1, String strTimeParam2) {

		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarStart.set(Integer.parseInt((strTimeParam1.substring(0, 4))),
				Integer.parseInt((strTimeParam1.substring(5, 7))) - 1,
				Integer.parseInt((strTimeParam1.substring(8, 10))));
		calendarEnd.set(Integer.parseInt((strTimeParam2.substring(0, 4))),
				Integer.parseInt((strTimeParam2.substring(5, 7))) - 1,
				Integer.parseInt((strTimeParam2.substring(8, 10))));
		if (calendarStart.after(calendarEnd)) {// END大
			return dayFormat.format(calendarStart.getTime());
		} else {// 相等或者start大
			return dayFormat.format(calendarEnd.getTime());
		}

	}

	public static String CheckDayMin(String strTimeParam1, String strTimeParam2) {
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();
		calendarStart.set(Integer.parseInt((strTimeParam1.substring(0, 4))),
				Integer.parseInt((strTimeParam1.substring(5, 7))) - 1,
				Integer.parseInt((strTimeParam1.substring(8, 10))));
		calendarEnd.set(Integer.parseInt((strTimeParam2.substring(0, 4))),
				Integer.parseInt((strTimeParam2.substring(5, 7))) - 1,
				Integer.parseInt((strTimeParam2.substring(8, 10))));
		if (calendarStart.after(calendarEnd)) {// END小
			return dayFormat.format(calendarEnd.getTime());
		} else {// 相等或者start大
			return dayFormat.format(calendarStart.getTime());
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static String strDay2Week(String date) {
		String strRtn = "99";
		String[] strWeekArr = new String[] { "一", "二", "三", "四", "五", "六", "七" };
		if (Tools.notEmpty(date)) {
			date = date.replace("Z", "");
			date = date.replace("T", " ");
			
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.set(Integer.parseInt((date.substring(0, 4))),
						Integer.parseInt((date.substring(5, 7))) - 1,
						Integer.parseInt((date.substring(8, 10))));
				long lweek = calendar.getTimeInMillis() / (24 * 3600 * 1000) % 7;
				int iweek = Integer.parseInt(lweek + "");
				iweek = iweek + 4;
				if (iweek > 7) {
					iweek = iweek - 7;
				}

				return strWeekArr[iweek - 1];
			} catch (Exception e) {
				e.printStackTrace();
			}
			strRtn = "99";
		} else {
			return strRtn;
		}
		return strRtn;
	}

	public static List<String> dateFtoS(String dateFirst, String dateSecond) {
		List<String> list = new ArrayList<String>();
		try {
			Date dateOne = dayFormat.parse(dateFirst);
			Date dateTwo = dayFormat.parse(dateSecond);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateOne);
			while (calendar.getTime().before(dateTwo)) {
				list.add(dayFormat.format(calendar.getTime()));
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 传入一个日期计算当前日期是星期几
	 *
	 * @param DateTime需要计算的日期字符串
	 * @return返回星期几
	 */
	public static String getDateToWeek(Date DateTime) {
		try {
			String dateStr = dayFormat.format(DateTime);
			if (dateStr == null || dateStr == "")
				return "";
			String[] tempDate = dateStr.split("-");
			int year = Integer.parseInt(tempDate[0]);
			int month = Integer.parseInt(tempDate[1]);
			int day = Integer.parseInt(tempDate[2]);
			Calendar calendar = Calendar.getInstance();// 获得一个日历
			calendar.set(year, month - 1, day);// 设置当前时间,月份是从0月开始计算
			int number = calendar.get(Calendar.DAY_OF_WEEK) - 1;// 星期表示1-7，是从星期日开始，
			String[] str = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			return str[number];
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 根据当前时间获取Dateval前或DateVal后的日期值
	 *
	 * @param StartDate开始计算的时间
	 * @param DateVal需要往前或往后计算的天数
	 * @return
	 */
	public static String getNextDateCalculation(String StartDate, int DateVal) {
		try {
			Date date = new Date();// 取时间
			if (StartDate != null && StartDate != "") {
				date = dayFormat.parse(StartDate);
			}
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE, DateVal);// 把日期往后增加一天或往前减少一天(整数往后推,负数往前移动)
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			String dateString = dayFormat.format(date);
			return dateString;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 *
	 * 方法描述：取得当前日期的上月或下月日期 ,amount=-1为上月日期，amount=1为下月日期；
	 * @param s_DateStr
	 * @param s_FormatStr
	 * @param amount  =-1为上月日期，amount=1为下月日期；
	 * @return
	 * @throws Exception
	 */
	public static String getFrontBackStrDate(String strDate, String format,int amount) throws Exception {
		if (null == strDate) {
			return null;
		}
		try {
			DateFormat fmt = new SimpleDateFormat(format);
			Calendar c = Calendar.getInstance();
			c.setTime(fmt.parse(strDate));
			c.add(Calendar.MONTH, amount);
			return fmt.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 计算2个日期相差的天数
	 *
	 * @param StartDate开始日期
	 * @param EndDate结束日期
	 * @return
	 */
	public static int getDaysBetween(String StartDate, String EndDate) {
		try {
			java.util.Calendar calst = java.util.Calendar.getInstance();
			java.util.Calendar caled = java.util.Calendar.getInstance();
			calst.setTime(dayFormat.parse(StartDate));
			caled.setTime(dayFormat.parse(EndDate));
			// 设置时间为0时
			calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calst.set(java.util.Calendar.MINUTE, 0);
			calst.set(java.util.Calendar.SECOND, 0);
			caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
			caled.set(java.util.Calendar.MINUTE, 0);
			caled.set(java.util.Calendar.SECOND, 0);
			// 得到两个日期相差的天数
			int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

			return days;
		}

		catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 比较2个日期大小
	 * @param startDate  开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static int compare_date(String startDate, String endDate) {

		try {
			Date dt1 = dayFormat.parse(startDate);
			Date dt2 = dayFormat.parse(endDate);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return -2;
	}

	/**
	 * 功能概述：计算指定年月的天数和周数<br>
	 * 创建时间：2010-5-17 下午05:25:58<br>
	 * month从0开始
	 *
	 * @author
	 */
	public static int calculateMonthDay(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取制定时间周一的起始时间
	 * @param date
	 * @return
	 */
	public static Date beginDateOneWeek(Date date) {
        if(date == null) return null;

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }
	
	/**
	 * 获取制定时间周日的结束时间
	 * @param date
	 * @return
	 */
	public static Date endDateOneWeek(Date date) {
        if(date == null) return null;

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(beginDateOneWeek(date));
        calendar.add(Calendar.DATE, 7);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }
	
	/**
	 * 获取定制时间的开始时间.
	 * @param date
	 * @return
	 */
	public static Date beginDateOneDay(Date date) {
		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取定制时间的结束时间.
	 * @param date
	 * @return
	 */
	public static Date endDateOneDay(Date date) {
		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 获得指定日期的所属月份的第一天时间.
	 * @param date
	 * @return
	 */
	public static Date beginDateOneMonth(Date date) {
		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
	
	/**
	 * 获得指定日期的所属月份的最后时间.
	 * @param date
	 * @return
	 */
	public static Date endDateOneMonth(Date date) {
		if (date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return calendar.getTime();
	}
	
	/**
	 * 上月开始时间(1号)
	 * @return
	 */
	public static Date beginDayLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		calendar.setTime(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}
	
	/**
	 * 上月结束时间
	 * @return
	 */
	public static Date endDateLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		calendar.setTime(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		//calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
	
	private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }
	
	/**
	 * 上周开始时间(周一)
	 * @return
	 */
	public static Date beginDateLastWeek() {
	    int mondayPlus = getMondayPlus();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mondayPlus + 7 * -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
	/**
	 * 上周结束时间(周日)
	 * @return
	 */
    public static Date endDateLastWeek() {
        int mondayPlus = getMondayPlus();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, mondayPlus + 7 * -1 +6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
	
    /**
     * 当前季度开始时间
     * @return
     */
    public static Date beginCurrentQuarterTime() { 
    	Calendar c = Calendar.getInstance(); 
    	int currentMonth = c.get(Calendar.MONTH) + 1; 
    	Date now = null; 
    	try { 
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 0);
			}else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 3); 
			}else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 6); 
			}else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 9); 
			}
			c.set(Calendar.DATE, 1); 
			
			now = beginDateOneDay(c.getTime());
		} catch (Exception e) { 
			e.printStackTrace(); 
    	} 
    	return now; 
    }

    	/** 
    	* 当前季度的结束时间
    	* @return date
    	*/ 
	public static Date endCurrentQuarterTime() { 
		Calendar c = Calendar.getInstance(); 
		int currentMonth = c.get(Calendar.MONTH)+1; 
		System.out.println(currentMonth);
		Date now = null; 
		try { 
			if (currentMonth >= 1 && currentMonth <= 3) { 
				c.set(Calendar.MONTH, 2); 
				c.set(Calendar.DATE, 31); 
			} else if (currentMonth >= 4 && currentMonth <= 6) { 
				c.set(Calendar.MONTH, 5); 
				c.set(Calendar.DATE, 30); 
			} else if (currentMonth >= 7 && currentMonth <= 9) { 
				c.set(Calendar.MONTH, 8); 
				c.set(Calendar.DATE, 30); 
			} else if (currentMonth >= 10 && currentMonth <= 12) { 
				c.set(Calendar.MONTH, 11); 
				c.set(Calendar.DATE, 31); 
			} 
			now = endDateOneDay(c.getTime());
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
    	return now; 
    }
	
	/**
	 * 获得当年的第一天日期
	 * @return
	 */
	public static Date beginDateYear() {
         Calendar calendar = Calendar.getInstance();
        
         calendar.setTime(calendar.getTime());
         calendar.set(Calendar.DAY_OF_YEAR, 1);
         calendar.set(Calendar.HOUR_OF_DAY, 0);
		 calendar.set(Calendar.MINUTE, 0);
		 calendar.set(Calendar.SECOND, 0);
		 calendar.set(Calendar.MILLISECOND, 0);
         
		 return calendar.getTime();
     }
    
    /**
     * 获得当年的最后一套日期
     * @return
     */
    public static Date endDateYear() {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(calendar.getTime());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
