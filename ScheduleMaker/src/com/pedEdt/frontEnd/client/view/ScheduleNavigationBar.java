package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class ScheduleNavigationBar extends Composite {

	public ScheduleNavigationBar() {
		
		HorizontalPanel navPanel = new HorizontalPanel();
		
		navPanel.add(new Button("Precedent", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		navPanel.add(new Label("Semaine XX"));
		
		navPanel.add(new Button("Suivant", new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		}));
		
		initWidget(navPanel);
	}
	
}
