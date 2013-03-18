package com.pedEdt.frontEnd.client.util;

import java.util.Date;

public class DateUtil {
	
	public static long WEEK = 604800;
	public static long DAY = 86400;
	public static long HOUR = 3600;
	
	public static Date getDate(long fromBD) {
		return new Date(fromBD * 1000);
	}
	
	public static boolean inPeriod(Date date, Date start, Date end) {
		if(date.before(end) && date.after(start))
			return true;
		
		return false;
	}

	public static long computeNewDate(long startSemester, int currentWeek, int posH, int posV) {
		Date start = getDate(startSemester);
		long currentDate = 0;
		//TODO : int contains '-' ? If yes, if else structure is not needed
		if(start.getDay() == posH + 1) { //same day in the week	
			currentDate = startSemester + WEEK * currentWeek + posV * 600;
		}
		else if(start.getDay() < posH + 1) {
			int diff = (posH + 1) - start.getDay();
			currentDate = startSemester + WEEK * currentWeek + diff * DAY + posV * 600;
		}
		else { //start.getDay() > posH + 1
			int diff = start.getDay() - (posH + 1);
			currentDate = startSemester + WEEK * currentWeek - diff * DAY + posV * 600;
		}
		return currentDate;
	}
	
}
