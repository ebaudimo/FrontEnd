package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Teaching;

//for the tooltip events
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

public class TreeTeachingWidget extends Label{
	
	protected Teaching teaching;
	
	public TreeTeachingWidget(Teaching teaching){
		super(teaching.getTeacher() + " " + String.valueOf(teaching.getType()));
		ScheduleDragController.getInstance().makeDraggable(this);
		this.teaching = teaching;
		
		
		final TooltipListener tooltip = new TooltipListener(String.valueOf(teaching.getNbSeance()) + " seances programmees.", 5000);
		final Widget sender = this;
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
		
		
		final ContextMenu contextMenu = new ContextMenu(this);
		this.addDomHandler(new ContextMenuHandler() {
			public void onContextMenu(ContextMenuEvent event) {
				event.preventDefault();
				contextMenu.showMenu();
			}
		}, ContextMenuEvent.getType());
		
	}
	
	public Teaching getTeaching(){
		return teaching;
	}

}
