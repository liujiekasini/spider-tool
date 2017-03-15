package com.dinfo.plugtool.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;


/**
 * @ClassName: DateUtil
 * @Description: 时间操作工具类
 * @author xulonglong
 * @date 2016-7-4 上午11:21:29
 */
public class DateUtil {
	
	/**
	 * @Description: Date时间格式转换成时间字符串
	 * @param @param date
	 * @param @param pattern
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:24:59
	 */
	public static String dateToString(Date date, String pattern) {
		if(date==null){
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * @Description: 字符串转换成时间格式
	 * @param @param str
	 * @param @param pattern
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:24:41
	 */
	public static Date stringToDate(String str, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * @Description: 时间格式转化成long格式
	 * @param @param str
	 * @param @param pattern
	 * @param @return   
	 * @return long  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:26:03
	 */
	public static long dateToLong(long str,String pattern) {
		Date date = new Date();
		date.setTime(str);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return Long.parseLong((sdf.format(date)));
	}
	/**
	 * @Description: 获得某一个月的最后一天
	 * @param @param year
	 * @param @param month
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:26:33
	 */
	public static Date getLastDate(int year, int month){
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR,year) ;
		cal.set(Calendar.MONTH, month); 
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.add(Calendar.DAY_OF_MONTH, -1);  
		return cal.getTime();
	}
	/**
	 * @Description: 获得某一个月的第一天
	 * @param @param year
	 * @param @param month
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:26:49
	 */
	public static Date getFirstDate(int year, int month){
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR,year) ;
		cal.set(Calendar.MONTH, month); 
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.add(Calendar.DAY_OF_MONTH, -1);    
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		return cal.getTime();
	}
	/**
	 * @Description: 获取一个月的第一天
	 * @param @param date
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:27:21
	 */
	public static Date getFirstDate(Date date){
		return getFirstDate(Integer.parseInt(dateToString(date,"yyyy")),Integer.parseInt(dateToString(date,"MM")));
	}
	/**
	 * @Description: 获取一个月的最后一天
	 * @param @param date
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:27:21
	 */
	public static Date getLastDate(Date date){
		return getLastDate(Integer.parseInt(dateToString(date,"yyyy")),Integer.parseInt(dateToString(date,"MM")));
	}
	/**
	 * @Description: 获取当前的日期是一周的第几天
	 * @param @param date
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:28:06
	 */
	public static int getDayOfWeek(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return  c.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * @Description: 得到时间的星期
	 * @param @param date
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:29:22
	 */
	public static String dateToStringWeek(Date date){
		String strDay = "";
		switch (getDayOfWeek(date)) {
		case 1:
			   strDay="日";
			break;
		case 2:
			   strDay="一";
			break;
		case 3:
			   strDay="二";
			break;
		case 4:
			   strDay="三";
			break;
		case 5:
			   strDay="四";
			break;
		case 6:
			   strDay="五";
			break;
		case 7:
			   strDay="六";
			break;
		default:
			break;
		}
		return "星期"+strDay;
	}
	/**
	 * @Description: 时间格式日期增加函数
	 * @param @param date
	 * @param @param n
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:29:49
	 */
	public static Date addDate(Date date, int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}
	/**
	 * @Description: 字符串格式日期增加函数
	 * @param @param dateStr
	 * @param @param n
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:30:20
	 */
	public static String addDateStr(String dateStr, int n){
		Date date=stringToDate(dateStr,"yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		dateStr=dateToString(c.getTime(), "yyyy-MM-dd");
		return dateStr;
	}
	/**
	 * @Description: 日期格式小时增加函数
	 * @param @param date
	 * @param @param n
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:30:53
	 */
	public static Date addHour(Date date, int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, n);
		return c.getTime();
	}
	/**
	 * @Description: 字符串格式分钟增加函数
	 * @param @param dateStr
	 * @param @param n
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:31:08
	 */
	public static String addMinuteStr(String dateStr, int n){
		Date date=stringToDate(dateStr,"yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, n);
		dateStr=dateToString(c.getTime(), "yyyy-MM-dd HH:mm");
		return dateStr;
	}
	/**
	 * @Description: 获取一个月有多少天
	 * @param @param year
	 * @param @param month
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:31:24
	 */
	public static int getMonthDays(int year,int month){
		Calendar   cal   =   Calendar.getInstance();   
		   cal.setTime(DateUtil.getFirstDate(year, month));
		  return cal.getActualMaximum(Calendar.DATE);
	}
	/**
	 * @Description: 获取一个月有多少天
	 * @param @param date
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:31:47
	 */
	public static int getMonthDays(Date date){
		Calendar   cal   =   Calendar.getInstance();   
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}
	/**
	 * @Description: 两日期比较大小
	 * @param @param date1
	 * @param @param date2
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:32:07
	 */
	public static boolean compareDate(Date date1,Date date2){
		return DateUtil.dateToString(date1, "yyyy-MM-dd").equals(DateUtil.dateToString(date2, "yyyy-MM-dd"));
		
	}
	/**
	 * @Description: 得到某个时间的日期
	 * @param @param date
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:32:44
	 */
	public static int getDayOfMonth(Date date){
		return Integer.parseInt(DateUtil.dateToString(date, "dd"));
		
	}
	/**
	 * @Description: 通过时间获得是哪一年
	 * @param @param date
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:33:11
	 */
	public static int getYear(Date date){
		Calendar   cal   =   Calendar.getInstance();   
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	/**
	 * @Description: 通过时间获得是哪一月
	 * @param @param date
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:33:36
	 */
	public static int getMonth(Date date){
		Calendar   cal   =   Calendar.getInstance();   
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
	}
	/**
	 * @Description: 获取某一天之后或者之前第几天的日期
	 * @param @param date
	 * @param @param n
	 * @param @return   
	 * @return Date  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:34:07
	 */
	public static Date getDate(Date date, int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}
	/**
	 * @Description: 获取Solr日期格式
	 * @param @param date
	 * @param @param type 为1时:时分秒为00:00:00,为2时时分秒为23:59:59,为3取准确值
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 上午11:34:23
	 */
	public static String getSolrDate(Date date,int type){
		StringBuffer strB = new StringBuffer();
		if(type==1){
			strB.append(dateToString(date, "yyyy-MM-dd")+"T"+"00:00:00"+"Z");
		}else if(type==2){
			strB.append(dateToString(date, "yyyy-MM-dd")+"T"+"23:59:59"+"Z");
		}else{
			strB.append(dateToString(date, "yyyy-MM-dd")+"T"+dateToString(date, "HH:mm:ss")+"Z");
		}
		return strB.toString();
		
	}
	/**
	 * @Description: 获取两个日期相差的天数
	 * @param @param time1
	 * @param @param time2
	 * @param @return   
	 * @return long  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:01:41
	 */
	public static long getQuotDays(String time1, String time2){
	    long quot = 0;
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	     Date date1 = ft.parse( time1 );
	     Date date2 = ft.parse( time2 );
	     quot = date2.getTime() - date1.getTime();
	     quot = quot/1000/60/60/24;
	    } catch (ParseException e) {
	     e.printStackTrace();
	    }
	    return quot;
	}
	/**
	 * @Description: 获取两个日期相差的秒数
	 * @param @param time1
	 * @param @param time2
	 * @param @return   
	 * @return long  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:01:55
	 */
	public static long getQuoHours(String time1, String time2){
	    long quot = 0;
	    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	     Date date1 = ft.parse( time1 );
	     Date date2 = ft.parse( time2 );
	     quot = date2.getTime() - date1.getTime();
	     quot = quot/1000;
	    } catch (ParseException e) {
	     e.printStackTrace();
	    }
	    return quot;
	}
	/**
	 * @Description: 获取当前系统时间和日期并格式化输出:
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:02:08
	 */
	public static String getTheCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return format.format(new Date());// new Date()为获取当前系统时间
	}
	/** 
	 * 获取指定时间对应的毫秒数 
	 * @param time "HH:mm:ss" 
	 * @return 
	 */  
	public static long getTimeMillis(String time) {  
	    try {  
	        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");  
	        DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");  
	        Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);  
	        return curDate.getTime();  
	    } catch (ParseException e) {  
	        e.printStackTrace();  
	    }  
	    return 0;  
	}  
	/**
	 * @Description: 计算两个日期相差的天数
	 * @param @param beginDateStr
	 * @param @param endDateStr
	 * @param @return   
	 * @return double  
	 * @throws
	 * @author xulonglong
	 * @date 2016-7-4 下午2:02:26
	 */
	public static double getDaySub(String beginDateStr,String endDateStr){
        double day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
        java.util.Date beginDate;
        java.util.Date endDate;
        try{
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
//            long str2 = beginDate.getTime();
            day=((endDate.getTime()-beginDate.getTime())/(24*60*60*1000))/30;
        } catch (ParseException e){
            e.printStackTrace();
        }  
        return day;
	}
	public static void main(String[] args) throws ParseException {
//		System.out.println(DateUtil.getQuotDays("2014-03-02", DateUtil.dateToString(new Date(), "yyyy-MM-dd")));
		String aString=addDateStr("2008-09-02", 1000);
		System.out.println(aString);
	}
	public static final SimpleDateFormat DF_YMDHMS = new SimpleDateFormat(
			"yyyyMMddHH:mm:ss");

