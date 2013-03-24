package com.allen_sauer.gwt.dnd.client.drop;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.pedEdt.frontEnd.client.controller.DateController;
import com.pedEdt.frontEnd.client.view.Grid;
import com.pedEdt.frontEnd.client.view.SeanceWidget;
import com.pedEdt.frontEnd.client.view.SeanceWidgetsManager;
import com.pedEdt.frontEnd.client.view.TreeTeachingWidget;

public class GridDropController extends AbsolutePositionDropController{
	
	private int gridX;
	private int gridY;
	private int days;
	private int intervals;
	private SeanceWidgetsManager swManager;
	
	public GridDropController(Grid grid) {
		super(grid.getDroppableArea());
		
		days = grid.getNbDays();
		intervals = grid.getNbIntervals()+1; //TODO: why +1 necessary to fit the grid?
		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / days);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / intervals);
		swManager = new SeanceWidgetsManager(grid);
	}
	
	private int applyVerticalAttraction(int posV, int length){
		if( length == 8){
			if( posV < 9)
				return 0;
			if( posV < 18)
				return 9;
			if( posV < 35)
				return 18;
			if( posV < 44)
				return 35;
			if( posV < 53)
				return 44;
			return 53;
		}
		posV = Math.min(posV, intervals - 1 - length);
		return posV;
	}
	
	public void onDrop(final DragContext context){

		//get the top and left position and the widget
		int top =draggableList.get(0).desiredY;
		int left=draggableList.get(0).desiredX;
		SeanceWidget widget = null;
		
		if( context.draggable instanceof TreeTeachingWidget){
			SeanceWidget tw = new SeanceWidget(((TreeTeachingWidget) context.draggable).getTeaching());
			int lenght = ((TreeTeachingWidget)context.draggable).getLengthOnGrid();
			tw.setLength(lenght);
			tw.setHeight((gridY*lenght+lenght)+"px");
			tw.setWidth((gridX-1)+"px");
			widget = tw;
		}
		else if( context.draggable instanceof SeanceWidget){
			widget = (SeanceWidget) context.draggable;
		}	
		
		int posH = Math.round((float) left / gridX);
		int posV = Math.round((float) top / gridY);
		posV = applyVerticalAttraction(posV, widget.getLength());

		widget.setPosV(posV);
		widget.setPosH(posH);
		
		//TODO: need to find a way to update the semester object (update the teaching of the semester object)
		new DateController().linkDateToSeanceWidget(widget, posH, posV);
		if(context.draggable instanceof TreeTeachingWidget) {
			((TreeTeachingWidget) context.draggable).updateTreeTeaching(widget.getTeaching());
		}
		
		swManager.addSeance(widget);
		swManager.doPositionning();
		draggableList.get(0).positioner.removeFromParent();	
	}
	
	@Override
	public void onMove(final DragContext context) {
		super.onMove(context);

		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / days); 
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / intervals);
		

		for (Draggable draggable : draggableList) {
			draggable.desiredX = context.desiredDraggableX - dropTargetOffsetX + draggable.relativeX;
			draggable.desiredY = context.desiredDraggableY - dropTargetOffsetY + draggable.relativeY;
			
			//border correction
			draggable.desiredY -= draggable.desiredY/10;

			int posH = Math.round((float) draggable.desiredX / gridX);
			int posV = Math.round((float) draggable.desiredY / gridY);
			int length = 0;
			
			if( draggable.widget instanceof TreeTeachingWidget){
				length = ((TreeTeachingWidget)draggable.widget).getLengthOnGrid();
				
			}
			else if( draggable.widget instanceof SeanceWidget){
				length = ((SeanceWidget)draggable.widget).getLength();		
			}
		
			posV = applyVerticalAttraction(posV, length);

			draggable.positioner.setWidth((gridX-1)+"px");
			draggable.positioner.setHeight((gridY*length+length)+"px");
			draggable.desiredX = posH * gridX;
			draggable.desiredX = Math.max(0, draggable.desiredX);
			draggable.desiredY = posV * gridY;
			draggable.desiredY = Math.max(0, draggable.desiredY);
			
			int postionerY = draggable.desiredY + (draggable.desiredY/10);
			dropTarget.add(draggable.positioner, draggable.desiredX, postionerY);
		}
	}	

	public void addTeachingSeanceWidget(SeanceWidget session) {
/*		//TeachingSeanceWidget l = new TeachingSeanceWidget(teaching, posH, posV);
		session.setHeight((gridY*8+8)+"px");
		session.setWidth((gridX*5)+"px");
		
		//int top =draggableList.get(0).desiredY;
		//int left=draggableList.get(0).desiredX;
		
		
		int left = session.getPosH() * gridX;
		left = Math.max(0,left);
		int top = session.getPosV() * gridY;
		top = Math.max(0,top);
		// border correction
		top += top/10;
		left += 1;
		
		dropTarget.add(session, left, top);*/
		
		//draggableList.get(0).positioner.removeFromParent();
		swManager.addSeance(session);
		swManager.doPositionning();
	}

	public void removeAllSeanceWidget() {
//		Iterator<Widget> i = dropTarget.iterator();
//		while(i.hasNext()) {
//			Widget w = i.next();
//			if(w instanceof SeanceWidget)
//				dropTarget.remove(w);
//		}
		swManager.removeAllSeances();
	}
	
	}