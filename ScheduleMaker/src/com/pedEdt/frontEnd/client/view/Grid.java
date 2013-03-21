package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class Grid extends Composite {
	
	protected AbsolutePanel grid;
   
    private final int days = 5;
    private int nbIntervals = 0;
    private final int intervalSize = 10; //px
    
    private final float GRID_WIDTH = 100;
    private final String WIDTH_TYPE = "%";

	public Grid() {
		grid = new AbsolutePanel();
	    initWidget(grid);
	    initialize();
	}
	
	public void initialize(){
		grid.clear();
        float dayWidth = GRID_WIDTH / days;
        float dayLeft = 0f;
        // 8h-9h20
        addSeanceCell();
        // Pause
        addPauseCell();
        //9h30-10h50
        addSeanceCell();
        //Pause
        addPauseCell();
        //11h-12h20
        addSeanceCell();
        //Lunch break
        addLunchBreak();
        //13h50-15h10
        addSeanceCell();
        //Pause
        addPauseCell();
        //15h20-16h40
        addSeanceCell();
        //Pause
        addPauseCell();
        //16h50-18h10
        addSeanceCell();

        for (int day = 0; day < days; day++) {
            dayLeft = dayWidth * day;
            SimplePanel dayPanel = new SimplePanel();
            dayPanel.setStyleName("day-separator");
            grid.add(dayPanel);
            DOM.setStyleAttribute(dayPanel.getElement(), "left", dayLeft + WIDTH_TYPE);
        }
	}

	public AbsolutePanel getDroppableArea() {
		return grid;
	}
	
	public int getNbDays(){
		return days;
	}
	
	public int getNbIntervals(){
		return nbIntervals;
	}
	
	// Private methods
	private void addSeanceCell(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
        nbIntervals++;
        for(int i=0; i<7;i++){
        	SimplePanel sp2 = new SimplePanel();
            sp2.setStyleName("minor-time-interval");
            sp2.setHeight(intervalSize + "px");
            grid.add(sp2);
            nbIntervals++;
        }
	}
	
	private void addPauseCell(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval-pause");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
        nbIntervals++;
	}
	
	private void addLunchBreak(){
		SimplePanel sp1 = new SimplePanel();
        sp1.setStyleName("major-time-interval-pause");
        sp1.setHeight(intervalSize + "px");
        grid.add(sp1);
        nbIntervals++;
        for(int i=0; i<8;i++){
        	SimplePanel sp2 = new SimplePanel();
            sp2.setStyleName("minor-time-interval-pause");
            sp2.setHeight(intervalSize + "px");
            grid.add(sp2);
            nbIntervals++;
        }
	}
		
}
