package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class Semester implements java.io.Serializable {
	
	public interface SemesterReader extends XmlReader<Semester> {}
	public static final SemesterReader fromXML = GWT.create(SemesterReader.class);
	
	public interface SemesterWriter extends XmlWriter<Semester> {}
	public static final SemesterWriter toXML = GWT.create(SemesterWriter.class);

	private int id;
	
	private int year;
	
	private int number;
	
	private long startDate;
	
	private long endDate;

	private List<TeachingUnit> teachingUnits;



	public Semester() {

	}

	public Semester(int year, int number, long startD, long endD, List<TeachingUnit> teachingUnits) {
		this.year = year;
		this.number = number;
		this.startDate = startD;
		this.endDate = endD;
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

	public void setNumber(int number) {
		this.number = number;
	}

	public long getStartDate() {
		return this.startDate;
	}

	public void setStartDate(long date) {
		this.startDate = date;
	}

	public long getEndDate() {
		return this.endDate;
	}

	public void setEndDate(long date) {
		this.endDate = date;
	}

	public List<TeachingUnit> getTeachingUnits() {
		return this.teachingUnits;
	}

	public void setTeachingUnits(List<TeachingUnit> teachingUnits) {
		this.teachingUnits = teachingUnits;
	}
	
	public void addTeachingUnit(TeachingUnit teachingUnit) {
		this.teachingUnits.add(teachingUnit);
	}
	
	public void removeTeachingUnit(TeachingUnit teachingUnit) {
		this.teachingUnits.remove(teachingUnit);
	}
	
	public List<Teaching> getAllTeaching() {
		List<Teaching> myList = new ArrayList<Teaching>();
		if(this.teachingUnits != null) {
			for (int i = 0; i < this.teachingUnits.size(); i++) {
				myList.addAll(this.teachingUnits.get(i).getAllTeaching());
			}
		}
		return myList;
	}
	
	public List<Teaching> isSessionInWeek(long start) {
		
		List<Teaching> myList = new ArrayList<Teaching>();
		
		if(this.teachingUnits != null) {
			for (int i = 0; i < this.teachingUnits.size(); i++) {
				myList.addAll(this.teachingUnits.get(i).isSessionInPeriod(start));
			}
		}
		return myList;
	}
}