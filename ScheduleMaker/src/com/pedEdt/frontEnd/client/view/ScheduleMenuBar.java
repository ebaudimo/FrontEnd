package com.pedEdt.frontEnd.client.view;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.pedEdt.frontEnd.client.util.DebugPanel;

public class ScheduleMenuBar extends Composite {

	protected MenuBar menuBar;
	public ScheduleMenuBar() {
		menuBar = new MenuBar();
		menuBar.addItem("test1", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("test1 handler"));	
			}
		});
		
		menuBar.addItem("test2", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("test2 handler"));	
			}
		});
		initWidget(menuBar);
	}

}
