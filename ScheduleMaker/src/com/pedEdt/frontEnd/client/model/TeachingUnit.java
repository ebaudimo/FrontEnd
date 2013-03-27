package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class TeachingUnit implements java.io.Serializable {

	public interface TUReader extends XmlReader<TeachingUnit> {}
	public static final TUReader fromXML = GWT.create(TUReader.class);
	
	public interface TUWriter extends XmlWriter<TeachingUnit> {}
	public static final TUWriter toXML = GWT.create(TUWriter.class);
	
	private int id;
	
	private String code;
	
	private String title;
	
	private List<Module> modules = new ArrayList<Module>();
	
	

	public TeachingUnit() {
		
	}

	public TeachingUnit(String code, String title, List<Module> modules) {
		this.code = code;
		this.title = title;
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

	public List<Module> getModules() {
		return this.modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module module) {
		this.modules.add(module);
	}
	
	public void removeModule(Module module) {
		this.modules.remove(module);
	}
	
	public List<Teaching> getAllTeaching() {
		List<Teaching> myList = new ArrayList<Teaching>();
		if(this.modules != null) {
			for (int i = 0; i < this.modules.size(); i++) {
				myList.addAll(this.modules.get(i).getAllTeaching());
			}
		}
		return myList;
	}
	
	public List<Teaching> isSessionInPeriod(long start) {
		
		List<Teaching> myList = new ArrayList<Teaching>();
		
		if(this.modules != null) {
			for (int i = 0; i < this.modules.size(); i++) {
				//List<Teaching> mList = this.modules.get(i).isSessionInPeriod(start, end);
				myList.addAll(this.modules.get(i).isSessionInPeriod(start));
			}
		}
		return myList;
	}
	
	public Module getModuleById(int id) {
		for (Module module : modules)
			if (module.getId() == id)
				return module;
		return null;
	}
}