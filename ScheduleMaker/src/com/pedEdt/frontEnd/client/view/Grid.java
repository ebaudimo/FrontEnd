package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class Grid extends Composite {
	
	protected AbsolutePanel grid;
   
    private final int days = 5;
    private final int HOURS_PER_DAY = 11;
    private final int intervalsPerHour = 6; // 10 minutes
    private final int intervalSize = 10; //px
    
    private final float GRID_WIDTH = 100;
    private final String WIDTH_TYPE = "%";
    private final int GRID_HEIGHT = intervalsPerHour*intervalSize*HOURS_PER_DAY;

	public Grid() {
		grid = new AbsolutePanel();
	    initWidget(grid);
	    
	    grid.clear();

        //this.setHeight(GRID_HEIGHT + "px");

        float dayWidth = GRID_WIDTH / days;
        float dayLeft = 0f;
        
        addSeanceCell();
        addPauseCell();
        addSeanceCell();
        addPauseCell();
        addSeanceCell();
        addLunchBreak();
        addSeanceCell();
        addPauseCell();
        addSeanceCell();
        addPauseCell();
        addSeanceCell();

        for (int day = 0; day < days; day++) {
            dayLeft = dayWidth * day;
            SimplePanel dayPanel = new SimplePanel();
            dayPanel.setStyleName("day-separator");
            grid.add(dayPanel);
            DOM.setStyleAttribute(dayPanel.getElement(), "left", dayLeft + WIDTH_TYPE);
        }
	}
	
	private void addSeanceCell(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
        
        for(int i=0; i<7;i++){
        	SimplePanel sp2 = new SimplePanel();
            sp2.setStyleName("minor-time-interval");
            sp2.setHeight(intervalSize + "px");
            grid.add(sp2);
        }
	}
	
	private void addPauseCell(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval-pause");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
	}
	
	private void addLunchBreak(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval-pause");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
        
        for(int i=0; i<8;i++){
        	SimplePanel sp2 = new SimplePanel();
            sp2.setStyleName("minor-time-interval-pause");
            sp2.setHeight(intervalSize + "px");
            grid.add(sp2);
        }
	}

	public AbsolutePanel getDroppableArea() {
		return grid;
	}
	
	public int getNbDays(){
		return days;
	}
	
	public int getNbIntervals(){
		return intervalsPerHour * HOURS_PER_DAY;
	}
		
}
