package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.util.ColorUtil;
//for the tooltip events

public class TreeTeachingWidget extends Label{
	
	protected Teaching teaching;
	protected TreeModuleWidget father;
	protected TooltipListener tooltip;
	
	public TreeTeachingWidget(final Teaching teaching,  TreeModuleWidget father) {
		super(String.valueOf(teaching.getType())+" G"+teaching.getNumGroup());
		ScheduleDragController.getInstance().makeDraggable(this);
		ScheduleDragController.getInstance().setBehaviorDragStartSensitivity(1);
		this.teaching = teaching;
		this.father = father;
		ColorUtil.getInstance().affectSameColor(father.getModule(), teaching);
		
		addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ScheduleMenuBar.updateToTeachingOptions(teaching);
			}
		});
		
		final Widget sender = this;
		tooltip = new TooltipListener(
				"Teacher : " + teaching.getTeacher() + "<br/>" +
				"Groupe : " + teaching.getNumGroup() + "<br/>" +
				"Reste " + String.valueOf(teaching.getNbSeance() - teaching.getSeances().size()) + 
				" seances sur " + String.valueOf(teaching.getNbSeance()),
				5000);
		
		
		tooltip.setStyleName("tooltip");
		this.addMouseOverHandler(new MouseOverHandler() {			
			public void onMouseOver(MouseOverEvent event) {
				tooltip.onMouseEnter(sender);
			}
		});
		this.addMouseOutHandler(new MouseOutHandler() {
			public void onMouseOut(MouseOutEvent event) {
				tooltip.onMouseLeave(sender);
			}
		});
		
		updateTreeTeaching();
	}
	
	public Teaching getTeaching() {
		return teaching;
	}
	
	public TreeModuleWidget getFather() {
		return father;
	}

	public void updateTreeTeaching() {
		if(this.teaching.getSeances().size() == this.teaching.getNbSeance()) { //all session is affected
			ScheduleDragController.getInstance().makeNotDraggable(this);
			this.setStyleName("teachingNotEnable");
		}
		else {
			ScheduleDragController.getInstance().makeDraggable(this);
			this.removeStyleName("teachingNotEnable");
		}
		updateTooltip();
	}
	
	public void updateTooltip() {
		tooltip.setText("Teacher : " + teaching.getTeacher() + "<br/>" +
		"Groupe : " + teaching.getNumGroup() + "<br/>" +
		"Reste " + String.valueOf(teaching.getNbSeance() - teaching.getSeances().size()) + 
		" seances sur " + String.valueOf(teaching.getNbSeance()));	
	}

	public int getLengthOnGrid(){
		return teaching.getNbHour()/(10*teaching.getNbSeance());
	}
}

