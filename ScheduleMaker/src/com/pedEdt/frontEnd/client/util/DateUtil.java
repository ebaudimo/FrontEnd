package com.pedEdt.frontEnd.client.util;

import java.util.Date;
import com.pedEdt.frontEnd.client.view.ScheduleNavigationBar;

public class DateUtil {
	
	public static long WEEK = 604800000;
	public static long DAY = 86400000;
	public static long HOUR = 3600000;
	
	public static Date getDate(long fromBD) {
		return new Date(fromBD * 1000); // for milliseconds
	}
	
	public static Date addWeek(Date startDate, int nbWeek) {
		return new Date(startDate.getTime() + WEEK * nbWeek);
	}
	
	//return the start of the week (Monday morning, 00h 00m etc.)
 	public static long getStartWeek(Date date) {
		long current = date.getTime();
		
		switch(date.getDay()) {
		case 0: //Sunday
			current = current - DAY * 6;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
			break;
		case 1: //Monday
			current = current - HOUR * date.getHours(); //subtract the hours
			current = current - 60000 * date.getMinutes(); //subtract the minutes
			break;
		case 2:
			current = current - DAY;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
			break;
		case 3:
			current = current - DAY * 2;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
			break;
		case 4:
			current = current - DAY * 3;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
			break;
		case 5:
			current = current - DAY * 4 ;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
			break;
		case 6:
			current = current - DAY * 5 * 1000;
			current = current - HOUR * date.getHours(); 
			current = current - 60000 * date.getMinutes();
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
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 1: //Monday
			current = current + DAY * 6;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 2:
			current = current + DAY * 5;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 3:
			current = current + DAY * 4;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 4:
			current = current + DAY * 3;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 5:
			current = current + DAY * 2;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
			break;
		case 6:
			current = current + DAY;
			current = current + HOUR * (23 - date.getHours()); 
			current = current + 60000 * (59 - date.getMinutes());
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
		
		Date navigation = new Date(navBar.getStart() + WEEK * (navBar.getCurrentValue()-1));
		
		Date startWeek = new Date(getStartWeek(navigation));
		Date endWeek = new Date(getEndWeek(navigation));
		
		return inPeriod(search, startWeek, endWeek);
		
	}
	
	//return the long for a new TeachingWidgetSession (create or update)
	public static long computeNewDate(int posH, int posV) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date start = getDate(navBar.getStart());	
		int diff = (posH + 1) - start.getDay();
		long currentDate = navBar.getStart() + WEEK * navBar.getCurrentValue() + diff * DAY + posV * 600;
		return currentDate;
	}

	public static int findPosH(Date sessionDate) {
		switch(sessionDate.getDay()) {
		case 1: //Monday
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 3;
		case 5: //Friday
			return 4;
		default:
			return -1;
		}
	}
	
	public static int findPosV(Date sessionDate) {
		int hour = sessionDate.getHours();
		int min = sessionDate.getMinutes();
		
		switch(hour) {
		case 8: {
			return min / 10;
		}
		case 9: {
			return min / 10 + 6;
		}
		case 10: {
			return min / 10 + 6 * 2;
		}
		case 11: {
			return min / 10 + 6 * 3;
		}
		case 12: {
			return min / 10 + 6 * 4;
		}
		case 13: {
			return min / 10 + 6 * 5;
		}
		case 14: {
			return min / 10 + 6 * 6;
		}
		case 15: {
			return min / 10 + 6 * 7;
		}
		case 16: {
			return min / 10 + 6 * 8;
		}
		case 17: {
			return min / 10 + 6 * 9;
		}
		default:
			return -1;
		}
	}
	
	public static long getNbWeek(Date start, Date end) {
		return (end.getTime() - start.getTime()) / WEEK;
	}
}
