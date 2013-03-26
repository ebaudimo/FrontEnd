package com.pedEdt.frontEnd.client.util;

import java.util.ArrayList;
import java.util.List;

class Color{
	private String name;
	private boolean used;
	private List<Object> users;
	
	public Color(String name){
	this.name = name;
	used = false;
	users = new ArrayList<Object>();
	}
	
	public String getName(){
		return name;
	}
	public boolean isUsed(){
		return used;
	}
	public void addUser(Object o){
		used = true;
		users.add(o);
	}
	public void setUnUsed(){
		used = false;
	}
	
	public boolean usedBy(Object o){
		return users.contains(o);
	}
}

class ColorType {
	private boolean used;
	private List<Object> users;
	private List<Color> colors;
	
	public ColorType(List<Color> colors){
		this.colors = colors;
		users = new ArrayList<Object>();
		used = false;
	}
	
	public List<Color> getColors(){
		return colors;
	}
	
	public boolean usedBy(Object o){
		return users.contains(o);
	}
	
	public void addUser(Object o){
		used = true;
		users.add(o);
	}
	public boolean isUsed(){
		return used;
	}
}

public class ColorUtil {
	
	private List<ColorType> colorTypes;
	
	private static ColorUtil instance = null;
	
	private ColorUtil(){
		colorTypes = new ArrayList<ColorType>();
		
		List<Color> reds = new ArrayList<Color>();
		reds.add(new Color("#990000"));
		reds.add(new Color("#CC0000"));
		reds.add(new Color("#FF0000"));
		reds.add(new Color("#FF6666"));
		colorTypes.add(new ColorType(reds));
		
		List<Color> blues = new ArrayList<Color>();
		blues.add(new Color("#000099"));
		blues.add(new Color("#3333FF"));
		blues.add(new Color("#6666CC"));
		blues.add(new Color("#9999FF"));
		colorTypes.add(new ColorType(blues));
		
		List<Color> greens = new ArrayList<Color>();
		greens.add(new Color("#006600"));
		greens.add(new Color("#009900"));
		greens.add(new Color("#33CC00"));
		greens.add(new Color("#33CC33"));
		colorTypes.add(new ColorType(greens));
		
		List<Color> yellows = new ArrayList<Color>();
		yellows.add(new Color("#FF9900"));
		yellows.add(new Color("#FFCC00"));
		yellows.add(new Color("#FFCC33"));
		yellows.add(new Color("#FFFF66"));
		colorTypes.add(new ColorType(yellows));
		
	}
	
	public static ColorUtil getInstance(){
		if(instance == null)
			instance = new ColorUtil();
		return instance;
	}
	
	private int getFreeColorType(){
		for (ColorType colorType : colorTypes) {
			if(!colorType.isUsed()){
				return colorTypes.indexOf(colorType);
			}
		}
		return 0;
	}
	
	public void setColorType(Object o){
		int i = getFreeColorType();
		colorTypes.get(i).addUser(o);
	}
	
	public void setColor(Object colorTypeUser, Object o){
		for (ColorType ct : colorTypes) {
			if(ct.usedBy(colorTypeUser)){
				for (Color c : ct.getColors()) {
					if(!c.isUsed()){
						c.addUser(o);
						return;
					}
				}
				ct.getColors().get(0).addUser(o);
				return;
			}
		}
	}
	
	public void affectSameColor(Object src, Object o){
		for (ColorType ct : colorTypes) {
			for (Color c : ct.getColors()) {
				if(c.usedBy(src)){
					c.addUser(o);
					return;
				}
			}
		}
	}
	
	public String getColor(Object o){
		for (ColorType ct : colorTypes) {
			for (Color c : ct.getColors()) {
				if(c.usedBy(o))
					return c.getName();
			}
		}
		return null;
	}
}
