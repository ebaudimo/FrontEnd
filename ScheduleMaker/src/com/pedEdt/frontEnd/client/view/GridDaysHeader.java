package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class GridDaysHeader extends Composite {

	private final int DAYS_PER_WEEK = 5;
	public AbsolutePanel dayPanel;
	
	public GridDaysHeader() {
		dayPanel = new AbsolutePanel();
		initWidget(dayPanel);
		dayPanel.setStyleName("day-cell-container");
		
		float dayWidth = 100f / DAYS_PER_WEEK;
        float dayLeft;

        for (int i = 0; i < DAYS_PER_WEEK; i++) {

            // set the left position of the day splitter to
            // the width * incremented value
            dayLeft = dayWidth * i;

            String title;
            String date;
            switch(i){
            	case 0:
            		title = "Lundi";
            		date = DateUtil.buildWeekHeader(1);
            		break;
            	case 1:
            		title = "Mardi";
            		date = DateUtil.buildWeekHeader(2);
            		break;
            	case 2:
            		title = "Mercredi";
            		date = DateUtil.buildWeekHeader(3);
            		break;
            	case 3: 
            		title = "Jeudi";
            		date = DateUtil.buildWeekHeader(4);
            		break;
            	case 4: 
            		title = "Vendredi";
            		date = DateUtil.buildWeekHeader(5);
            		break;
            	default:
            		title = "ERROR";
            		date = "";
            		break;
            }
            
            Label dayLabel = new Label();
            dayLabel.setStylePrimaryName("day-cell");
            dayLabel.setWidth(dayWidth + "%");
            dayLabel.setText(title + " " + date);
            DOM.setStyleAttribute(dayLabel.getElement(), "left", dayLeft + "%");

            dayPanel.add(dayLabel);
        }
	}

	public void updateGridDaysHeader() {
		dayPanel.clear();
		
		float dayWidth = 100f / DAYS_PER_WEEK;
        float dayLeft;

        for (int i = 0; i < DAYS_PER_WEEK; i++) {

            // set the left position of the day splitter to
            // the width * incremented value
            dayLeft = dayWidth * i;

            String title;
            String date;
            switch(i){
            	case 0:
            		title = "Lundi";
            		date = DateUtil.buildWeekHeader(1);
            		break;
            	case 1:
            		title = "Mardi";
            		date = DateUtil.buildWeekHeader(2);
            		break;
            	case 2:
            		title = "Mercredi";
            		date = DateUtil.buildWeekHeader(3);
            		break;
            	case 3: 
            		title = "Jeudi";
            		date = DateUtil.buildWeekHeader(4);
            		break;
            	case 4: 
            		title = "Vendredi";
            		date = DateUtil.buildWeekHeader(5);
            		break;
            	default:
            		title = "ERROR";
            		date = "";
            		break;
            }
            
            Label dayLabel = new Label();
            dayLabel.setStylePrimaryName("day-cell");
            dayLabel.setWidth(dayWidth + "%");
            dayLabel.setText(title + " " + date);
            DOM.setStyleAttribute(dayLabel.getElement(), "left", dayLeft + "%");

            dayPanel.add(dayLabel);
        }
	}
}
