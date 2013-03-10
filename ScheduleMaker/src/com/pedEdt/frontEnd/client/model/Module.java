package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;


public class Module implements java.io.Serializable {

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
}
