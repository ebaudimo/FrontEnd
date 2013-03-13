package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class ScheduleNavigationBar extends Composite {

	static int WEEK = 604800;
	
	private int currentValue;
	private int nbWeek;
	private Label myLabel;
	
	public ScheduleNavigationBar(int numWeek, int startSemester, int endSemester) {
		
		nbWeek = (endSemester-startSemester)/WEEK;
		
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
	
}
