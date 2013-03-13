package com.pedEdt.frontEnd.client.util;

import java.util.Date;

public class DateUtil {

	public static Date getDate(int fromBD) {
		long longDate = Long.parseLong(String.valueOf(fromBD).concat("000"));
		Date res = new Date(longDate);
		return res;
	}
	
}
