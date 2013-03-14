package com.pedEdt.frontEnd.client.util;

import java.util.Date;

public class DateUtil {

	public static Date getDate(long fromBD) {
		return new Date(fromBD * 1000);
	}
	
}
