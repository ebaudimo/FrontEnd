package com.pedEdt.frontEnd.client.controller;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.pedEdt.frontEnd.client.model.Module;
import com.pedEdt.frontEnd.client.model.Semester;
import com.pedEdt.frontEnd.client.model.Teaching;
import com.pedEdt.frontEnd.client.model.TeachingUnit;

public final class ServerCommunication {

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

	public void createSemester(Semester semester) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/create/semester");
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Semester.toXML.toXml(semester));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Semestre cree");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSemester(Semester semester) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/update/semester");
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Semester.toXML.toXml(semester));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Semestre modifie");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSemester(int id) {
		builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/delete/semester/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du semestre.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Semestre supprime");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* TeachingUnit */

	public void createTeachingUnit(int id_semester, TeachingUnit teachingUnit) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/create/teachingUnit/" + id_semester);
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(TeachingUnit.toXML.toXml(teachingUnit));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("TeachingUnit cree");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeachingUnit(TeachingUnit teachingUnit) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/update/teachingUnit");
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(TeachingUnit.toXML.toXml(teachingUnit));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("TeachingUnit modifie");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeachingUnit(int id) {
		builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/delete/teachingUnit/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du TeachingUnit.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("TeachingUnit supprime");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* Module */

	public void createModule(int id_teachingUnit, Module module) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/create/module/" + id_teachingUnit);
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Module.toXML.toXml(module));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Module cree");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateModule(Module module) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/update/module");
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Module.toXML.toXml(module));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Module modifie");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteModule(int id) {
		builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/delete/module/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du Module.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Module supprime");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


	
	/* Teaching */

	public void createTeaching(int id_module, Teaching teaching) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/create/teaching/" + id_module);
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Teaching.toXML.toXml(teaching));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la creation du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Teaching cree");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTeaching(Teaching teaching) {
		builder = new RequestBuilder(RequestBuilder.POST, "proxy.jsp?url=http://localhost:8080/rest/service/update/teaching");
		builder.setHeader("Content-type", "application/xml");
		builder.setRequestData(Teaching.toXML.toXml(teaching));
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la modification du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Teaching modifie");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTeaching(int id) {
		builder = new RequestBuilder(RequestBuilder.GET, "proxy.jsp?url=http://localhost:8080/rest/service/delete/teaching/" + id);
		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert("Erreur lors de la suppression du Teaching.");
				}

				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						Window.alert("Teaching supprime");
					}
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
}
