package com.pedEdt.frontEnd.client.model;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class TeachingList {
	public interface TeachingListReader extends XmlReader<TeachingList> {}
    public static final TeachingListReader fromXML = GWT.create(TeachingListReader.class);
    
    public interface TeachingListWriter extends XmlWriter<TeachingList> {}
    public static final TeachingListWriter toXML = GWT.create(TeachingListWriter.class);
    
    

	@Path("teaching") List<Teaching> teachingList;
	
	public List<Teaching> getTeachingList(){
		return teachingList;
	}

}
