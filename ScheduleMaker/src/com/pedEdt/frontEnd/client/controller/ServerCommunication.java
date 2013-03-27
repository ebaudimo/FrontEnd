package com.pedEdt.frontEnd.client.controller;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingUnit;
import com.pedEdt.frontEnd.client.view.MainGUI;
import com.pedEdt.frontEnd.client.view.StartWindow;
import com.pedEdt.frontEnd.client.view.TitlePanel;

public final class ServerCommunication {

	public final static String SERVERURL = "http://localhost:8080/rest/service/";
	
	private static ServerCommunication instance = null;
	private static RequestBuilder builder;
	
	private ServerCommunication() {
		super();
	}

	public final static ServerCommunication getInstance() {
		if (ServerCommunication.instance == null) {
			synchronized(ServerCommunication.class) {
				if (ServerCommunication.instance == null) {
					ServerCommunication.instance = new ServerCommunication();
				}
			}
		}
		return ServerCommunication.instance;
	}


	
	/* Semester */

	public void createSemester(final Semester semester) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "create/semester");
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Semester.toXML.toXml(semester), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Semester s = Semester.fromXML.read(response.getText().trim());
						MainGUI.getInstance(s);
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSemester(final Semester semester) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "update/semester");
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Semester.toXML.toXml(semester), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance(semester);
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSemester(int idSemester) {
		builder = new RequestBuilder(RequestBuilder.GET, SERVERURL + "delete/semester/" + idSemester);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						RootPanel.get().clear();
						RootPanel.get().setStyleName("center");
						RootPanel.get().add(new TitlePanel());
						StartWindow startPopup = new StartWindow();
						RootPanel.get().add(startPopup);
						startPopup.center();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* TeachingUnit */

	public void createTeachingUnit(final Semester semester, TeachingUnit teachingUnit) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "create/teachingUnit/" + semester.getId());
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(TeachingUnit.toXML.toXml(teachingUnit), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Semester semester = MainGUI.getInstance().getSchedTree().getSemesterTree().getSemester();
						Semester s = Semester.fromXML.read(response.getText().trim());
						semester.setTeachingUnits(s.getTeachingUnits());
						MainGUI.getInstance().reloadTree();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeachingUnit(TeachingUnit teachingUnit) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "update/teachingUnit");
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(TeachingUnit.toXML.toXml(teachingUnit), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeachingUnit(int id_teachingUnit) {
		builder = new RequestBuilder(RequestBuilder.GET, SERVERURL + "delete/teachingUnit/" + id_teachingUnit);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* Module */

	public void createModule(final TeachingUnit teachingUnit, Module module) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "create/module/" + teachingUnit.getId());
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Module.toXML.toXml(module), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Semester semester = MainGUI.getInstance().getSchedTree().getSemesterTree().getSemester();
						TeachingUnit t = TeachingUnit.fromXML.read(response.getText().trim());
						semester.removeTeachingUnit(teachingUnit);
						semester.addTeachingUnit(t);
						MainGUI.getInstance().reloadTree();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateModule(Module module) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "update/module");
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Module.toXML.toXml(module), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteModule(int id_module) {
		builder = new RequestBuilder(RequestBuilder.GET, SERVERURL + "delete/module/" + id_module);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* Teaching */

	public void createTeaching(final Module module, Teaching teaching) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "create/teaching/" + module.getId());
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Teaching.toXML.toXml(teaching), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						TeachingUnit teachingUnit = MainGUI.getInstance().getSchedTree().getSemesterTree().getParentTeachingUnit(module);
						Module m = Module.fromXML.read(response.getText().trim());
						teachingUnit.removeModule(module);
						teachingUnit.addModule(m);
						MainGUI.getInstance().reloadTree();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeaching(Teaching teaching) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "update/teaching");
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(Teaching.toXML.toXml(teaching), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeaching(int id_teaching) {
		builder = new RequestBuilder(RequestBuilder.GET, SERVERURL + "delete/teaching/" + id_teaching);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						MainGUI.getInstance().reloadTree();
						MainGUI.getInstance().loadWeekGrid();
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* TeachingSceance */

	public void createTeachingSceance(int id_teaching, long date) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "create/teachingSeance/" + id_teaching);
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest("" + date, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation de la TeachingSeance.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeachingSeance(int id_teaching, long oldDate, long newDate) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "update/teachingSeance/" + id_teaching);
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest(oldDate + "," + newDate, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification de la TeachingSeance.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	public void deleteTeachingSeance(int id_teaching, long date) {
		builder = new RequestBuilder(RequestBuilder.POST, SERVERURL + "delete/teachingSeance/" + id_teaching);
		builder.setHeader("Content-Type", "application/xml");
		try {
			builder.sendRequest("" + date, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression de la TeachingSeance.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
}
