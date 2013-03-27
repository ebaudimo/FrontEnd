package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.model.Semester;

public class ScheduleSemesterInformation extends Composite {
	
	public ScheduleSemesterInformation(Semester s) {
		HorizontalPanel myPanel = new HorizontalPanel();
		
		Label myLabel = new Label("Annee " + String.valueOf(s.getYear()) + ", Semestre " + String.valueOf(s.getNumber()));
		myLabel.setStyleName("semesterInformation");
		myPanel.add(myLabel);
		initWidget(myPanel);
	}

}
