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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.util.DebugPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MainGUI {
	
	protected VerticalPanel vpan;
	protected HorizontalPanel hpan;
	protected ScheduleGridPanel schedGridPan;
	protected ScheduleTreePanel schedTree;
	protected ScheduleMenuBar schedMenubar;
	protected ScheduleSemesterInformation schedInfobar;
	protected HorizontalPanel menuContentPanel;
	
	//singleton
	private static MainGUI mainGUIInstance;
	
	public static MainGUI getInstance(Semester s) {
		if(null == mainGUIInstance) 
			mainGUIInstance = new MainGUI(s);
		
		return mainGUIInstance;
	}
	
	private MainGUI(Semester s) {
		
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
		
		//1362060000 for test
		//get a list of teaching which is present in the timestamp week
		//List<Teaching> myList = s.isSessionInPeriod(1362060000);

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
		
		ScheduleNavigationBar navBar = new ScheduleNavigationBar();
		vpan.add(navBar);
		
		RootPanel.get().add(vpan);
		
	}
}