	public static final SimpleDateFormat DF_YMD = new SimpleDateFormat(
			"yyyyMMdd");
	public static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat GMT = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss +0800 yyyy",Locale.ENGLISH);
	public static final SimpleDateFormat SOLR_SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	public static final SimpleDateFormat SDF_CHINA = new SimpleDateFormat("yyyy年MM月dd日");
	public static Date getSystemDateTime() {
		return new Date();
	}
	
	public static String getGMT8Date(Date date) {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 设置时区为GMT
	    return sdf.format(cd.getTime())+" 0800 (中国标准时间)";
	}

	public static String getStryyyyMMdd(Date date){
		return DF_YMD.format(date);
	}


	public static Date getDate(String year, String month, String day,
			String time) {
		try {

			if (month.length() <= 1)
				month = "0" + month;

			if (day.length() <= 1)
				day = "0" + day;

			return DF_YMDHMS.parse(year + month + day + time);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Timestamp getTimestamp(String year, String month, String day,
			String time) {
		try {

			if (StringUtils.isBlank(year) || StringUtils.isBlank(month)
					|| StringUtils.isBlank(day) || StringUtils.isBlank(time))
				return null;

			// Month
			if (month.trim().equalsIgnoreCase("jan")
					|| month.trim().equalsIgnoreCase("january")) {
				month = "1";
			} else if (month.trim().equalsIgnoreCase("feb")
					|| month.trim().equalsIgnoreCase("february")) {
				month = "2";
			} else if (month.trim().equalsIgnoreCase("mar")
					|| month.trim().equalsIgnoreCase("march")) {
				month = "3";
			} else if (month.trim().equalsIgnoreCase("apr")
					|| month.trim().equalsIgnoreCase("april")) {
				month = "4";
			} else if (month.trim().equalsIgnoreCase("may")
					|| month.trim().equalsIgnoreCase("may")) {
				month = "5";
			} else if (month.trim().equalsIgnoreCase("jun")
					|| month.trim().equalsIgnoreCase("june")) {
				month = "6";
			} else if (month.trim().equalsIgnoreCase("jul")
					|| month.trim().equalsIgnoreCase("july")) {
				month = "7";
			} else if (month.trim().equalsIgnoreCase("aug")
					|| month.trim().equalsIgnoreCase("august")) {
				month = "8";
			} else if (month.trim().equalsIgnoreCase("sep")
					|| month.trim().equalsIgnoreCase("september")) {
				month = "9";
			} else if (month.trim().equalsIgnoreCase("oct")
					|| month.trim().equalsIgnoreCase("october")) {
				month = "10";
			} else if (month.trim().equalsIgnoreCase("nov")
					|| month.trim().equalsIgnoreCase("november")) {
				month = "11";
			} else if (month.trim().equalsIgnoreCase("dec")
					|| month.trim().equalsIgnoreCase("december")) {
				month = "12";
			}

			return new Timestamp(getDate(year, month, day, time).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(String year, String month, String day) {
		try {

			if (month.length() <= 1)
				month = "0" + month;

			if (day.length() <= 1)
				day = "0" + day;

			return DF_YMD.parse(year + month + day);
		} catch (ParseException e) {
			return null;
		}
	}

	public static java.sql.Date getSqlDate(String year, String month, String day) {
		try {

			// Month
			if (month.trim().equalsIgnoreCase("jan")
					|| month.trim().equalsIgnoreCase("january")) {
				month = "1";
			} else if (month.trim().equalsIgnoreCase("feb")
					|| month.trim().equalsIgnoreCase("february")) {
				month = "2";
			} else if (month.trim().equalsIgnoreCase("mar")
					|| month.trim().equalsIgnoreCase("march")) {
				month = "3";
			} else if (month.trim().equalsIgnoreCase("apr")
					|| month.trim().equalsIgnoreCase("april")) {
				month = "4";
			} else if (month.trim().equalsIgnoreCase("may")
					|| month.trim().equalsIgnoreCase("may")) {
				month = "5";
			} else if (month.trim().equalsIgnoreCase("jun")
					|| month.trim().equalsIgnoreCase("june")) {
				month = "6";
			} else if (month.trim().equalsIgnoreCase("jul")
					|| month.trim().equalsIgnoreCase("july")) {
				month = "7";
			} else if (month.trim().equalsIgnoreCase("aug")
					|| month.trim().equalsIgnoreCase("august")) {
				month = "8";
			} else if (month.trim().equalsIgnoreCase("sep")
					|| month.trim().equalsIgnoreCase("september")) {
				month = "9";
			} else if (month.trim().equalsIgnoreCase("oct")
					|| month.trim().equalsIgnoreCase("october")) {
				month = "10";
			} else if (month.trim().equalsIgnoreCase("nov")
					|| month.trim().equalsIgnoreCase("november")) {
				month = "11";
			} else if (month.trim().equalsIgnoreCase("dec")
					|| month.trim().equalsIgnoreCase("december")) {
				month = "12";
			}

			if (month.length() <= 1)
				month = "0" + month;

			if (day.length() <= 1)
				day = "0" + day;

			return new java.sql.Date(DF_YMD.parse(year + month + day).getTime());
		} catch (ParseException e) {
			return null;
		}
	}

	public static Timestamp getTimestamp(String year, String month, String day) {
		try {

			if (StringUtils.isBlank(year) || StringUtils.isBlank(month)
					|| StringUtils.isBlank(day))
				return null;

			return new Timestamp(getDate(year, month, day).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	public static java.sql.Date getBeginWeek(java.sql.Date date) {
		try {

			if (DateFormatUtils.format(date, "E", Locale.US).equalsIgnoreCase(
					"Mon")) {
				return date;
			}

			Calendar cd = Calendar.getInstance();
			java.sql.Date monday = null;
			cd.setTime(date);

			for (int i = -1; i > -7; i--) {
				cd.add(Calendar.DATE, i);

				monday = new java.sql.Date(cd.getTimeInMillis());

				if (DateFormatUtils.format(monday, "E", Locale.US)
						.equalsIgnoreCase("Mon")) {
					break;
				}
			}

			return monday;
		} catch (Exception e) {
			return null;
		}
	}

	public static java.sql.Date getBeginMonth(java.sql.Date date) {
		try {

			String strDate = DateFormatUtils.format(date, "yyyyMMdd");

			if (strDate.substring(6).equals("01")) {
				return date;
			}

			return getSqlDate(strDate.substring(0, 4), strDate.substring(4, 6),
					"01");
		} catch (Exception e) {
			return null;
		}
	}

	public static Date ddMMMyyyyToyyyyMMddDate(String str)
			throws ParseException {

		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

		date = sdf.parse(str);

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

		return java.sql.Date.valueOf(sdf2.format(date));
	}

	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	public static Date datetimeToDate(Date datetime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(datetime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 根据出生日期计算年龄
	 * 
	 * @param birthDay
	 * @return 未来日期返回0
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) {

		if (birthDay == null)
			return -1;

		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			return 0;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		return age;
	}

	public static int getAge(Timestamp birthDay) {
		return getAge(new Date(birthDay.getTime()));
	}

	public static String format(String fmt, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	public static Date parse(String date, String pattern) {
		Date result;
		try {
			result = org.apache.commons.lang.time.DateUtils.parseDate(date,
					new String[] { pattern });
		} catch (Exception e) {
			result = new Date(0);
		}
		return result;
	}

	/**
	 * 根据出生日期计算年龄
	 * 
	 * @param strBirthDay
	 *            字符串型日期
	 * @param format
	 *            日期格式
	 * @return 未来日期返回0
	 * @throws java.text.ParseException
	 * @throws Exception
	 */
	public static int getAge(String strBirthDay, String format)
			throws ParseException {

		DateFormat df = new SimpleDateFormat(format);
		Date birthDay = df.parse(strBirthDay);
		return getAge(birthDay);
	}
	public static String getbeforeN(int n){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,-n);    //得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	public static String getbeforeMinute(int n){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE,-n);    //得到前一天
		Date date = calendar.getTime();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	@SuppressWarnings("unused")
	public  static long getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return hour;
	}


	public static final SimpleDateFormat GMTCa = new SimpleDateFormat(
			"yyyy年M月d日");

	public static String formatDate(String timeStr){
		if(timeStr==null||"".equals(timeStr)){
			return "";
		}
		if(timeStr.trim().length()<8){
			return timeStr;
		}
		if(timeStr.indexOf("月")>0&&timeStr.indexOf("年")<0){
			return "";
		}
		timeStr = timeStr.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", " ");
		
		String strFormatDate=null;
		//2016-05-09
		String[] format = {"((19|20|21)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ",
				"((19|20|21)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]",
				};
		for(String tempFormat:format){
			Pattern pattern = Pattern.compile(tempFormat);
			Matcher matcher = pattern.matcher(timeStr);
	        if(matcher.matches()){
	        	strFormatDate = timeStr;
	        	return strFormatDate;
	        }
		}
		//2016-05-2615:42:52
		String formatSec = "((19|20|21)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(formatSec);
		Matcher matcher = pattern.matcher(timeStr);
        if(matcher.matches()){
        	strFormatDate = timeStr.substring(0,10)+" "+timeStr.substring(10);
        	return strFormatDate;
        }
        
      //2016-06-0814:10 2016-06-08 14:10 2016年06月21日15:18
  		formatSec = "((19|20|21)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])[\\s]?([01]?[0-9]|2[0-3]):[0-5][0-9]";
  		pattern = Pattern.compile(formatSec);
  		matcher = pattern.matcher(timeStr);
        if(matcher.matches()){
           strFormatDate = timeStr.substring(0,10)+" "+timeStr.substring(10).trim()+":00";
      	   return strFormatDate;
        }
        
     
		
		return strFormatDate;
	}

	
	public static boolean isDateFormat(String date){
		boolean result = false;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").format(sf.parse(date));
			result = true;
		} catch (ParseException e) {
			result = false;
		}
		
		return result;
	}
	
	/**
	* 时间格式转换，并统一成"yyyy-mm-dd HH:MM:ss"
	* @param dateTime 待转换时间
	* @param releaseTime 发布时间
	* <p> 描述 : 方法的主要功能和使用场合</p>
	* <p> 备注 : 其他对方法的说明信息</p>
	* @return String
	 */
	public static String convertZwrq(String dateTime,String releaseTime) {
		String year = "";
		String[] darr = dateTime.split("在");
		String date = "";
		try {
			date = StringUtils.isEmpty(darr[0])?darr[1]:darr[0];
			date = date.split("整")[0];
		} catch (Exception e1) {
			e1.printStackTrace();
			date = dateTime;
		}
		if(date.contains("日")){
			date = date.replace(" ", "").replace(" ", "").replace("　", "")
					.replace("　", "").replace("T", "").replace("Z", "");
		}else{
			date = date.replace(" ", "日").replace(" ", "日").replace("　", "日")
					.replace("　", "日").replace("T", "").replace("Z", "");
		}
		if(!date.contains("日")){
    		if(date.contains("上午")){
    			date = date.replace("上午", "日");
    		}
    		if(date.contains("下午")){
    			date = date.replace("下午", "日下午");
    		}
    	}
		date = date.replace("下午日", "下午");
		date = date.replace("许", "").replace("上午", "").replace("　", "");
		date = date.contains("--")?date.split("--")[0]:date;
		date = date.contains("分―")?date.split("分―")[0] + "分":date;
		date = date.contains("分至")?date.split("分至")[0] + "分":date;
		date = date.contains("时至")?date.split("时至")[0] + "时":date;
		date = date.contains("时―")?date.split("时―")[0] + "时":date;
		
		if(date.contains("星期")){
			String[] a = date.split("星期");
			date = a[0] + a[1].substring(1, a[1].length());
		}
		if(date.contains("周")){
			String[] a = date.split("周");
			date = a[0] + a[1].substring(1, a[1].length());
		}
		date = date.replace("点", ":").replace("时", ":").replace("分", ":").replace("秒", ":").replace("：", ":");
		date = date.replace("(", "").replace(")", "").replace(",", "").replace("，", "")
				.replace("（", "").replace("）", "").replace("[", "").replace("]", "").replace(" ", "");
		
		if(!StringUtils.isEmpty(releaseTime) && isDateFormat(releaseTime)){
			year = releaseTime.split("-")[0];
			try{
				if(Integer.parseInt(year) < 999){
					year = "";
				}
			}catch(Exception e){
				e.printStackTrace();
				year = "";
			}
		}
		
		if(!StringUtils.isEmpty(date) && isDateFormat(date)){
			date = date.replaceFirst("-", "年");
			date = date.replaceFirst("-", "月");
			StringBuffer sb = new StringBuffer();
			sb.append(date);
			StringBuilder sbr = new StringBuilder(sb.toString());
			if(!sbr.toString().contains("日")){
				sbr.insert(10, "日");
			}
			String _t = sbr.toString().split("月")[1].split("日")[0];
			try {
				if(Integer.parseInt(_t.replace(" ", "").replace(" ", "").replace("　", "")
						.replace("　", "")) > 31){
					sbr = new StringBuilder(sb.toString());
					sbr.insert(9, "日");
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			date = sbr.toString();
		}
		
		if(date.contains(":")){
    		String[] arr = date.split(":");
    		if(arr.length == 2){
    			//有时分
    			date = arr[0] + "时" + arr[1] + "分";
    		}
    		if(arr.length == 3){
    			//有时分秒
    			date = arr[0] + "时" + arr[1] + "分" + arr[2] + "秒";
    		}
    		if(arr.length == 1){
    			//只有时
    			date = arr[0] + "时";
    		}
    	}
		
    	Date d = null;
		try {
			d = convertCnDate(date);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("传递的日期是：" + dateTime + " 组装日期：" + date + " 发布日期：" + releaseTime);
			return dateTime;
		}
    	if(date.contains("时")){
    		if(date.contains("年")){
    			if(date.contains("分")){
    				if(date.contains("秒")){
    					date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
    				}else{
    					date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(d);
    					date = date + ":00";
    				}
    			}else{
    				date = new SimpleDateFormat("yyyy-MM-dd HH").format(d);
    				date = date + ":00:00";
    			}
    		}else{
    			if(date.contains("分")){
    				if(date.contains("秒")){
    					date = new SimpleDateFormat("MM-dd HH:mm:ss").format(d);
    				}else{
    					date = new SimpleDateFormat("MM-dd HH:mm").format(d);
    					date = date + ":00";
    				}
    			}else{
    				date = new SimpleDateFormat("MM-dd HH").format(d);
    				date = date + ":00:00";
    			}
    			if(!StringUtils.isEmpty(year)){
    				date = year + "-" + date;
    	    	}
    		}
    	}else{
    		if(date.contains("年")){
    			date = new SimpleDateFormat("yyyy-MM-dd").format(d);
    		}else{
    			date = new SimpleDateFormat("MM-dd").format(d);
    			if(!StringUtils.isEmpty(year)){
    				date = year + "-" + date;
    	    	}
    		}
    		date = date + " 00:00:00";
    	}
    	
        return date;
    }
	
    public static Date convertCnDate(String cprq) {
    	if(!cprq.contains("日")){
    		if(cprq.contains("上午")){
    			cprq = cprq.replace("上午", "日");
    		}
    		if(cprq.contains("下午")){
    			cprq = cprq.replace("下午", "日下午");
    		}
    	}else{
    		if(cprq.contains("上午")){
    			cprq = cprq.replace("上午", "");
    		}
    	}
    	cprq = cprq.replace("上午", "").replace("　", "");
        int monthPos = cprq.indexOf("月");
        int dayPos = cprq.indexOf("日");
        int hourPos = cprq.indexOf("时");
        int minutePos = cprq.indexOf("分");
        int secondsPos = cprq.indexOf("秒");
        
        String cnDay = cprq.substring(monthPos + 1, dayPos);
        String day = ConvertCnDateNumber(cnDay);
        day = StringUtils.isEmpty(day)?"":day.replace(" ", "");
        
        Calendar c = Calendar.getInstance();
        if(cprq.contains("年")){
    		 int yearPos = cprq.indexOf("年");
    		 String cnYear = cprq.substring(0, yearPos);
    	     String cnMonth = cprq.substring(yearPos + 1, monthPos);
    	     String year = ConvertCnYear(cnYear).replace(" ", "");
    	     String month = ConvertCnDateNumber(cnMonth).replace(" ", "");
    	     c.set(Calendar.YEAR, Integer.parseInt(year));
    	     c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
    	}else{
	   	     String cnMonth = cprq.substring(0, monthPos);
	   	     String month = ConvertCnDateNumber(cnMonth);
	   	     month = StringUtils.isEmpty(month)?"":month.replace(" ", "");
	   	  if(!StringUtils.isEmpty(month)){
	   	     c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
	   	  }
    	}
        if(!StringUtils.isEmpty(day)){
        	c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        }
        
        if(cprq.contains("时")){
        	String cnHour = cprq.substring(dayPos + 1, hourPos);
        	if(cnHour.contains("）")){
        		cnHour = cnHour.split("）")[1];
        	}
        	if(cnHour.contains(")")){
        		cnHour = cnHour.split(")")[1];
        	}
        	cnHour = cnHour.replace("上午", "");
        	boolean xw = false;
        	if(cnHour.contains("下午")){
        		cnHour = cnHour.replace("下午", "");
        		xw = true;
        	}
        	String hour = ConvertCnNumberChar(cnHour).replace(" ", "");
        	if(xw && !hour.contains("十")){
        		hour = String.valueOf(Integer.parseInt(hour) > 12?Integer.parseInt(hour):Integer.parseInt(hour) + 12);
        	}
        	if(hour.contains("十")){
        		String[] mArr = hour.split("十");
        		if(mArr.length == 2){
        			hour = String.valueOf((StringUtils.isEmpty(mArr[0])?10:Integer.parseInt(mArr[0]) * 10)
        					+ Integer.parseInt(mArr[1]));
        		}else{
        			if(mArr.length == 0){
        				hour = "10";
        			}else{
        				hour = String.valueOf(10 + (StringUtils.isEmpty(mArr[0])?0:Integer.parseInt(mArr[0])));
        			}
        		}
        	}
        	hour = StringUtils.isEmpty(hour)?"0":hour;
        	c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.replace(" ", "").replace("　", "").replace(" ", "")));
        }
        
        if(cprq.contains("分")){
        	String cnMinute = cprq.substring(hourPos + 1, minutePos);
        	String minute = ConvertCnNumberChar(cnMinute).replace(" ", "").replace("　", "").replace(" ", "");
        	if(minute.contains("十")){
        		String[] mArr = minute.split("十");
        		if(mArr.length == 2){
        			minute = String.valueOf((StringUtils.isEmpty(mArr[0])?10:Integer.parseInt(mArr[0]) * 10) + Integer.parseInt(mArr[1]));
        		}else{
        			if(mArr.length == 0){
        				minute = "10";
        			}else{
        				minute = String.valueOf(10 * (StringUtils.isEmpty(mArr[0])?1:Integer.parseInt(mArr[0])));
        			}
        		}
        	}
        	minute = StringUtils.isEmpty(minute)?"0":minute;
        	c.set(Calendar.MINUTE, minute.trim().equals("半")?30:Integer.parseInt(minute.replace(" ", "")));
        }
        
        if(cprq.contains("秒")){
        	String cnSeconds = cprq.substring(minutePos + 1, secondsPos);
            String seconds = ConvertCnNumberChar(cnSeconds).replace(" ", "").replace("　", "").replace(" ", "");
            if(seconds.contains("十")){
        		String[] mArr = seconds.split("十");
        		if(mArr.length == 2){
        			seconds = String.valueOf((StringUtils.isEmpty(mArr[0])?10:Integer.parseInt(mArr[0]) * 10) + Integer.parseInt(mArr[1]));
        		}else{
        			if(mArr.length == 0){
        				seconds = "10";
        			}else{
        				seconds = String.valueOf(10 * (StringUtils.isEmpty(mArr[0])?1:Integer.parseInt(mArr[0])));
        			}
        		}
        	}
            seconds = StringUtils.isEmpty(seconds)?"0":seconds;
            seconds = seconds.equals("00.0")?"0":seconds;
            c.set(Calendar.SECOND, Integer.parseInt(seconds));
        }
        
        return c.getTime();
    }

    private static String ConvertCnYear(String cnYear) {
        if(cnYear.length() == 2)
           return "20" + ConvertCnNumberChar(cnYear);
        else
            return ConvertCnNumberChar(cnYear);
    }

    private static String ConvertCnDateNumber(String cnNumber) {
        if (cnNumber.length() == 1) {
            if(cnNumber.equals("十")){
            	return "10";
            }else{
            	return ConvertCnNumberChar(cnNumber);
            }
        } else if (cnNumber.length() == 2) {
            if (cnNumber.startsWith("十")) {
                return "1" + ConvertCnNumberChar(cnNumber.substring(1, 2));
            } else if (cnNumber.endsWith("十")) {
                return ConvertCnNumberChar(cnNumber.substring(0, 1)) + "0";
            } else {
                return ConvertCnNumberChar(cnNumber);
            }
        } else if (cnNumber.length() == 3) {
            return ConvertCnNumberChar(cnNumber.substring(0, 1) + cnNumber.substring(2, 3));
        }
        return null;
    }

    private static String ConvertCnNumberChar(String cnNumberStr) {
        String ALL_CN_NUMBER = "O〇○Ｏ零一二两三四五六七八九�整?";
        String ALL_NUMBER = "000001223456789000";
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < cnNumberStr.length(); i++) {
            char c = cnNumberStr.charAt(i);
            int index = ALL_CN_NUMBER.indexOf(c);
            if (index != -1) {
                buf.append(ALL_NUMBER.charAt(index));
            } else {
                buf.append(cnNumberStr.charAt(i));
            }
        }
        return buf.toString();
    }

}
