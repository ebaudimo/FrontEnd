package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

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
            switch(i){
            	case 0:
            		title = "Lundi";
            		break;
            	case 1:
            		title = "mardi";
            		break;
            	case 2:
            		title = "mercredi";
            		break;
            	case 3:
            		title = "jeudi";
            		break;
            	case 4:
            		title = "vendredi";
            		break;
            	default:
            		title = "ERROR";
            		break;
            }

            Label dayLabel = new Label();
            dayLabel.setStylePrimaryName("day-cell");
            dayLabel.setWidth(dayWidth + "%");
            dayLabel.setText(title);
            DOM.setStyleAttribute(dayLabel.getElement(), "left", dayLeft + "%");

            dayPanel.add(dayLabel);
        }
	}

}
