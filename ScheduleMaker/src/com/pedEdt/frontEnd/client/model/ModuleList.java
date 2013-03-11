package com.pedEdt.frontEnd.client.model;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

public class ModuleList {
	
	public interface ModuleListReader extends XmlReader<ModuleList> {}
    public static final ModuleListReader fromXML = GWT.create(ModuleListReader.class);
    
    public interface ModuleListWriter extends XmlWriter<ModuleList> {}
    public static final ModuleListWriter toXML = GWT.create(ModuleListWriter.class);
    
    

	@Path("module") List<Module> moduleList;
	
	public List<Module> getModuleList(){
		return moduleList;
	}
}
