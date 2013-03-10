package com.allen_sauer.gwt.dnd.client.drop;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.pedEdt.frontEnd.client.util.DebugPanel;
import com.pedEdt.frontEnd.client.view.TeachingSeanceWidget;
import com.pedEdt.frontEnd.client.view.TreeTeachingWidget;

public class GridDropController extends AbsolutePositionDropController{
	
	private int gridX;
	private int gridY;
	
	
	public GridDropController(AbsolutePanel dropTarget) {
		super(dropTarget);
		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / 5);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / 62);
	}
	
	public void onDrop(final DragContext context){
		//super.onDrop(context);

		//get the top and left position and the widget
		int top =draggableList.get(0).desiredY;
		int left=draggableList.get(0).desiredX;
		Widget widget = context.draggable;
		
		int posH = Math.round((float) left / gridX);
		int posV = Math.round((float) top / gridY);
		left = posH * gridX;
		left = Math.max(0,left);
		top = posV * gridY;
		top = Math.max(0,top);
		
		// border correction
		top += top/10;
		left += 1;
		
		//debug
		DebugPanel.getInstance().vpan.clear();
		DebugPanel.getInstance().vpan.add(new Label("posH = "+posH));
		DebugPanel.getInstance().vpan.add(new Label("posV = "+posV));
		//end debug
		
		if( widget instanceof TreeTeachingWidget){
			TeachingSeanceWidget l = new TeachingSeanceWidget(((TreeTeachingWidget) widget).getTeaching(),posH,posV);
			l.setHeight((gridY*8+8)+"px");
			l.setWidth((gridX-1)+"px");
			dropTarget.add(l, left, top);
			draggableList.get(0).positioner.removeFromParent();
		}
		else if( widget instanceof TeachingSeanceWidget){
			((TeachingSeanceWidget) widget).setPosH(posH);
			((TeachingSeanceWidget) widget).setPosV(posV);
			dropTarget.add(widget, left, top);
			draggableList.get(0).positioner.removeFromParent();
		}	
	}
	
	@Override
	public void onMove(final DragContext context) {
		super.onMove(context);

		gridX = (int) Math.floor(dropTarget.getOffsetWidth() / 5);
		gridY = (int) Math.floor(dropTarget.getOffsetHeight() / 62);


		for (Draggable draggable : draggableList) {
			
			draggable.desiredX = context.desiredDraggableX - dropTargetOffsetX + draggable.relativeX;
			draggable.desiredY = context.desiredDraggableY - dropTargetOffsetY + draggable.relativeY;
			
			//debug
			DebugPanel.getInstance().vpan.clear();
			DebugPanel.getInstance().vpan.add(new Label("desiredX = "+draggable.desiredX));
			DebugPanel.getInstance().vpan.add(new Label("desiredY = "+draggable.desiredY));
			//end debug
			
			//border correction
			draggable.desiredY -= draggable.desiredY/10;

			draggable.desiredX = (int)Math.floor((double) draggable.desiredX / gridX) * gridX;
			draggable.desiredX = Math.max(0, draggable.desiredX);
			draggable.desiredY = (int)Math.round((double) draggable.desiredY / gridY) * gridY;
			draggable.desiredY = Math.max(0, draggable.desiredY);
			
			//debug
			DebugPanel.getInstance().vpan.add(new Label("left = "+draggable.desiredX));
			DebugPanel.getInstance().vpan.add(new Label("top = "+draggable.desiredY));
			//end debug

			int postionerY = draggable.desiredY + (draggable.desiredY/10);
			
			draggable.positioner.setWidth((gridX-1)+"px");
			draggable.positioner.setHeight((gridY*8+8)+"px");
			dropTarget.add(draggable.positioner, draggable.desiredX, postionerY);
		}
	}
	
}