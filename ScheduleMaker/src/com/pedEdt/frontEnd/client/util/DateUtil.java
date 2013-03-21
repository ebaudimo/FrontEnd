package com.pedEdt.frontEnd.client.util;

import java.util.Date;
import com.pedEdt.frontEnd.client.view.ScheduleNavigationBar;

public class DateUtil {
	
	public static long WEEK = 604800;
	public static long DAY = 86400;
	public static long HOUR = 3600;
	
	public static Date getDate(long fromBD) {
		return new Date(fromBD * 1000); //*1000 for milliseconds
	}
	
	//why need a Date object : with long i am not sure if it's from database (without millisecond) or somewhere else  
	public static Date addWeek(Date startDate, int nbWeek) {
		return new Date(startDate.getTime() + (WEEK*1000)*nbWeek);
	}
	
	//return the start of the week (Monday morning, 00h 00m 01s)
 	public static long getStartWeek(Date date) {
		long current = date.getTime();
		
		switch(date.getDay()) {
		case 0: //Sunday
			current = current - DAY * 6 * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		case 1: //Monday
			current = current - (HOUR*1000) * date.getHours(); //subtract the hours
			current = current - (60*1000) * date.getMinutes(); //subtract the minutes
			break;
		case 2:
			current = current - DAY * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		case 3:
			current = current - DAY * 2 * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		case 4:
			current = current - DAY * 3 * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		case 5:
			current = current - DAY * 4 * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		case 6:
			current = current - DAY * 5 * 1000;
			current = current - (HOUR*1000) * date.getHours(); 
			current = current - (60*1000) * date.getMinutes();
			break;
		default:
			break;
		}
		
		return current;
		
	}
	
	//return the end of the week
	public static long getEndWeek(Date date) {
		long current = date.getTime();
		
		switch(date.getDay()) {
		case 0: //Sunday
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 1: //Monday
			current = current + (DAY*1000) * 6;
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 2:
			current = current + (DAY*1000) * 5;
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 3:
			current = current + (DAY*1000) * 4;
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 4:
			current = current + (DAY*1000) * 3;
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 5:
			current = current + (DAY*1000) * 2;
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		case 6:
			current = current + (DAY*1000);
			current = current + (HOUR*1000) * (23 - date.getHours()); 
			current = current + (60*1000) * (59 - date.getMinutes());
			break;
		default:
			break;
		}
		
		return current;
	}
	
	public static boolean inPeriod(Date date, Date start, Date end) {
		if(date.before(end) && date.after(start))
			return true;
		
		return false;
	}

	public static boolean inThisWeek(Date search) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date navigation = new Date(navBar.getStart() * navBar.getCurrentValue());
		
		Date startWeek = new Date(getStartWeek(navigation));
		Date endWeek = new Date(getEndWeek(navigation));
		
		return inPeriod(getDate(search.getTime()), startWeek, endWeek);
		
	}
	
	//return the long for a new TeachingWidgetSession (create or update)
	public static long computeNewDate(int posH, int posV) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date start = getDate(navBar.getStart());	
		int diff = (posH + 1) - start.getDay();
		long currentDate = navBar.getStart() + WEEK * navBar.getCurrentValue() + diff * DAY + posV * 600;
		return currentDate;
	}

	public static int findPosH(long sessionDate) {
		Date myDate = new Date(sessionDate);	
		switch(myDate.getDay()) {
		case 1: //Monday
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 3;
		case 5: 
			return 4;
		default:
			return -1;
		}
	}
	
	public static int findPosV(long sessionDate) {
		Date myDate = new Date(sessionDate);

		int hour = myDate.getHours();
		int min = myDate.getMinutes();
		/*
		switch(myDate.getHours()) {
		case 8:
			return 
		}
		*/
		return 5;
		
	}
	
	public static long getNbWeek(Date start, Date end) {
		return (end.getTime() - start.getTime()) / (WEEK*1000);
	}
}
