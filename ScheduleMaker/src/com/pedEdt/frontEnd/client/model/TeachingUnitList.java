package com.pedEdt.frontEnd.client.model;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class TeachingUnitList {
	
	public interface TeachingUnitListReader extends XmlReader<TeachingUnitList> {}
    public static final TeachingUnitListReader fromXML = GWT.create(TeachingUnitListReader.class);
    
    public interface TeachingUnitListWriter extends XmlWriter<TeachingUnitList> {}
    public static final TeachingUnitListWriter toXML = GWT.create(TeachingUnitListWriter.class);
    
    

	@Path("teachingUnit") List<TeachingUnit> teachingUnitList;
	
	public List<TeachingUnit> getTeachingUnitList(){
		return teachingUnitList;
	}
}
