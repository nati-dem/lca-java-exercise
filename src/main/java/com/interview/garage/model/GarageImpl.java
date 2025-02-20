package com.interview.garage.model;


public class GarageImpl implements Garage {
	
	private int compactSpaces;
	private int fullSizeSpaces;
	
	public GarageImpl(int compactSpaces, int fullSizeSpaces) {
		super();
		this.compactSpaces = compactSpaces;
		this.fullSizeSpaces = fullSizeSpaces;
	}
	
	public int getCompactSpaces() {
		return compactSpaces;
	}

	public int getFullSizeSpaces() {
		return fullSizeSpaces;
	}
	
	public void setCompactSpaces(int compactSpaces) {
		this.compactSpaces = compactSpaces;
	}

	public void setFullSizeSpaces(int fullSizeSpaces) {
		this.fullSizeSpaces = fullSizeSpaces;
	}

	@Override
	public String getGarageState() {
		return "Garage state: compactSpaces::" + this.compactSpaces + " , fullSizeSpaces::" + fullSizeSpaces;
	}

}
