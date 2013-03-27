package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class GridTimeline extends Composite {

	private AbsolutePanel timelinePanel;
	
	public GridTimeline(){
		timelinePanel = new AbsolutePanel();
		timelinePanel.setWidth("50px");
		initWidget(timelinePanel);

		addSeanceLabel("8h - 9h20");
		addPauseLabel();
		addSeanceLabel("9h30 - 10h50");
		addPauseLabel();
		addSeanceLabel("11h - 12h20");
		addLunchLabel();
		addSeanceLabel("13h50 - 15h10");
		addPauseLabel();
		addSeanceLabel("15h20 - 16h40");
		addPauseLabel();
		addSeanceLabel("16h50 - 18h10");
		
	}
	
	private void addSeanceLabel(String text){
		Label l = new Label(text);
		l.setWidth("50px");
		l.setHeight("87px");
		l.setStyleName("timeline-label");
		timelinePanel.add(l);
	}
	
	private void addPauseLabel(){
		Label l = new Label("");
		l.setWidth("50px");
		l.setHeight("10px");
		l.setStyleName("timeline-label-pause");
		timelinePanel.add(l);
	}
	
	private void addLunchLabel(){
		Label l = new Label("Pause");
		l.setWidth("50px");
		l.setHeight("98px");
		l.setStyleName("timeline-label-pause");
		timelinePanel.add(l);
	}
}
