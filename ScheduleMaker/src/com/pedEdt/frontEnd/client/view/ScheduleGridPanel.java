package com.pedEdt.frontEnd.client.view;

import com.allen_sauer.gwt.dnd.client.drop.GridDropController;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class ScheduleGridPanel extends Composite {
	
	protected FlexTable mainPan;
	protected GridDropController dropController;
	protected GridDaysHeader gridDaysHdr;
	public Grid schedGrid;
	public GridTimeline schedTimeline;

	public ScheduleGridPanel() {
		mainPan = new FlexTable();
		mainPan.setBorderWidth(1);
		mainPan.setCellSpacing(0);
		mainPan.setCellPadding(0);
		schedGrid = new Grid();
		schedTimeline = new GridTimeline();
		dropController = new GridDropController(schedGrid.getDroppableArea());
		gridDaysHdr = new GridDaysHeader();
		initWidget(mainPan);
		mainPan.setWidget(0, 1, gridDaysHdr);
		mainPan.setWidget(1, 1, schedGrid);
		mainPan.setWidget(1, 0, schedTimeline);
//		mainPan.getFlexCellFormatter().setWidth(1, 0, "50px");
		mainPan.getFlexCellFormatter().setHeight(1, 1, schedGrid.getOffsetHeight()+"px");
		mainPan.getFlexCellFormatter().setWidth(1, 1, "800px");
		//mainPan.getFlexCellFormatter().setRowSpan(1, 1, 11);
		
//		dropController.setGridX(800/5); // TODO: use variables
//		dropController.setGridY(10);
	}
	
	public GridDropController getDropController(){
		return dropController;
	}

}

