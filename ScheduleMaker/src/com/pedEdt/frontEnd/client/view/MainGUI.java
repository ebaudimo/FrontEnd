/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.pedEdt.frontEnd.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.util.DateUtil;
import com.pedEdt.frontEnd.client.util.DebugPanel;

public class MainGUI {
	
	protected VerticalPanel vpan;
	protected HorizontalPanel hpan;
	protected ScheduleGridPanel schedGridPan;
	protected ScheduleTreePanel schedTree;
	protected ScheduleMenuBar schedMenubar;
	protected ScheduleSemesterInformation schedInfobar;
	protected HorizontalPanel menuContentPanel;
	
	private Semester semester;
	
	//singleton
	private static MainGUI mainGUIInstance;
	
	public static MainGUI getInstance(Semester s) {
		if(null == mainGUIInstance) 
			mainGUIInstance = new MainGUI(s);
		
		return mainGUIInstance;
	}
	
	public static MainGUI getInstance() {
		return mainGUIInstance;
	}
	
	private MainGUI(Semester s) {
		
		this.semester = s;
		
		vpan = new VerticalPanel();
		vpan.setSpacing(5);
		hpan = new HorizontalPanel();
		hpan.setSpacing(5);
		schedMenubar = new ScheduleMenuBar();
		schedGridPan = new ScheduleGridPanel();
		
		ScheduleDragController.createInstance(schedGridPan.schedGrid.getDroppableArea(), false);
		ScheduleDragController.getInstance().registerDropController(schedGridPan.getDropController());
		ScheduleDragController.getInstance().setBehaviorDragProxy(true);
		
		//build the tree
		schedTree = new ScheduleTreePanel(s);
				
		hpan.add(schedTree);
		hpan.add(schedGridPan);
		
		//debug
		hpan.add(DebugPanel.getInstance());
		//end debug
		
		schedInfobar = new ScheduleSemesterInformation(s);
		
		menuContentPanel = new HorizontalPanel();
		menuContentPanel.add(schedMenubar);
		menuContentPanel.add(schedInfobar);
		vpan.add(menuContentPanel);
		//vpan.add(schedMenubar);
		
		vpan.add(hpan);
		
		ScheduleNavigationBar navBar = ScheduleNavigationBar.getInstance(1, s.getStartDate(), s.getEndDate());
		vpan.add(navBar);
		
		RootPanel.get().add(vpan);
		
		loadWeekGrid();
		
		}

	public Semester getSemester() {
		return this.semester;
	}
	
	public void loadWeekGrid() {
		schedGridPan.getDropController().removeAllSeanceWidget();
		
		List<Teaching> allTeaching = this.semester.getAllTeaching();
		if(allTeaching != null) {
			for (Teaching teaching : allTeaching) {
				//a real 'for loop' to have a counter
				for(int cpt = 0; cpt < teaching.getSeances().size(); cpt++) {
					if(DateUtil.inThisWeek(DateUtil.getDate(teaching.getSeances().get(cpt)))) {
						SeanceWidget session = new SeanceWidget(teaching, 
								DateUtil.findPosH(DateUtil.getDate(teaching.getSeances().get(cpt))), 
								DateUtil.findPosV(DateUtil.getDate(teaching.getSeances().get(cpt))));
						session.setIndexSession(cpt);

						schedGridPan.getDropController().addTeachingSeanceWidget(session);
					}
				}
			}
		}
		
//		Date date = DateUtil.addWeek(DateUtil.getDate(this.semester.getStartDate()), ScheduleNavigationBar.getInstance().getCurrentValue());
//		List<Teaching> myList = this.semester.isSessionInWeek(date.getTime());
//		if(myList != null) {
//			//put the SessionWidget with teaching in grid
//			Iterator<Teaching> i = myList.iterator();
//			while(i.hasNext()) {
//				Teaching t = i.next();
//
//				List<Long> l = t.getSeances();
//				for(int cpt = 0; cpt < l.size(); cpt++) {
//					if(DateUtil.inThisWeek(DateUtil.getDate(l.get(cpt)))) {
//						SeanceWidget session = new SeanceWidget(t, 
//								DateUtil.findPosH(DateUtil.getDate(l.get(cpt))), 
//								DateUtil.findPosV(DateUtil.getDate(l.get(cpt))));
//						session.setIndexSession(cpt);
//						schedGridPan.getDropController().addTeachingSeanceWidget(session);
//					}
//				}
//			}
//		}
	}
}
