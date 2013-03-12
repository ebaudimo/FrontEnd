package com.pedEdt.frontEnd.client.view;

import java.sql.Date;
import java.text.Format;
import java.util.Calendar;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.core.java.sql.Timestamp_CustomFieldSerializer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.pedEdt.frontEnd.client.model.Semester;

public class ScheduleSemesterInformation extends Composite {
	
	public ScheduleSemesterInformation(Semester s) {
		HorizontalPanel myPanel = new HorizontalPanel();
		
		DateTimeFormat Format = DateTimeFormat.getFormat("dd/MM/yyyy");
		
		//long longStart = new Long("1363106046000");
		long longStart = Long.parseLong(String.valueOf(s.getStartDate()).concat("000"));
		Date start = new Date(longStart);
		long longEnd = Long.parseLong(String.valueOf(1363106046*1000));
		Date end = new Date(longEnd);
		
		Label myLabel = new Label("Semestre : " + String.valueOf(s.getNumber()) + "	Annee : " + String.valueOf(s.getYear()) + "<br />" +
									"Date début : " + Format.format(start) + " Date fin : " + end.toGMTString()
				);
		myLabel.setStyleName("semesterInformation");
		myPanel.add(myLabel);
		initWidget(myPanel);
	}

}
