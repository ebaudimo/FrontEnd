package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;
import com.pedEdt.frontEnd.client.util.DateUtil;

public class Teaching implements java.io.Serializable {
	
	interface TeachingReader extends XmlReader<Teaching> {}
	public static final TeachingReader fromXML = GWT.create(TeachingReader.class);
	
	public interface TeachingWriter extends XmlWriter<Teaching> {}
	public static final TeachingWriter toXML = GWT.create(TeachingWriter.class);

	private int id;
	
	private TeachingType type;
	
	private String teacher;
	
	private int nbHour;
	
	private int nbSeance;
	
	private int numGroup;
	
	private List<Long> seances = new ArrayList<Long>();
	
	
	
	public Teaching() {
		
	}
	
	public Teaching(TeachingType type, String teacher, int nbHour, int nbSeance, int numGroup, List<Long> seances) {
		this.type = type;
		this.teacher = teacher;
		this.nbHour = nbHour; //nbMinutes in fact
		this.nbSeance = nbSeance;
		this.numGroup = numGroup;
		this.seances = seances;
	}
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public TeachingType getType() {
		return type;
	}

	public void setType(TeachingType type) {
		this.type = type;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getNbHour() {
		return nbHour;
	}

	public void setNbHour(int nbHour) {
		this.nbHour = nbHour;
	}

	public int getNbSeance() {
		return nbSeance;
	}

	public void setNbSeance(int nbSeance) {
		this.nbSeance = nbSeance;
	}

	public int getNumGroup() {
		return numGroup;
	}

	public void setNumGroup(int numGroup) {
		this.numGroup = numGroup;
	}

	public List<Long> getSeances() {
		return seances;
	}

	public void setSeances(List<Long> seances) {
		this.seances = seances;
	}
	
	public int addSeance(long date) {
		this.seances.add(date);
		Collections.sort(this.seances);
		
		return this.seances.indexOf(date);
	}
	
	public int findSeance(long date) {
		return this.seances.indexOf(date);
	}
	
	public void removeSeance(long date) {
		this.seances.remove(date);
	}
	
	public void removeSeanceByIndex(int index) {
		this.seances.remove(index);
		Collections.sort(this.seances);
	}
	
	public boolean isSessionInPeriod(long start) {
		for (int i = 0; i < this.seances.size(); i++) {
//			if (this.seances.get(i)*1000 >= start && this.seances.get(i)*1000 <= start + DateUtil.WEEK)
//				return true;
//			}
			
			Date d = DateUtil.getDate(this.seances.get(i));
			Date s = new Date(start);
			Date e = new Date(start + DateUtil.WEEK);
			
			if(d.after(s) && d.before(e))
				return true;
		}
		return false;
	}
}
