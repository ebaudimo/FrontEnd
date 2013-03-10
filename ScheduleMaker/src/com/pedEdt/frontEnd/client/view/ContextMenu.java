package com.pedEdt.frontEnd.client.view;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContextMenu {
	
	protected Widget parent;
	final protected PopupPanel menuPanel;
	
	
	public ContextMenu(Widget parent) {
		
		this.parent = parent;
		
		this.menuPanel = new PopupPanel(true);
		MenuBar popupMenu = new MenuBar(true);
		
		Command cmdModifyItem = new Command() {
			public void execute() {
				Window.alert("Show new page to modify item");
				menuPanel.hide();
			}
		};
		
		Command cmdShowDetaiItem = new Command() {
			public void execute() {
				Window.alert("Show new page see details");
				menuPanel.hide();
			}
		};
		
		MenuItem modify = new MenuItem("Modifier", cmdModifyItem);
		MenuItem details = new MenuItem("Details", cmdShowDetaiItem);
		
		popupMenu.addItem(modify);
		popupMenu.addItem(details);
		
		this.menuPanel.add(popupMenu);	
	}
	
	public void showMenu() {
		this.menuPanel.showRelativeTo(this.parent);
	}
}
