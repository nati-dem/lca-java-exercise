package com.interview.garage.model;

public interface Garage {

	/**
	 * Returns a string representation of the current state of the garage e.g. How many spaces are available/occupied.
	 */
	String getGarageState();
	
	/**
	 * Returns back the total # of Compact Spaces
	 */
	public int getCompactSpaces();

	/**
	 * Returns back the total # of Full Spaces
	 */
	public int getFullSizeSpaces();
	
	/**
	 * Set # of Compact size parking spaces
	 */
	public void setCompactSpaces(int compactSpaces);

	/**
	 * Set # of Full size parking spaces
	 */
	public void setFullSizeSpaces(int fullSizeSpaces);
	
}
