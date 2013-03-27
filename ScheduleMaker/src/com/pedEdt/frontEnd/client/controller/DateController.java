package com.pedEdt.frontEnd.client.controller;

import com.pedEdt.frontEnd.client.util.DateUtil;
import com.pedEdt.frontEnd.client.view.SeanceWidget;

public class DateController {
		
	public static void linkDateToSeanceWidget(SeanceWidget widget, int posH, int posV) {
		long newDate = DateUtil.computeNewDate(posH, posV);
		
		if(widget.getBeginning() != 0) //if it is not a new SeanceWidget
			widget.getTeaching().removeSeance(widget.getBeginning());
		
		widget.getTeaching().addSeance(DateUtil.setDateToDB(newDate));
		ServerCommunication.getInstance().updateTeaching(widget.getTeaching());
		widget.setBeginning(DateUtil.setDateToDB(newDate));
	}
	
	public static void unlinkTheSeanceWidget(SeanceWidget widget) {
		widget.getTeaching().removeSeance(widget.getBeginning());
		ServerCommunication.getInstance().updateTeaching(widget.getTeaching());
	}
	
}
