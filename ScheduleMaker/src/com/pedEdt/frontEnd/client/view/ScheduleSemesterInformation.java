package com.pedEdt.frontEnd.client.view;

import java.util.Date;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class ScheduleSemesterInformation extends Composite {
	
	public ScheduleSemesterInformation(Semester s) {
		HorizontalPanel myPanel = new HorizontalPanel();
		
		DateTimeFormat Format = DateTimeFormat.getFormat("dd/MM/yyyy");
		
//		Date start = DateUtil.getDate(s.getStartDate());
//		Date end = DateUtil.getDate(s.getEndDate());
		Date start = s.getStartDate();
		Date end = s.getEndDate();
		
		Label myLabel = new Label("   Semestre : " + String.valueOf(s.getNumber()) + "	Annee : " + String.valueOf(s.getYear()) +
									"      Date debut : " + start.toGMTString() + " Date fin : " + end.toLocaleString()
				);
		myLabel.setStyleName("semesterInformation");
		myPanel.add(myLabel);
		initWidget(myPanel);
	}

}
