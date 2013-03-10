package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class Semester implements java.io.Serializable {
	
	public interface SemesterReader extends XmlReader<Semester> {}
	public static final SemesterReader fromXML = GWT.create(SemesterReader.class);
	
	interface SemesterWriter extends XmlWriter<Semester> {}
	public static final SemesterWriter toXML = GWT.create(SemesterWriter.class);

	private int id;
	
	private int year;
	
	private int number;
	
	private List<TeachingUnit> teachingUnits;



	public Semester() {
		teachingUnits = new ArrayList<TeachingUnit>();
	}

	public Semester(int year, int number, List<TeachingUnit> teachingUnits) {
		this.year = year;
		this.number = number;
		this.teachingUnits = teachingUnits;
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int nunber) {
		this.number = nunber;
	}

	public List<TeachingUnit> getTeachingUnits() {
		return this.teachingUnits;
	}

	public void setTeachingUnits(List<TeachingUnit> teachingUnits) {
		this.teachingUnits = teachingUnits;
	}
	
	public void addTeachingUnit(TeachingUnit teachingUnit) {
		this.teachingUnits.add(teachingUnit);
		teachingUnit.setSemester(this);
	}
	
	public void removeTeachingUnit(TeachingUnit teachingUnit) {
		if( this.teachingUnits.remove(teachingUnit) )
			teachingUnit.setSemester(null);
	}
}