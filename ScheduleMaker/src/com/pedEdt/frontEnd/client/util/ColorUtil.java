package com.pedEdt.frontEnd.client.util;

import java.util.ArrayList;
import java.util.List;

class Color{
	private String name;
	private List<Object> users;
	
	public Color(String name){
	this.name = name;
	users = new ArrayList<Object>();
	}
	
	public String getName(){
		return name;
	}
	public boolean isUsed(){
		return !users.isEmpty();
	}
	public void addUser(Object o){
		users.add(o);
	}
	
	public boolean usedBy(Object o){
		return users.contains(o);
	}
	
	public void removeUser(Object o){
		users.remove(0);
	}
	
	public void clearUsers(){
		users.clear();
	}
}

class ColorType {
	private List<Object> users;
	private List<Color> colors;
	
	public ColorType(List<Color> colors){
		this.colors = colors;
		users = new ArrayList<Object>();
	}
	
	public List<Color> getColors(){
		return colors;
	}
	
	public boolean usedBy(Object o){
		return users.contains(o);
	}
	
	public void addUser(Object o){
		users.add(o);
	}
	public boolean isUsed(){
		return !users.isEmpty();
	}
	public void removeUser(Object o){
		users.remove(o);
	}
}

public class ColorUtil {
	
	private List<ColorType> colorTypes;
	
	private static ColorUtil instance = null;
	
	private ColorUtil(){
		colorTypes = new ArrayList<ColorType>();
		
		List<Color> reds = new ArrayList<Color>();
		reds.add(new Color("#DF6B62"));
		reds.add(new Color("#E77E76"));
		reds.add(new Color("#E78A83"));
		reds.add(new Color("#B8463D"));
		colorTypes.add(new ColorType(reds));
		
		List<Color> blues = new ArrayList<Color>();
		blues.add(new Color("#496594"));
		blues.add(new Color("#617EAF"));
		blues.add(new Color("#6A83AF"));
		blues.add(new Color("#2D4A7A"));
		colorTypes.add(new ColorType(blues));
		
		List<Color> greens = new ArrayList<Color>();
		greens.add(new Color("#5AB750"));
		greens.add(new Color("#70C966"));
		greens.add(new Color("#7AC972"));
		greens.add(new Color("#3B9732"));
		colorTypes.add(new ColorType(greens));
		
		List<Color> yellows = new ArrayList<Color>();
		yellows.add(new Color("#DFD862"));
		yellows.add(new Color("#E7E176"));
		yellows.add(new Color("#E7E183"));
		yellows.add(new Color("#B8B13D"));
		colorTypes.add(new ColorType(yellows));
		
		List<Color> violets = new ArrayList<Color>();
		violets.add(new Color("#A2478E"));
		violets.add(new Color("#B95FA5"));
		violets.add(new Color("#B969A8"));
		violets.add(new Color("#862C72"));
		colorTypes.add(new ColorType(violets));
		
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
		for (ColorType colorType : colorTypes) {
			if( colorType.usedBy(o))
				return;
		}
		int i = getFreeColorType();
		colorTypes.get(i).addUser(o);
	}
	
	public void setColor(Object colorTypeUser, Object o){
		for (ColorType ct : colorTypes) {
			if(ct.usedBy(colorTypeUser)){
				for (Color c : ct.getColors()) {
					if(c.usedBy(o))
						return;
				}
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
				if(c.usedBy(src) && !c.usedBy(o)){
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
	
	public String getColorFromColorTypeUser(Object o){
		for (ColorType ct : colorTypes) {
			if( ct.usedBy(o))
				return ct.getColors().get(0).getName();
		}
		return null;
	}
	
	public void removeColorType(Object o){
		for (ColorType colorType : colorTypes) {
			if(colorType.usedBy(o)){
				for (Color color : colorType.getColors()) {
					color.clearUsers();
				}
				colorType.removeUser(o);
				return;
			}
		}
	}
	
	public void removeColor(Object o){
		for (ColorType colorType : colorTypes) {
			for (Color color : colorType.getColors()) {
				if(color.usedBy(o)){
					color.removeUser(o);
					return;
				}
			}
		}
	}

	public static void reset() {
		instance = new ColorUtil();
	}
}