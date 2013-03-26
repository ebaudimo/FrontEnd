package com.pedEdt.frontEnd.client.controller;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.pedEdt.frontEnd.client.view.MainGUI;
import com.pedEdt.frontEnd.client.view.SeanceWidget;

public class SeanceWidgetButtonHandler implements ClickHandler {

	private SeanceWidget widget;
	
	public SeanceWidgetButtonHandler(SeanceWidget widget){
		this.widget = widget;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		//widget.getTeaching().removeSeance(widget.getBeginning());
		new DateController().unlinkTheSeanceWidget(widget);
		
		widget.setRemoved();
		widget.removeFromParent();
		
		MainGUI.getInstance().reloadTree(); //we need to rebuild all the tree to actualize the tooltip
		
	}
}
