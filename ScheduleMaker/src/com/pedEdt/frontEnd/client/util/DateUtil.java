package com.pedEdt.frontEnd.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.pedEdt.frontEnd.client.view.ScheduleNavigationBar;

/**
 * 
 * Tool class for many method with or on date for our application
 *
 */
public class DateUtil {
	
	public static long WEEK = 604800000;
	public static long DAY = 86400000;
	public static long HOUR = 3600000;
	public static long TEN_MINUTES = 600000;
	
	//in database the date is in long, which represent date in second
	//java.util.date need a long in millisecond to build a date
	public static Date getDate(long fromBD) {
		return new Date(fromBD * 1000); // for milliseconds
	}
	
	//reverse of the previous method
	public static long setDateToDB(Date date) {
		Long l = date.getTime();
		String tmp = l.toString().substring(0, l.toString().length()-3);
		return Long.valueOf(tmp);
	}
	
	public static long setDateToDB(Long date) {
		String tmp = date.toString().substring(0, date.toString().length()-3);
		return Long.valueOf(tmp);
	}
	
	//convert hours int into minutes representation in int
	public static int hoursToMinutes(int nbHour) {
		return nbHour * 60;
	}
	
	//reverse of the previous
	public static int minutesToHours(int nbMin) {
		return nbMin / 60;
	}
	
	public static Date addWeek(Date startDate, int nbWeek) {
		return new Date(startDate.getTime() + WEEK * nbWeek);
	}
	
	//return the start of the week (Monday morning)
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
	
	//return the end of the week (Sunday 00h00)
	public static long getEndWeek(Date date) {
		long current = date.getTime();
		
		switch(date.getDay()) {
		case 0: //Sunday
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 1: //Monday
			current = current + DAY * 6;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 2:
			current = current + DAY * 5;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 3:
			current = current + DAY * 4;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 4:
			current = current + DAY * 3;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 5:
			current = current + DAY * 2;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		case 6:
			current = current + DAY;
			//current = current + HOUR * (23 - date.getHours()); 
			//current = current + 60000 * (59 - date.getMinutes());
			break;
		default:
			break;
		}
		
		return current;
	}
	
	//test if my date is between the two others
	public static boolean inPeriod(Date search, Date start, Date end) {
		if(search.before(end) && search.after(start))
			return true;
		
		return false;
	}

	//test if my date is in the current week
	public static boolean inThisWeek(Date search) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date navigation = new Date(navBar.getStart() + WEEK * (navBar.getCurrentValue()-1));
		
		Date startWeek = new Date(getStartWeek(navigation));
		Date endWeek = new Date(getEndWeek(navigation));
		
		return inPeriod(search, startWeek, endWeek);
		
	}
	
	//test if my date is in the current semester
	public static boolean inThisSemester(Date search) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		return inPeriod(search, new Date(navBar.getStart()), new Date(navBar.getEnd()));
		
	}
	
	//return the long for a new TeachingWidgetSession (create or update)
	public static long computeNewDate(int posH, int posV) {
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date start = new Date(navBar.getStart());
		int diff = (posH + 1) - start.getDay();
		
		long currentDate = navBar.getStart() + WEEK * (navBar.getCurrentValue()-1) + diff * DAY + (HOUR * 8 + posV * TEN_MINUTES);
		return currentDate;
	}

	//find the position H (the day) in the grid with the date desired
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
	
	//find the position V (hours and minutes) in the grid with the date desired
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
	
	//return the number of week between two dates
	public static long getNbWeek(Date start, Date end) {
		return (getEndWeek(end) - getStartWeek(start) + DAY) / WEEK; //+ DAY because the getEndWeek return the Sunday morning
	}

	//return index of semester which start at start, return only 1 (for July to December) or 2 (January to June)
	public static int getIndexSemester(Date start) {
		if(start.getMonth() - 5 > 0)
			return 1;
		else
			return 2;
	}

	//return a string representation of date for the header column label
	public static String buildWeekHeader(int indexDay) {
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM");
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance();
		
		Date navigation = new Date(navBar.getStart() + WEEK * (navBar.getCurrentValue()-1));
		Date startWeek = new Date(getStartWeek(navigation));
				
		long currentDate = startWeek.getTime();
		
		switch(indexDay) {
		case 1: //Monday
			return fmt.format(new Date(currentDate));
		case 2:
			currentDate = currentDate + DAY;
			return fmt.format(new Date(currentDate));
		case 3:
			currentDate = currentDate + DAY * 2;
			return fmt.format(new Date(currentDate));
		case 4:
			currentDate = currentDate + DAY * 3;
			return fmt.format(new Date(currentDate));
		case 5: //Friday
			currentDate = currentDate + DAY * 4;
			return fmt.format(new Date(currentDate));
		default:
			return "";
		}
	}
}
