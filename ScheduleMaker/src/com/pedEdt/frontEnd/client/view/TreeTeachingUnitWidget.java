package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.TeachingUnit;
import com.pedEdt.frontEnd.client.util.ColorUtil;

public class TreeTeachingUnitWidget extends TreeItem{
	
	protected TeachingUnit teachingUnit;
	
	public TreeTeachingUnitWidget(final TeachingUnit teachingUnit){
		//super(this.teachingUnit.getTitle() + " (" + this.teachingUnit.getCode() + ")");
		this.teachingUnit = teachingUnit;
		
		ColorUtil.getInstance().setColorType(teachingUnit);
		
		Label label = new Label(this.teachingUnit.getTitle() + " (" + this.teachingUnit.getCode() + ")");
		label.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ScheduleMenuBar.updateToTeachingUnitOptions(teachingUnit);
			}
		});
		setWidget(label);
		
		/*
		final ContextMenu contextMenu = new ContextMenu(this);
		this.addDomHandler(new ContextMenuHandler() {
			public void onContextMenu(ContextMenuEvent event) {
				event.preventDefault();
				contextMenu.showMenu();
			}
		}, ContextMenuEvent.getType());
		*/
	}
	
	public TeachingUnit getTeachingUnit(){
		return teachingUnit;
	}

	public void createTree() {
		if(teachingUnit.getModules() != null) {
			for (Module module : teachingUnit.getModules()) {
				TreeModuleWidget widget = new TreeModuleWidget(module, this);
				widget.createTree();
				addItem(widget);	
			}
		}
	}
}
