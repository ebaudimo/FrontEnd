package com.pedEdt.frontEnd.client.model;

import java.util.ArrayList;
import java.util.List;


public class Teaching implements java.io.Serializable {

	private int id;
	
	private TeachingType type;
	
	private String teacher;
	
	private int nbHour;
	
	private int nbSeance;
	
	private int numGroup;
	
	private List<Integer> sceances;
	
	private Module module;
	
	
	
	public Teaching() {
		sceances = new ArrayList<Integer>();
	}
	
	public Teaching(TeachingType type, String teacher, int nbHour, int nbSeance, int numGroup, List<Integer> sceances, Module module) {
		this.type = type;
		this.teacher = teacher;
		this.nbHour = nbHour;
		this.nbSeance = nbSeance;
		this.numGroup = numGroup;
		this.sceances = sceances;
		this.module = module;
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

	public List<Integer> getSeances() {
		return sceances;
	}

	public void setSeances(List<Integer> sceances) {
		this.sceances = sceances;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	public void addSeance(int timestamp) {
		this.sceances.add(timestamp);
	}
	
	public void removeSeance(int timestamp) {
		for (int i = 0; i < this.sceances.size(); i++)
			if (this.sceances.get(i) == timestamp)
				this.sceances.remove(i);
	}
}
