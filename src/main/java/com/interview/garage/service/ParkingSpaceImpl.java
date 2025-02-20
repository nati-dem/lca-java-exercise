package com.interview.garage.service;

import com.interview.garage.model.Vehicle;
import com.interview.garage.model.VehicleSize;

public class ParkingSpaceImpl implements ParkingSpace{
	
	private long spaceId;
	private Vehicle vehicle;
	private VehicleSize spaceSize;
	
	public ParkingSpaceImpl(long spaceId, VehicleSize spaceSize){
		this.spaceId = spaceId;
		this.spaceSize = spaceSize;
	}

	@Override
	public boolean isOccupied() {
		return vehicle != null;
	}

	@Override
	public long park(Vehicle v) {
		if(isOccupied())
			throw new IllegalArgumentException("Parking space already occupied");
		
		if(v.getSize() == VehicleSize.Full && isCompact())
			throw new IllegalArgumentException("Can not park fullsize Vehicle in compact space");
		
		this.vehicle = v;
		return spaceId;
	}

	@Override
	public Vehicle retrieveVehicle() {
		Vehicle v1 = vehicle;
		vehicle = null;
		return v1;
	}

	@Override
	public boolean isCompact() {
		return spaceSize == VehicleSize.Compact;
	}

	@Override
	public String toString() {
		return "ParkingSpaceImpl [spaceId=" + spaceId + ", vehicle=" + vehicle + ", spaceSize=" + spaceSize + "]";
	}

}
