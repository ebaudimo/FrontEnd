package com.pedEdt.frontEnd.client.util;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DebugPanel extends Composite {

	public VerticalPanel vpan = null;
	
	private static DebugPanel instance = null;
	
	private DebugPanel() {
		vpan = new VerticalPanel();
		initWidget(vpan);
		vpan.setWidth("200px");
		vpan.add(new Label("DEBUG PANEL"));
	}
	
	public static DebugPanel getInstance(){
		if(instance == null)
			instance = new DebugPanel();
		return instance;
			
	}

}
