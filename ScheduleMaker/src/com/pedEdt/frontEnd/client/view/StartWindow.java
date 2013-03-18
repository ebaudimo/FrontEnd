package com.pedEdt.frontEnd.client.view;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.SemesterList;

public class StartWindow extends PopupPanel {

	final private PopupPanel me;

	public StartWindow() {
		super(false, true);

		me = this;

		setSize("300px", "300px");
		setStyleName("startWindow");
		setPopupPosition(500, 100);

		VerticalPanel contentPanel = new VerticalPanel();

		contentPanel.add(new Label("Veuillez patienter nous recherchons les semestres deja existant ..."));

		//build the semesterList with a list of semester send by server
		//final ListBox semesterList = new ListBox();
		buildSemesterList(contentPanel);

		//add the form to popup
		this.setWidget(contentPanel);	
	}

	private void buildAddButton(VerticalPanel parent) {
		parent.add(new Button("Ajouter", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//Window.alert("Not implemented yet, coming soon ;)");
				PopupPanel newSemester = Forms.popupCreateSemester();
				newSemester.showRelativeTo(me);
				me.hide();
			}
		}));
	}

	private void buildLoadButton(VerticalPanel parent, final ListBox list) {
		parent.add(new Button("Charger", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				searchSemester(list.getValue(list.getSelectedIndex()));
			}
		}));
	}

 	private void buildSemesterList(/*final ListBox myListBox,*/ final VerticalPanel parent) {

		final ListBox myListBox = new ListBox();

		String url = "proxy.jsp?url=http://localhost:8080/rest/service/read/semesters";
		final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			builder.sendRequest(null, new RequestCallback() {

				public void onResponseReceived(Request request, Response response) {

					parent.clear();
					parent.add(new Label("Selectionner un semestre : "));

					if(response.getStatusCode() == 200) {
						List<Semester> sl = SemesterList.fromXML.read(response.getText().trim()).getSemesterList();

						if(!sl.isEmpty()) {
							Iterator<Semester> i = sl.iterator();
							while (i.hasNext()) {
								Semester s = i.next();
								myListBox.addItem("Semestre " + String.valueOf(s.getNumber()) + " de " + String.valueOf(s.getYear()),
													String.valueOf(s.getId())); //value is the semester id in database
							}

							parent.add(myListBox);
							buildLoadButton(parent, myListBox);
						}						
					}
					
					buildAddButton(parent);
				}

				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					Window.alert("Erreur sur la récupération des semestres.");
				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchSemester(String id) {

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/read/semester/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 200) {
						
						final Semester semester = Semester.fromXML.read(response.getText().trim());
						MainGUI.getInstance(semester);
						me.hide();
						
//						RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/read/teachingUnits/semester/" + semester.getId());
//						try {
//							builder.sendRequest(null, new RequestCallback() {
//								public void onResponseReceived(Request request, Response response) {
//									if(response.getStatusCode() == 200) {
//										semester.setTeachingUnits(TeachingUnitList.fromXML.read(response.getText().trim()).getTeachingUnitList());
//
//										if(semester.getTeachingUnits() != null) {
//											for (final TeachingUnit teachingUnit : semester.getTeachingUnits()) {
//												RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/read/modules/teachingUnit/" + teachingUnit.getId());
//												try {
//													builder.sendRequest(null, new RequestCallback() {
//														public void onResponseReceived(Request request, Response response) {
//															if(response.getStatusCode() == 200) {
//																teachingUnit.setModules(ModuleList.fromXML.read(response.getText().trim()).getModuleList());
//	
//																if(teachingUnit.getModules() != null) {
//																	for (final Module module : teachingUnit.getModules()) {
//																		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/read/teachings/module/" + module.getId());
//																		try {
//																			builder.sendRequest(null, new RequestCallback() {
//																				public void onResponseReceived(Request request, Response response) {
//																					if(response.getStatusCode() == 200) {
//																						module.setTeachings(TeachingList.fromXML.read(response.getText().trim()).getTeachingList());
//		
//																						MainGUI.getInstance(semester);
//																						//new MainGUI(semester);
//																						me.hide();
//																					}
//																					else {
//																						Window.alert(String.valueOf(response.getStatusCode()) + " : " + response.getStatusText());
//																					}
//																				}
//		
//																				public void onError(Request request, Throwable exception) {
//																					// TODO Auto-generated method stub
//																				}
//																			});
//																		} catch (RequestException e) {
//																			e.printStackTrace();
//																		}
//																	}
//																} //end if Modules list is not empty
//															}
//															else {
//																Window.alert(String.valueOf(response.getStatusCode()) + " : " + response.getStatusText());
//															}
//														}
//	
//														public void onError(Request request, Throwable exception) {
//															// TODO Auto-generated method stub
//														}
//													});
//												} catch (RequestException e) {
//													e.printStackTrace();
//												}
//											}
//										} //end if TeachingUnit is not empty
//									}
//									else {
//										Window.alert(String.valueOf(response.getStatusCode()) + " : " + response.getStatusText());
//									}
//								}
//
//								public void onError(Request request, Throwable exception) {
//									// TODO Auto-generated method stub
//								}
//							});
//						} catch (RequestException e) {
//							e.printStackTrace();
//						}
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