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
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.SemesterList;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingList;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public class StartWindow extends PopupPanel {

	final private PopupPanel me;
	
	public StartWindow() {
		//if true the panel closes automatically when the user click outside
		super(false);
		
		me = this;
		
		setSize("300px", "300px");
		setStyleName("startWindow");
		setPopupPosition(500, 100);
		
		VerticalPanel contentPanel = new VerticalPanel();
		contentPanel.add(new Label("Selectionner un semestre : "));
		
		//build the semesterList with a list of semester send by server
		final ListBox semesterList = new ListBox();
		buildSemesterList(semesterList);

		contentPanel.add(semesterList);
		
		//if(semesterList.getItemCount() != 0) {
			contentPanel.add(new Button("Charger", new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					searchSemester(semesterList.getValue(semesterList.getSelectedIndex()));
				}
			}));
		//}
		/*else {
			contentPanel.add(new Button("Ajouter", new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("Not implemented yet, coming soon ;)");
				}
			}));
		}*/

		//add the form to popup
		this.setWidget(contentPanel);	
	}

	private void buildSemesterList(final ListBox myListBox) {
		
		String url = "proxy.jsp?url=http://localhost:8080/rest/service/read/semesters";
		final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
			builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {				
					if(response.getStatusCode() == 200) {
						List<Semester> sl = SemesterList.fromXML.read(response.getText().trim()).getSemesterList();
						
						if(sl.isEmpty()) {
							Window.alert("Aucun semestre encore enregistré.");
						}
						else {
							Iterator<Semester> i = sl.iterator();
							while (i.hasNext()) {
								Semester s = i.next();
								myListBox.addItem(String.valueOf(s.getNumber()) + " de " + String.valueOf(s.getYear()),
													String.valueOf(s.getId())); //value is the semester id in database
							}
						}
					}
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

	private void searchSemester(final String id) {
		String url = "proxy.jsp?url=http://localhost:8080/rest/service/read/teachings/semester/" + id;
		final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
			builder.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					if(response.getStatusCode() == 200) {
						Semester mySemester = new Semester();
						mySemester.setId(Integer.valueOf(id));
						List<Teaching> tl = TeachingList.fromXML.read(response.getText().trim()).getTeachingList();
						
						Iterator<Teaching> i = tl.iterator();
						while (i.hasNext()) {
							Teaching t = i.next();
							
							Module m = t.getModule();
							m.addTeaching(t);
							TeachingUnit tu = m.getTeachingUnit();
							tu.addModule(m);
							
							mySemester.addTeachingUnit(tu);
						}
						
						new MainGUI(mySemester);
						me.hide();
						
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	} //end searchSemester
	
}
