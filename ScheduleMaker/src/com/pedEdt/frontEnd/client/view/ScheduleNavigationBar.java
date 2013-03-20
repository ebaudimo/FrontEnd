package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class ScheduleNavigationBar extends Composite {
	
	private long start;
	private long end;
	private int currentValue;
	private long nbWeek;
	private Label myLabel;
	
	
	private static ScheduleNavigationBar navBar;
	public static ScheduleNavigationBar getInstance(int numWeek, long startSemester, long endSemester) {
		if(navBar == null)
			navBar = new ScheduleNavigationBar(numWeek, startSemester*1000, endSemester*1000);
		
		return navBar;
	}
	
	public static ScheduleNavigationBar getInstance() {
		return navBar;
	}
	
	private ScheduleNavigationBar(int numWeek, long startSemester, long endSemester) {
		
		this.start = startSemester;
		this.end = endSemester;
		
		nbWeek = (endSemester - startSemester) / DateUtil.WEEK;
		
		if(numWeek > 0 && numWeek <= nbWeek)
			setCurrentValue(numWeek);
		else
			setCurrentValue(1);
		
		HorizontalPanel navPanel = new HorizontalPanel();
		
		navPanel.add(new Button("Precedent", new ClickHandler() {

			public void onClick(ClickEvent event) {
				if(currentValue > 1) {
					currentValue --;
					myLabel.setText("Semaine " + String.valueOf(currentValue));
				}
				//else : do nothing
			}
		}));
		
		myLabel = new Label("Semaine " + String.valueOf(currentValue));
		navPanel.add(myLabel);
		
		navPanel.add(new Button("Suivant", new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if(currentValue < nbWeek) {
					currentValue ++;
					myLabel.setText("Semaine " + String.valueOf(currentValue));
				}
				//else : do nothing
			}
		}));
		
		initWidget(navPanel);
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
	
	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}	
}
