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
package com.pedEdt.frontEnd.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ScheduleDragController;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingType;
import com.pedEdt.frontEnd.client.model.TeachingUnit;
import com.pedEdt.frontEnd.client.util.DebugPanel;
import com.pedEdt.frontEnd.client.view.ScheduleGridPanel;
import com.pedEdt.frontEnd.client.view.ScheduleMenuBar;
import com.pedEdt.frontEnd.client.view.ScheduleTreePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScheduleMaker implements EntryPoint {
	
	protected VerticalPanel vpan;
	protected HorizontalPanel hpan;
	protected ScheduleGridPanel schedGridPan;
	protected ScheduleTreePanel schedTree;
	protected ScheduleMenuBar schedMenubar;
	
	public void onModuleLoad() {
		vpan = new VerticalPanel();
		vpan.setSpacing(5);
		hpan = new HorizontalPanel();
		hpan.setSpacing(5);
		schedMenubar = new ScheduleMenuBar();
		schedGridPan = new ScheduleGridPanel();
		
		ScheduleDragController.createInstance(schedGridPan.schedGrid.getDroppableArea(), false);
		ScheduleDragController.getInstance().registerDropController(schedGridPan.getDropController());
		ScheduleDragController.getInstance().setBehaviorDragProxy(true);
		
		//static semester for the test
		Teaching teaching1 = new Teaching();
		teaching1.setType(TeachingType.COURS);
		
		Teaching teaching2 = new Teaching();
		teaching2.setType(TeachingType.TD);
		
		Teaching teaching3 = new Teaching();
		teaching3.setType(TeachingType.COURS);
		
		Module module1 = new Module();
		module1.setCode("UE1M1");
		module1.setTitle("Module 1");
		module1.addTeaching(teaching1);
		module1.addTeaching(teaching2);
		
		Module module2 = new Module();
		module2.setCode("UE2M1");
		module2.setTitle("Module 1");
		module2.addTeaching(teaching3);
		
		TeachingUnit teachingUnit1 = new TeachingUnit();
		teachingUnit1.setCode("UE1");
		teachingUnit1.setTitle("UE 1");
		teachingUnit1.addModule(module1);
		
		TeachingUnit teachingUnit2 = new TeachingUnit();
		teachingUnit2.setCode("UE2");
		teachingUnit2.setTitle("UE 2");
		teachingUnit2.addModule(module2);
		
		Semester semester = new Semester();
		semester.addTeachingUnit(teachingUnit1);
		semester.addTeachingUnit(teachingUnit2);
		//end semester
		
		schedTree = new ScheduleTreePanel(semester);
		

		hpan.add(schedTree);
		hpan.add(schedGridPan);
		
		//debug
		hpan.add(DebugPanel.getInstance());
		//end debug
		
		vpan.add(schedMenubar);
		vpan.add(hpan);
		RootPanel.get().add(vpan);
	}
}
