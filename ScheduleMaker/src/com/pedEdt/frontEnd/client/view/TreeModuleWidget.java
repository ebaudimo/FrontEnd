package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Teaching;

public class TreeModuleWidget extends TreeItem{
	
	protected Module module;
	
	@SuppressWarnings("deprecation")
	public TreeModuleWidget(Module module){
		super(module.getTitle()+" ("+module.getCode()+")");
		this.module = module;
		
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
		for (Teaching teaching : module.getTeachings()) {
			TreeTeachingWidget widget = new TreeTeachingWidget(teaching);
			addItem(widget);	
		}
	}

}
