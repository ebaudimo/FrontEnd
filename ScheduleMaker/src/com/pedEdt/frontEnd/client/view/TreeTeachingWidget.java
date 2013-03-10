package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Teaching;

public class TreeTeachingWidget extends Label{
	
	protected Teaching teaching;
	
	public TreeTeachingWidget(Teaching teaching){
		super(String.valueOf(teaching.getType()));
		ScheduleDragController.getInstance().makeDraggable(this);
		this.teaching = teaching;
	}
	
	public Teaching getTeaching(){
		return teaching;
	}

}
