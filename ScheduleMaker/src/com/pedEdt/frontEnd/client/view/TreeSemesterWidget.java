package com.pedEdt.frontEnd.client.view;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class TreeSemesterWidget extends Tree{
	
	protected Semester semester;
	
	public TreeSemesterWidget(Semester semester){
		super();
		this.semester = semester;
		
		// code from https://code.google.com/p/google-web-toolkit/issues/detail?id=3660
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
		for (TeachingUnit teachingUnit : semester.getTeachingUnits()) {
			TreeTeachingUnitWidget widget = new TreeTeachingUnitWidget(teachingUnit);
			widget.createTree();
			addItem(widget);
		}
	}
	
}
