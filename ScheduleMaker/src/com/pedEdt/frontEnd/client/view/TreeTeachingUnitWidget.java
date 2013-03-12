package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class TreeTeachingUnitWidget extends TreeItem{
	
	protected TeachingUnit teachingUnit;
	
	@SuppressWarnings("deprecation")
	public TreeTeachingUnitWidget(TeachingUnit teachingUnit){
		super(teachingUnit.getTitle()+" ("+teachingUnit.getCode()+")");
		this.teachingUnit = teachingUnit;
		
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
				TreeModuleWidget widget = new TreeModuleWidget(module);
				widget.createTree();
				addItem(widget);	
			}
		}
	}
}
