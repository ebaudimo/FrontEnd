package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class Module implements java.io.Serializable {
	
	interface ModuleReader extends XmlReader<Module> {}
	public static final ModuleReader fromXML = GWT.create(ModuleReader.class);
	
	public interface ModuleWriter extends XmlWriter<Module> {}
	public static final ModuleWriter toXML = GWT.create(ModuleWriter.class);

	private int id;
	
	private String code;
	
	private String title;
	
	private List<Teaching> teachings;
	
	

	public Module() {
		teachings = new ArrayList<Teaching>();
	}

	public Module(String code, String title, List<Teaching> teachings) {
		this.code = code;
		this.title = title;
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

	public List<Teaching> getTeachings() {
		return this.teachings;
	}

	public void setTeachings(List<Teaching> teachings) {
		this.teachings = teachings;
	}
	
	public void addTeaching(Teaching teaching) {
		this.teachings.add(teaching);
	}
	
	public void removeTeaching(Teaching teaching) {
		this.teachings.remove(teaching);
	}
	
	public List<Teaching> isSessionInPeriod(long start) {
		
		List<Teaching> myList = new ArrayList<Teaching>();
		
		for (int i = 0; i < this.teachings.size(); i++)
			if (this.teachings.get(i).isSessionInPeriod(start))
				myList.add(this.teachings.get(i));
		
		return myList;
	}
	
}
