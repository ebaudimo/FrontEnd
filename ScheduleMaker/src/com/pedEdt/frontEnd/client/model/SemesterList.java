package com.pedEdt.frontEnd.client.model;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class SemesterList {
	public interface SemesterListReader extends XmlReader<SemesterList> {}
    public static final SemesterListReader fromXML = GWT.create(SemesterListReader.class);
    
    public interface SemesterListWriter extends XmlWriter<SemesterList> {}
    public static final SemesterListWriter toXML = GWT.create(SemesterListWriter.class);
    
    

	@Path("semester") List<Semester> semesterList;
	
	public List<Semester> getSemesterList(){
		return semesterList;
	}
}
