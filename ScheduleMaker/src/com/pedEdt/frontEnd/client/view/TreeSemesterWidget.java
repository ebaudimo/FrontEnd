package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class TreeSemesterWidget extends Tree{
	
	protected Semester semester;
	
	public TreeSemesterWidget(Semester semester){
		super();
		this.semester = semester;
		
		// code from https://code.google.com/p/google-web-toolkit/issues/detail?id=3660
		// to correct a known bug
		addSelectionHandler(new SelectionHandler<TreeItem>() {
			int comingFromSetState = 0;
			boolean prevOpenState = true;

			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem item = event.getSelectedItem();
				
				if (item.getChildCount() == 0) {
					// Do nothing
				} 
				else {
					if (comingFromSetState == 1 && prevOpenState) {
						comingFromSetState++;
					}
					if (comingFromSetState != 2) {
						comingFromSetState++;
						item.setState(!item.getState());
						prevOpenState = !item.getState();
					}
					else {
						comingFromSetState = 0;
						prevOpenState = true;
					}
				}
			}
		});		
		
	}

	public Semester getSemester(){
		return semester;
	}
	
	public void createTree(){
		if(semester.getTeachingUnits() != null) {
			for (TeachingUnit teachingUnit : semester.getTeachingUnits()) {
				TreeTeachingUnitWidget widget = new TreeTeachingUnitWidget(teachingUnit);
				widget.createTree();
				widget.setState(true);
				addItem(widget);
			}
		}
	}
	
	//return the TeachingUnit parent int the tree of the Module object
	public TeachingUnit getParentTeachingUnit(Module module){
		for(int i=0;i<getItemCount();i++){ //TeachingUnit course
			TreeItem itemTU = getItem(i);
			for(int j =0; j< itemTU.getChildCount();j++){ //Module course
				TreeItem itemM = itemTU.getChild(j);
				if(((TreeModuleWidget)itemM).module == module){
					return ((TreeTeachingUnitWidget)itemTU).getTeachingUnit();
				}
			}
		}
		return null;
	}
	
	//return the Module parent in the tree of the teaching object
	public Module getParentModule(Teaching teaching){
		for(int i=0;i<getItemCount();i++){ //TeachingUnit course
			TreeItem itemTU = getItem(i);
			for(int j =0; j< itemTU.getChildCount();j++){ //Module course
				TreeItem itemM = itemTU.getChild(j);
				for(int k = 0; k< itemM.getChildCount();k++){
					if(((TreeTeachingWidget)itemM.getChild(k).getWidget()).teaching == teaching){
						return ((TreeModuleWidget)itemM).getModule();
					}
				}
			}
		}
		return null;
	}
	
}
