package com.interview.garage.model;

public class VehicleImpl implements Vehicle {
	
	private String licensePlateNumber;
	private String licensePlateState;
	private VehicleSize vehicleSize;
	
	public VehicleImpl(String licensePlate, String state, VehicleSize vehicleSize) {
		super();
		this.licensePlateNumber = licensePlate;
		this.licensePlateState = state;
		this.vehicleSize = vehicleSize;
	}

	@Override
	public VehicleSize getSize() {
		return this.vehicleSize;
	}
	
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public String getLicensePlateState() {
		return licensePlateState;
	}

	@Override
	public String toString() {
		return "VehicleImpl [licensePlateNumber=" + licensePlateNumber + ", licensePlateState=" + licensePlateState
				+ ", vehicleSize=" + vehicleSize + "]";
	}
	
}
