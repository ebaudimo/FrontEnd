package com.pedEdt.frontEnd.client.model;

public enum TeachingType {
	COURS("COURS"),
	EI("EI"),
	TD("TD"),
	TP("TP");
	
	private final String stringValue;
	
	private TeachingType() {
		stringValue = "";
	}
	
	private TeachingType(final String s) {
		stringValue = s;
	}
	
	public String toString() {
		return stringValue;
	}
}
