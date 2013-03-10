package com.pedEdt.frontEnd.client.controller;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class ScheduleDragController extends PickupDragController{

	private static ScheduleDragController instance = null;
	
	private ScheduleDragController(AbsolutePanel boundaryPanel,
			boolean allowDroppingOnBoundaryPanel) {
		super(boundaryPanel, allowDroppingOnBoundaryPanel);
	}
	
	public static boolean isSet(){
		return instance != null;
	}
	
	public static void createInstance(AbsolutePanel boundaryPanel,
			boolean allowDroppingOnBoundaryPanel) {
		instance = new ScheduleDragController(boundaryPanel,allowDroppingOnBoundaryPanel);
	}
	
	public static ScheduleDragController getInstance(){			
		return instance;
	}

}
