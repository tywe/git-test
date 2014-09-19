package org.teng.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimerTool {

	private final static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 将日期格式化成：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date) {
		synchronized (dateFormat) {
			return dateFormat.format(date);
		}
	}

	/**
	 * 将日期时间格式化成：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTimeToString(Date date) {
		synchronized (timeFormat) {
			return timeFormat.format(date);
		}
	}

	/**
	 * 将字符串转换成日期
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStringToDate(String dateString) throws ParseException {
		synchronized (dateFormat) {
			return dateFormat.parse(dateString);
		}
	}

	/**
	 * 将字符串转换成日期时间
	 * 
	 * @param timeString
	 * @return
	 * @throws ParseException
	 */
	public static Date formatStringToTime(String timeString) throws ParseException {
		synchronized (timeFormat) {
			return timeFormat.parse(timeString);
		}
	}

	public static Date getTimeInHour(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getTimeInDay(Date time) throws ParseException {
		String[] date = formatTimeToString(time).split(" ");
		String re = date[0] + " 00:00:00";
		return formatStringToDate(re);
	}

	public static String getNowDateTimeString() {
		Date now = new Date();
		return formatTimeToString(now);
	}

	public static String getNowDateString() {
		Date now = new Date();
		return formatDateToString(now);
	}

	public static Date getDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date date = calendar.getTime();
		return date;
	}

	public static int getDayOfWeek(Date day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(day);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static Date formatTimeInHour(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数（负数表示当前时间点以前的日期）
	 * @return
	 */
	public static Date addDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeInMillis(calendar.getTimeInMillis() + ((long) day) * 24 * 3600 * 1000);
		return calendar.getTime();
	}

	/**
	 * 时间相加
	 * 
	 * @param date
	 *            日期
	 * @param second
	 *            秒（负数表示当前时间点以前的时间）
	 * @return
	 */
	public static Date addTime(Date date, int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeInMillis(calendar.getTimeInMillis() + ((long) second) * 1000);
		return calendar.getTime();
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取当前日期所在月份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int lastDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, lastDay);
		Date lastDate = c.getTime();

		return lastDate;
	}

	/**
	 * 获取当前日期所在月份的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, lastDay);
		Date lastDate = c.getTime();

		return lastDate;
	}

	/**
	 * 获取当期日期所在周的第一天（周一）
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(4);
		c.setTime(date);
		// 周一Monday对应的是2，为最小值 + 1
		int lastDay = c.getActualMinimum(Calendar.DAY_OF_WEEK);
		c.set(Calendar.DAY_OF_WEEK, lastDay + 1);
		Date lastDate = c.getTime();

		return lastDate;
	}

	/**
	 * 获取当期日期所在周的最后一天（周日）
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(4);
		c.setTime(date);
		// 周日Sunday对应的是1，为最小值
		int lastDay = c.getActualMinimum(Calendar.DAY_OF_WEEK);
		c.set(Calendar.DAY_OF_WEEK, lastDay);
		Date lastDate = c.getTime();

		return lastDate;
	}

	/**
	 * 获取从开始日期到截止日期之间跨越的月份（升序）
	 * 
	 * @param fromDate
	 *            开始日期，格式：2012-02-05
	 * @param toDate
	 *            截止日期，格式：2012-02-05
	 * @return 月份的格式是：2014-02
	 */
	public static List<String> getMonthsFromDates(String fromDateStr, String toDateStr)
			throws Exception {
		if (StringHandler.isNullorEmpty(fromDateStr) || StringHandler.isNullorEmpty(toDateStr)) {
			throw new Exception("开始日期和截止日期都不能为空！");
		}
		if (fromDateStr.compareTo(toDateStr) > 0) {
			throw new Exception("开始日期必须小于等于截止日期！");
		}

		Date fromDate = TimerTool.formatStringToDate(fromDateStr);
		Date toDate = TimerTool.formatStringToDate(toDateStr);

		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		fromCal.setTime(fromDate);
		// 将截止日期设置到当前月的最后一天
		toCal.setTime(TimerTool.getLastDayOfMonth(toDate));

		List<String> months = new ArrayList<String>();
		while (fromCal.compareTo(toCal) <= 0) {
			String month = TimerTool.formatDateToString(fromCal.getTime());
			month = month.substring(0, month.lastIndexOf("-"));
			months.add(month);

			// 开始日期加一个月直到等于结束日期为止
			fromCal.add(Calendar.MONTH, 1);
		}

		return months;
	}

	/**
	 * 获取从开始日期到截止日期之间跨越的周（升序）
	 * 
	 * @param fromDate
	 *            开始日期，格式：2012-02-05
	 * @param toDate
	 *            截止日期，格式：2012-02-05
	 * @return 周的格式是：201402
	 */
	public static List<String> getWeeksFromDates(String fromDateStr, String toDateStr)
			throws Exception {
		if (StringHandler.isNullorEmpty(fromDateStr) || StringHandler.isNullorEmpty(toDateStr)) {
			throw new Exception("开始日期和截止日期都不能为空！");
		}
		if (fromDateStr.compareTo(toDateStr) > 0) {
			throw new Exception("开始日期必须小于等于截止日期！");
		}

		Date fromDate = TimerTool.formatStringToDate(fromDateStr);
		Date toDate = TimerTool.formatStringToDate(toDateStr);

		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();

		// 设置日期计算模式
		fromCal.setFirstDayOfWeek(Calendar.MONDAY);
		fromCal.setMinimalDaysInFirstWeek(4);
		fromCal.setTime(fromDate);
		// 将截止日期设置到当前月的最后一天
		toCal.setTime(TimerTool.getLastDayOfWeek(toDate));

		List<String> weeks = new ArrayList<String>();
		while (fromCal.compareTo(toCal) <= 0) {
			int year = fromCal.get(Calendar.YEAR);
			int week = fromCal.get(Calendar.WEEK_OF_YEAR);
			// 第一周的年份取开始日期所在周的最后一天的年份
			if (week == 1) {
				Calendar tmpCal = Calendar.getInstance();
				tmpCal.setTime(getLastDayOfWeek(fromCal.getTime()));
				year = tmpCal.get(Calendar.YEAR);
			}

			String weekString = null;
			if (week < 10) {
				weekString = String.valueOf(year) + "0" + week;
			} else {
				weekString = String.valueOf(year) + week;
			}
			weeks.add(weekString);

			// 开始日期加7天直到等于结束日期为止
			fromCal.add(Calendar.DATE, 7);
		}

		return weeks;
	}

	/**
	 * 获取从开始日期到截止日期之间日期（升序）
	 * 
	 * @param fromDate
	 *            开始日期，格式：2012-02-05
	 * @param toDate
	 *            截止日期，格式：2012-02-05
	 * @return 日期的格式是：2014-02-04
	 */
	public static List<String> getDaysFromDates(String fromDateStr, String toDateStr)
			throws Exception {
		if (StringHandler.isNullorEmpty(fromDateStr) || StringHandler.isNullorEmpty(toDateStr)) {
			throw new Exception("开始日期和截止日期都不能为空！");
		}
		if (fromDateStr.compareTo(toDateStr) > 0) {
			throw new Exception("开始日期必须小于等于截止日期！");
		}

		Date fromDate = TimerTool.formatStringToDate(fromDateStr);
		Date toDate = TimerTool.formatStringToDate(toDateStr);

		Calendar fromCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		fromCal.setTime(fromDate);
		toCal.setTime(toDate);

		List<String> days = new ArrayList<String>();
		while (fromCal.compareTo(toCal) <= 0) {
			String day = TimerTool.formatDateToString(fromCal.getTime());
			days.add(day);

			// 开始日期加1天直到等于结束日期为止
			fromCal.add(Calendar.DATE, 1);
		}

		return days;
	}

	public static void main(String[] args) throws Exception {
		Date now = new Date();
		System.out.println(formatDateToString(getLastDayOfMonth(now)));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2012);
		calendar.set(Calendar.MONTH, 10 - 1);
		String dayDate = TimerTool.formatDateToString(TimerTool.getLastDayOfMonth(calendar
				.getTime())) + " 00:00:00";
		System.out.println(dayDate);

		System.out.println(formatDateToString(getFirstDayOfWeek(formatStringToDate("2014-02-27"))));
		System.out.println(formatDateToString(getLastDayOfWeek(formatStringToDate("2014-02-27"))));
		System.out
				.println(formatDateToString(getFirstDayOfMonth(formatStringToDate("2014-02-27"))));
		System.out.println(formatDateToString(getLastDayOfMonth(formatStringToDate("2014-02-27"))));

		List<String> months = getMonthsFromDates("2012-05-12", "2014-02-01");
		for (String month : months) {
			System.out.println(month);
		}
		List<String> weeks = getWeeksFromDates("2014-01-05", "2014-02-03");
		for (String week : weeks) {
			System.out.println(week);
		}
		List<String> days = getDaysFromDates("2014-01-05", "2014-03-01");
		for (String day : days) {
			System.out.println(day);
		}
	}
}