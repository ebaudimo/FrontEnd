package com.pedEdt.frontEnd.client.view;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.controller.ServerCommunication;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.SemesterList;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class StartWindow extends PopupPanel {

	final private PopupPanel me;

	public StartWindow() {
		super(false, true);

		me = this;
		this.setSize("300px", "300px");
		this.setStyleName("startWindow");

		VerticalPanel contentPanel = new VerticalPanel();
		contentPanel.add(new Label("Veuillez patienter nous recherchons les semestres déjà existant ..."));

		//build the semesterList with a list of semester send by server
		buildSemesterList(contentPanel);

		//add the form to popup
		this.setWidget(contentPanel);	
	}

	private void buildAddButton(VerticalPanel parent) {
		parent.add(new Button("Ajouter", new ClickHandler() {
			public void onClick(ClickEvent event) {
				Forms.popupCreateSemester().center();
			}
		}));
	}

	private void buildLoadButton(Panel parent, final ListBox list) {
		parent.add(new Button("Charger", new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchSemester(list.getValue(list.getSelectedIndex()));
			}
		}));
	}
	
	private void buildGoSemesterButton(VerticalPanel parent, final String idSemester) {
		parent.add(new Button("Aller au semestre courant", new ClickHandler() {
			public void onClick(ClickEvent event) {
				searchSemester(idSemester);
			}
		}));
	}

 	private void buildSemesterList(final VerticalPanel parent) {

		final ListBox myListBox = new ListBox();

		String url = ServerCommunication.SERVERURL + "read/semesters";
		final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			builder.sendRequest(null, new RequestCallback() {

				public void onResponseReceived(Request request, Response response) {
					parent.clear();
					parent.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
					
					if(response.getStatusCode() == 200) {
						List<Semester> sl = SemesterList.fromXML.read(response.getText().trim()).getSemesterList();
						if(sl != null) {
							if(!sl.isEmpty()) {
								parent.add(new Label("Selectionner un semestre : é è à "));
								Iterator<Semester> i = sl.iterator();
								while (i.hasNext()) {
									Semester s = i.next();
									myListBox.addItem("Semestre " + String.valueOf(s.getNumber()) + " de " + String.valueOf(s.getYear()),
											String.valueOf(s.getId())); //value is the semester id in database

									if(DateUtil.inPeriod(new Date(), 
											DateUtil.getDate(s.getStartDate()), 
											DateUtil.getDate(s.getEndDate()))) {
										buildGoSemesterButton(parent, String.valueOf(s.getId()));
									}	
								}
								HorizontalPanel hpan = new HorizontalPanel();
								hpan.add(myListBox);
								buildLoadButton(hpan, myListBox);
								parent.add(hpan);
							}	
						}
						buildAddButton(parent);					
					}
				}

				@Override
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur sur la récupèration des semestres.");
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	private void searchSemester(String id) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, ServerCommunication.SERVERURL + "read/semester/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 200) {		
						final Semester semester = Semester.fromXML.read(response.getText().trim());
						MainGUI.getInstance(semester);
						me.hide();
					}
					else {
						Window.alert(String.valueOf(response.getStatusCode()) + " : " + response.getStatusText());
					}
				}

				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}	
	} //end searchSemester

}