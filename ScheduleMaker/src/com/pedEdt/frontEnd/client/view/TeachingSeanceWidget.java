package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Teaching;

public class TeachingSeanceWidget extends Composite{

	protected VerticalPanel mainPanel;
	protected Label headerPanel;
	protected Label label;
	
	protected Teaching teaching;
	protected int length; // nbr of blocks in the grid
	protected int posH; // [0-4] horizontal position on the grid
	protected int posV; // [0-61] vertical position on the grid
	
	public TeachingSeanceWidget(final Teaching teaching, int posH, int posV){
		this.teaching = teaching;
		this.posH = posH;
		this.posV = posV;
		
		mainPanel = new VerticalPanel();
		headerPanel = new Label(teaching.getModule().getTitle()+"-"+teaching.getType());
		headerPanel.setHeight("15px");
		headerPanel.setWidth("100%");
		headerPanel.setStyleName("teaching-cell-header");
		headerPanel.setHorizontalAlignment(Label.ALIGN_CENTER);
		
		label = new Label("infos complémentaires");
		label.setWidth("100%");
		label.setHorizontalAlignment(Label.ALIGN_CENTER);
		
		mainPanel.add(headerPanel);
		mainPanel.add(label);
		
		initWidget(mainPanel);
		setStyleName("teaching-cell");
		
		ScheduleDragController.getInstance().makeDraggable(this, headerPanel);
		
	}
	
	public void setPosH(int newPosH){
		posH = newPosH;
	}
	
	public void setPosV(int newPosV){
		posV = newPosV;
	}
	
	public int getPosH(){
		return posH;
	}
	
	public int getPosV(){
		return posV;
	}

	public int getLength(){
		return length;
	}
}
