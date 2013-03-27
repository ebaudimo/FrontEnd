package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.pedEdt.frontEnd.client.model.Semester;

public class ScheduleTreePanel extends Composite {

	protected ScrollPanel scrollPanel;
	protected static TreeSemesterWidget semesterTree;
	
	public ScheduleTreePanel(Semester semester) {
		
		scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);
		scrollPanel.setWidth("150px");
		
		semesterTree = new TreeSemesterWidget(semester);
		semesterTree.createTree();
		scrollPanel.add(semesterTree);
		
		scrollPanel.setStylePrimaryName("scroll-area");
	}
	
	public void rebuildTree(Semester semester) {
		scrollPanel.remove(semesterTree);
		semesterTree = new TreeSemesterWidget(semester);
		semesterTree.createTree();
		scrollPanel.add(semesterTree);
	}
	
	public static TreeSemesterWidget getSemesterTree() {
		return semesterTree;
	}
}
