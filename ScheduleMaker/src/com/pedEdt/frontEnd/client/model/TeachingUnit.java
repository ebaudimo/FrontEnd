package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;


public class TeachingUnit implements java.io.Serializable {

	
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
}