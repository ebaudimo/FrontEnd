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

		MenuBar semesterMenu = new MenuBar(true);
		semesterMenu.addItem("Nouveau", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->New callback"));
			}
		});
		semesterMenu.addItem("Ouvrir", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->Open callback"));
			}
		});
		semesterMenu.addItem("Sauvegarder", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->Save callback"));
			}
		});
		semesterMenu.addItem("Fermer", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.add(new Label("Semester->Close callback"));
			}
		});
		menuBar.addItem("Semestre", semesterMenu);
		
		//debug
		MenuBar debugMenu = new MenuBar(true);
		debugMenu.addItem("Afficher/Cacher", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().setVisible(!DebugPanel.getInstance().isVisible());
			}
		});
		debugMenu.addItem("Clear", new ScheduledCommand() {
			
			@Override
			public void execute() {
				DebugPanel.getInstance().vpan.clear();	
			}
		});
		menuBar.addItem("Debug", debugMenu);
		//end debug
		
		initWidget(menuBar);
	}

}
