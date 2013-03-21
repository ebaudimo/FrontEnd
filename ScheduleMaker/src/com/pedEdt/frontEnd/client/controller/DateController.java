package com.pedEdt.frontEnd.client.controller;

import com.pedEdt.frontEnd.client.util.DateUtil;
import com.pedEdt.frontEnd.client.view.ScheduleNavigationBar;
import com.pedEdt.frontEnd.client.view.SeanceWidget;

public class DateController {

	private ScheduleNavigationBar timeKeeper;
	
	private static DateController instance;
	
	public static DateController getInstance() {
		if(instance == null) 
			instance = new DateController();
		
		return instance;
	}
	
	private DateController() {
		timeKeeper = ScheduleNavigationBar.getInstance();
	}
	
	public void linkDateToSeanceWidget(SeanceWidget widget, int posH, int posV) {
		long newDate = DateUtil.computeNewDate(posH, posV);
		widget.getTeaching().removeSeanceByIndex(widget.getIndexSession());
		int index = widget.getTeaching().addSeance(newDate);
		ServerCommunication.getInstance().updateTeaching(widget.getTeaching());
		widget.setIndexSession(index);
	}
}
