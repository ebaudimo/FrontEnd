package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class Module implements java.io.Serializable {
	
	interface ModuleReader extends XmlReader<Module> {}
	public static final ModuleReader fromXML = GWT.create(ModuleReader.class);
	
	interface ModuleWriter extends XmlWriter<Module> {}
	public static final ModuleWriter toXML = GWT.create(ModuleWriter.class);

	private int id;
	
	private String code;
	
	private String title;
	
	private TeachingUnit teachingUnit;
	
	private List<Teaching> teachings;

	public Module() {
		teachings = new ArrayList<Teaching>();
	}

	public Module(String code, String title, TeachingUnit teachingUnit, List<Teaching> teachings) {
		this.code = code;
		this.title = title;
		this.teachingUnit = teachingUnit;
		this.teachings = teachings;
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

	public TeachingUnit getTeachingUnit() {
		return this.teachingUnit;
	}

	public void setTeachingUnit(TeachingUnit teachingUnit) {
		this.teachingUnit = teachingUnit;
	}

	public List<Teaching> getTeachings() {
		return this.teachings;
	}

	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}
	
	public void addTeaching(Teaching teaching) {
		this.teachings.add(teaching);
		teaching.setModule(this);
	}
	
	public void removeTeaching(Teaching teaching) {
		if( this.teachings.remove(teaching) )
			teaching.setModule(null);
	}
	
	public List<Teaching> isSessionInPeriod(int start) {
		
		List<Teaching> myList = new ArrayList<Teaching>();
		
		for (int i = 0; i < this.teachings.size(); i++)
			if (this.teachings.get(i).isSessionInPeriod(start))
				myList.add(this.teachings.get(i));
		
		return myList;
	}
	
}
