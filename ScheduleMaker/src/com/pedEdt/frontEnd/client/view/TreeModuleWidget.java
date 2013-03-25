package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Teaching;

public class TreeModuleWidget extends TreeItem{
	
	protected Module module;
	
	public TreeModuleWidget(final Module module){
//		super(module.getTitle()+" ("+module.getCode()+")");
		this.module = module;
		Label label = new Label(module.getTitle() + " ("+module.getCode() + ")");
		label.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ScheduleMenuBar.updateToModuleOptions(module);
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
	
	public Module getModule(){
		return module;
	}
	
	public void createTree(){
		if(module.getTeachings() != null) {
			for (Teaching teaching : module.getTeachings()) {
				TreeTeachingWidget widget = new TreeTeachingWidget(teaching);
				addItem(widget);	
			}
		}
	}

}
