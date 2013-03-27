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
import com.google.gwt.user.client.ui.RootPanel;
import com.pedEdt.frontEnd.client.view.StartWindow;
import com.pedEdt.frontEnd.client.view.TitlePanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ScheduleMaker implements EntryPoint {
	public void onModuleLoad() {
		StartWindow startPopup = new StartWindow();
		RootPanel.get().setStyleName("center");
		RootPanel.get().add(new TitlePanel());
		RootPanel.get().add(startPopup);
		startPopup.center();
	}
}
