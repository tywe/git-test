package org.teng.java.basic.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarTest {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static void calendarBasicTest() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println("year:" + calendar.get(Calendar.YEAR) + ", month:"
				+ calendar.get(Calendar.MONTH) + ", day:"
				+ calendar.get(Calendar.DAY_OF_MONTH) + ", time:"
				+ calendar.get(Calendar.HOUR) + ":"
				+ calendar.get(Calendar.MINUTE));
		System.out.println(dateFormat.format(calendar.getTime()));
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		System.out.println(dateFormat.format(calendar.getTime()));
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 1);
		System.out.println(dateFormat.format(calendar.getTime()));
		Calendar bc = Calendar.getInstance();
		bc.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 30);
		Calendar ac = Calendar.getInstance();
		ac.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 30);

		System.out.println("====================");
		while (ac.after(bc)) {
			System.out.println(dateFormat.format(bc.getTime()));
			bc.set(Calendar.DAY_OF_MONTH, bc.get(Calendar.DAY_OF_MONTH) + 1);
		}
	}

	public static List<Date> completeDateRange(List<Date> dateList, int timeSpan) {
		if (dateList == null || dateList.size() == 0)
			return null;

		List<Date> completeDateList = new ArrayList<Date>();
		Calendar fcr = Calendar.getInstance();
		Calendar lcr = Calendar.getInstance();
		int size = dateList.size();
		int firstIdx = 0;
		int lastIdx = 1;
		Date curDate = null;

		if (timeSpan == 0) {// 按天补齐
			curDate = dateList.get(firstIdx);
			while (lastIdx < size) {
				fcr.setTime(curDate);
				lcr.setTime(dateList.get(lastIdx));
				if (lcr.after(fcr)
						&& lcr.get(Calendar.YEAR) == fcr.get(Calendar.YEAR)
						&& lcr.get(Calendar.MONTH) == fcr.get(Calendar.MONTH)
						&& lcr.get(Calendar.DAY_OF_MONTH)
								- fcr.get(Calendar.DAY_OF_MONTH) == 1) {
					firstIdx++;
					lastIdx++;
					completeDateList.add(curDate);
					continue;
				} else {
					completeDateList.add(curDate);
					fcr.set(Calendar.DAY_OF_MONTH,
							fcr.get(Calendar.DAY_OF_MONTH) + 1);
					curDate = fcr.getTime();
					continue;
				}
			}
			completeDateList.add(dateList.get(size - 1));
		} else {// 同天，按时段补齐
			while (lastIdx < size) {
				curDate = dateList.get(firstIdx);
				while (lastIdx < size) {
					fcr.setTime(curDate);
					lcr.setTime(dateList.get(lastIdx));
					if ((lcr.get(Calendar.HOUR_OF_DAY) - fcr
							.get(Calendar.HOUR_OF_DAY)) <= 1) {
						firstIdx++;
						lastIdx++;
						completeDateList.add(curDate);
						continue;
					} else {
						completeDateList.add(curDate);
						fcr.set(Calendar.HOUR_OF_DAY,
								fcr.get(Calendar.HOUR_OF_DAY) + 1);
						curDate = fcr.getTime();
						continue;
					}
				}
				completeDateList.add(dateList.get(size - 1));
			}
		}

		return completeDateList;
	}

	public static void compeletByDay() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		List<Date> dateList = new ArrayList<Date>();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) - 3);
		Date bDate = calendar.getTime();
		System.out.println(dateFormat.format(bDate));
		dateList.add(bDate);

		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.get(Calendar.DAY_OF_MONTH) + 2);
		Date eDate = calendar.getTime();
		System.out.println(dateFormat.format(eDate));
		dateList.add(eDate);

		// calendar.setTime(new Date());
		// calendar.set(Calendar.DAY_OF_MONTH,
		// calendar.get(Calendar.DAY_OF_MONTH) + 4);
		// eDate = calendar.getTime();
		// System.out.println(dateFormat.format(eDate));
		// dateList.add(eDate);

		dateList = completeDateRange(dateList, 0);
		System.out.println("=============================");
		for (Date de : dateList) {
			System.out.println(dateFormat.format(de));
		}
	}

	public static void completeByHour() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		List<Date> dateList = new ArrayList<Date>();
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) - 2);
		Date bDate = calendar.getTime();
		System.out.println(dateFormat.format(bDate));
		dateList.add(bDate);

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 2);
		Date eDate = calendar.getTime();
		System.out.println(dateFormat.format(eDate));
		dateList.add(eDate);
		dateList = completeDateRange(dateList, 1);
		System.out.println("=============================");
		for (Date de : dateList) {
			System.out.println(dateFormat.format(de));
		}
	}

	public static void isDateEquals() {
		try {
			Date date1 = dateFormat.parse("2014-08-01 10:10:10");
			Date date2 = dateFormat.parse("2014-08-02 10:10:10");
			System.out.println("date1:" + dateFormat.format(date1) + ", date2:"
					+ dateFormat.format(date2));
			if (date1.equals(date2))
				System.out.println("date1:" + date1 + " equals date2:" + date2);
			else
				System.out.println("date1:" + date1 + " not equals date2:"
						+ date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ParseException {
		// calendarBasicTest();
		// compeletByDay();
		// completeByHour();

	}
}
