package com.pedEdt.frontEnd.client.view;

import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.controller.ServerCommunication;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.util.ColorUtil;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class MainGUI {
	
	protected VerticalPanel vpan;
	protected HorizontalPanel hpan;
	protected ScheduleGridPanel schedGridPan;
	protected ScheduleTreePanel schedTree;
	protected ScheduleMenuBar schedMenubar;
	protected ScheduleSemesterInformation schedInfobar;
	protected VerticalPanel menuContentPanel;
	protected ScheduleNavigationBar navBar;
	
	private Semester semester;
	
	//singleton
	private static MainGUI mainGUIInstance;
	
	public static MainGUI getInstance(Semester s) {
		mainGUIInstance = new MainGUI(s); //rebuild with new semester
		return mainGUIInstance;
	}
	
	public static MainGUI getInstance() {
		return mainGUIInstance;
	}
	
	private MainGUI(Semester s) {

		RootPanel.get().clear();
		ColorUtil.reset();
		
		this.semester = s;
		vpan = new VerticalPanel();
		vpan.setWidth("90%");
		vpan.setSpacing(5);
		vpan.addStyleName("center");
		vpan.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		hpan = new HorizontalPanel();
		hpan.setSpacing(5);
		hpan.setWidth("100%");
		schedMenubar = new ScheduleMenuBar(s);
		navBar = ScheduleNavigationBar.getInstance(1, s.getStartDate(), s.getEndDate());
		schedGridPan = new ScheduleGridPanel();
		
		
		ScheduleDragController.createInstance(schedGridPan.schedGrid.getDroppableArea(), false);
		ScheduleDragController.getInstance().registerDropController(schedGridPan.getDropController());
		ScheduleDragController.getInstance().setBehaviorDragProxy(true);
		
		//build the tree
		schedTree = new ScheduleTreePanel(s);
		
		hpan.add(schedTree);
		hpan.add(schedGridPan);
		
		schedInfobar = new ScheduleSemesterInformation(s);
		
		
		menuContentPanel = new VerticalPanel();
		menuContentPanel.setWidth("100%");
		schedMenubar.setWidth("100%");
		schedInfobar.setWidth("100%");
		menuContentPanel.add(schedMenubar);
		menuContentPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		menuContentPanel.add(schedInfobar);
		
		vpan.add( new TitlePanel());
		vpan.add(menuContentPanel);
		vpan.add(hpan);
		vpan.add(navBar);
		
		RootPanel.get().add(vpan);
		
		loadWeekGrid();
	}

	public Semester getSemester() {
		return this.semester;
	}
	
	public ScheduleTreePanel getSchedTree() {
		return this.schedTree;
	}
	
	public void loadWeekGrid() {
		schedGridPan.getDropController().removeAllSeanceWidget();
		
		List<Teaching> allTeaching = this.semester.getAllTeaching();
		if(allTeaching != null) {
			for (Teaching teaching : allTeaching) {
				boolean update = false;
				for(int cpt = 0; cpt < teaching.getSeances().size(); cpt++) {
					if(DateUtil.inThisWeek(DateUtil.getDate(teaching.getSeances().get(cpt)))) {
						//this teaching has a seance in the current week, we need to build and place a seanceWidget
						SeanceWidget session = new SeanceWidget(teaching, 
								DateUtil.findPosH(DateUtil.getDate(teaching.getSeances().get(cpt))), 
								DateUtil.findPosV(DateUtil.getDate(teaching.getSeances().get(cpt))));
						session.setBeginning(teaching.getSeances().get(cpt));
						
						DOM.setStyleAttribute(session.getElement(), "background",
								ColorUtil.getInstance().getColor(teaching));
						
						session.setTitle(schedTree.semesterTree.getParentModule(teaching).getTitle());
						
						schedGridPan.getDropController().addTeachingSeanceWidget(session);
					}
					
					//this seance is not correct
					if(!DateUtil.inThisSemester(DateUtil.getDate(teaching.getSeances().get(cpt)))) {
						teaching.removeSeance(teaching.getSeances().get(cpt));
						update = true;
					}
					
				} //end for each seance of teaching
				
				if(update)
					ServerCommunication.getInstance().updateTeaching(teaching);
				
			} //end for each teaching
		}
	}

	public void reloadTree() {
		mainGUIInstance.schedTree.rebuildTree(mainGUIInstance.semester);
	}
}
