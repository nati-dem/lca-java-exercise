package com.interview.garage.service;

import com.interview.garage.model.Vehicle;

public interface ParkingSpace {

	public boolean isOccupied();
	public long park(Vehicle v);
	public Vehicle retrieveVehicle();
	public boolean isCompact();

}