package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.controller.SeanceWidgetButtonHandler;
import com.pedEdt.frontEnd.client.model.Teaching;

public class SeanceWidget extends Composite {

	protected VerticalPanel mainPanel;
	protected HorizontalPanel headerPanel;
	protected Button headerCloseButton;
	protected Label headerLabel;
	protected VerticalPanel bodyPanel;
	
	protected Teaching teaching;
	protected int indexSession; // index of the session represented by this widget in the teaching tab : 'seances'
	protected int length; // nbr of blocks in the grid
	protected int posH; // [0-4] horizontal position on the grid
	protected int posV; // [0-61] vertical position on the grid
	
	protected boolean toRemove;
	
	public SeanceWidget(Teaching teaching){
		this(teaching,0,0);
	}
	
	public SeanceWidget(final Teaching teaching, int posH, int posV) {
		length = -1;
		toRemove = false;
		this.teaching = teaching;
		this.posH = posH;
		this.posV = posV;

		mainPanel = new VerticalPanel();
		
		headerPanel = new HorizontalPanel();
		headerPanel.setHeight("15px");
		headerPanel.setWidth("100%");
		headerPanel.setStyleName("teaching-cell-header");
		headerPanel.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		//TODO
		headerLabel = new Label("title");
		headerLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
		headerLabel.setStyleName("teaching-cell-header");
		
		headerCloseButton = new Button("x", new SeanceWidgetButtonHandler(this));
		headerCloseButton.setHeight("15px");
		headerCloseButton.setStyleName("teaching-cell-header");
		headerPanel.add(headerLabel);
		headerPanel.add(headerCloseButton);
		
		bodyPanel = new VerticalPanel();
		bodyPanel.setWidth("100%");
		bodyPanel.setStyleName("teaching-cell-body");
		//TODO
		Label line = new Label("title");
		line.setStyleName("teaching-cell-body");
		bodyPanel.add(line);
		line = new Label(String.valueOf(teaching.getType()));
		line.setStyleName("teaching-cell-body");
		bodyPanel.add(line);
		line = new Label(teaching.getTeacher());
		line.setStyleName("teaching-cell-body");
		bodyPanel.add(line);
		line = new Label("n� seance");
		line.setStyleName("teaching-cell-body");
		bodyPanel.add(line);
		
		mainPanel.add(headerPanel);
		mainPanel.add(bodyPanel);
		
		initWidget(mainPanel);
		setStyleName("teaching-cell");
		ScheduleDragController.getInstance().makeDraggable(this, headerLabel);

	}
	
	public void setPosH(int newPosH) {
		posH = newPosH;
	}
	
	public void setPosV(int newPosV) {
		posV = newPosV;
	}
	
	public int getPosH() {
		return posH;
	}
	
	public int getPosV() {
		return posV;
	}

	public int getLength() {
		return length;
	}
	
	public void setLength(int l) {
		this.length = l;
	}
	
	public Teaching getTeaching() {
		return teaching;
	}
	
	public void setTeaching(Teaching newTeaching) {
		this.teaching = newTeaching;
	}
	
	public void setRemoved(){
		toRemove = true;
	}
	
	public boolean isRemoved(){
		return toRemove;
	}

	public void setIndexSession(int index) {
		this.indexSession = index;
	}
	
	public int getIndexSession() {
		return this.indexSession;
	}
}
