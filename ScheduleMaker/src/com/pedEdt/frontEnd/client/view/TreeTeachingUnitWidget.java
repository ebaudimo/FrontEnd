package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class TreeTeachingUnitWidget extends TreeItem{
	
	protected TeachingUnit teachingUnit;
	
	@SuppressWarnings("deprecation")
	public TreeTeachingUnitWidget(TeachingUnit teachingUnit){
		super(teachingUnit.getTitle()+" ("+teachingUnit.getCode()+")");
		this.teachingUnit = teachingUnit;
	}
	
	public TeachingUnit getTeachingUnit(){
		return teachingUnit;
	}

	public void createTree(){
		for (Module module : teachingUnit.getModules()) {
			TreeModuleWidget widget = new TreeModuleWidget(module);
			widget.createTree();
			addItem(widget);	
		}
	}
}
