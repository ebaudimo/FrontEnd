package com.pedEdt.frontEnd.client.view;

import java.util.Date;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class ScheduleNavigationBar extends Composite {
	
	private long start;
	private long end;
	private int currentValue;
	private long nbWeek;
	private Label myLabel;
	
	
	private static ScheduleNavigationBar navBar;
	public static ScheduleNavigationBar getInstance(int numWeek, long startFromDB, long endFromDB) {
		if(navBar == null)
			navBar = new ScheduleNavigationBar(numWeek, startFromDB, endFromDB);
		
		return navBar;
	}
	
	public static ScheduleNavigationBar getInstance() {
		return navBar;
	}
	
	private ScheduleNavigationBar(int numWeek, long startFromDB, long endFromDB) {
		
		this.start = DateUtil.getDate(startFromDB).getTime();
		this.end = DateUtil.getDate(endFromDB).getTime();
		
		nbWeek = DateUtil.getNbWeek(new Date(this.start), new Date(this.end));
		
		if(numWeek > 0 && numWeek <= nbWeek)
			setCurrentValue(numWeek);
		else
			setCurrentValue(1);
		
		HorizontalPanel navPanel = new HorizontalPanel();
		
		navPanel.add(new Button("Precedent", new ClickHandler() {

			public void onClick(ClickEvent event) {
				if(currentValue > 1) {
					currentValue --;
					myLabel.setText("Semaine " + String.valueOf(currentValue));
					
					MainGUI.getInstance().loadWeekGrid();
				}
				//else : do nothing
			}
		}));
		
		myLabel = new Label("Semaine " + String.valueOf(this.currentValue));
		navPanel.add(myLabel);
		
		navPanel.add(new Button("Suivant", new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if(currentValue < nbWeek) {
					currentValue ++;
					myLabel.setText("Semaine " + String.valueOf(currentValue));
					
					MainGUI.getInstance().loadWeekGrid();
				}
				//else : do nothing
			}
		}));
		
		navPanel.add(new Label("Aller directement : "));
		final TextBox where = new TextBox();
		where.setStyleName("textbox");
		final Button go = new Button("GO", new ClickHandler() {
			public void onClick(ClickEvent event) {
				currentValue = Integer.valueOf(where.getText());
				myLabel.setText("Semaine " + String.valueOf(currentValue));
				MainGUI.getInstance().loadWeekGrid();
			}
		});
		where.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				String str = where.getText();
				
				if(str.isEmpty()) {
					where.removeStyleDependentName("onError");
					go.setEnabled(false);
				}
				else {
					if(str.matches("[0-9]*")) {
						where.removeStyleDependentName("onError");
						go.setEnabled(true);
					}
					else {
						where.setStyleDependentName("onError", true);
						go.setEnabled(false);
					}
				}
			}
		});
		
		navPanel.add(where);
		navPanel.add(go);
		
		initWidget(navPanel);
	}

	public int getCurrentValue() {
		return this.currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}
	
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}
	
	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}	
}
