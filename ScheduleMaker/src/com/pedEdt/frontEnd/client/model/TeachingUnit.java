package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class TeachingUnit implements java.io.Serializable {

	interface TUReader extends XmlReader<TeachingUnit> {}
	public static final TUReader fromXML = GWT.create(TUReader.class);
	
	interface TUWriter extends XmlWriter<TeachingUnit> {}
	public static final TUWriter toXML = GWT.create(TUWriter.class);
	
	private int id;
	
	private String code;
	
	private String title;
	
	private Semester semester;
	
	private List<Module> modules;
	
	

	public TeachingUnit() {
		modules = new ArrayList<Module>();
	}

	public TeachingUnit(String code, String title, Semester semester, List<Module> modules) {
		this.code = code;
		this.title = title;
		this.semester = semester;
		this.modules = modules;
	}
	

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public Semester getSemester() {
		return this.semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module module) {
		this.modules.add(module);
		module.setTeachingUnit(this);
	}
	
	public void removeModule(Module module) {
		if( this.modules.remove(module) )
			module.setTeachingUnit(null);
	}
	
	public List<Teaching> isSessionInPeriod(int start) {
		
		List<Teaching> myList = new ArrayList<Teaching>();
		
		for (int i = 0; i < this.modules.size(); i++) {
			//List<Teaching> mList = this.modules.get(i).isSessionInPeriod(start, end);
			myList.addAll(this.modules.get(i).isSessionInPeriod(start));
		}
		
		return myList;
	}
	
}