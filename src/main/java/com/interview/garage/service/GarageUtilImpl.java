package com.interview.garage.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.interview.garage.model.Garage;
import com.interview.garage.model.Ticket;
import com.interview.garage.model.TicketImpl;
import com.interview.garage.model.Vehicle;
import com.interview.garage.model.VehicleSize;

public class GarageUtilImpl implements GarageUtil {

	Garage garage;
	Map<Long, ParkingSpace> compactParkingSpaces = new ConcurrentHashMap<>();
	Map<Long, ParkingSpace> fullParkingSpaces = new ConcurrentHashMap<>();
	
	public GarageUtilImpl(Garage garage) {
		super();
		this.garage = garage;
		initilizeGarageSpaces();
	}
	
	private void initilizeGarageSpaces() {
		long spaceId = 1;
		for(int i=1; i<=this.garage.getCompactSpaces(); i++, spaceId++) {
			compactParkingSpaces.put(spaceId, new ParkingSpaceImpl(spaceId, VehicleSize.Compact));
		}
		for(int i=1; i<=this.garage.getFullSizeSpaces();i++, spaceId++) {
			fullParkingSpaces.put(spaceId, new ParkingSpaceImpl(spaceId, VehicleSize.Full));
		}
	}
	
	@Override
	public Ticket park(Vehicle v) {

		if(v.getSize() == VehicleSize.Compact) {
			
			for(ParkingSpace compactParkingSpace: compactParkingSpaces.values()) {
				if(!compactParkingSpace.isOccupied()) {
					this.garage.setCompactSpaces(this.garage.getCompactSpaces()-1); 
					return new TicketImpl(compactParkingSpace.park(v));
				}
			}
		}
		for(ParkingSpace fullParkingSpace: fullParkingSpaces.values()) {
			if(!fullParkingSpace.isOccupied()) {
				this.garage.setFullSizeSpaces(this.garage.getFullSizeSpaces()-1); 
				return new TicketImpl(fullParkingSpace.park(v));
			}
		}
		throw new IllegalStateException("No space available");
	}

	@Override
	public Vehicle retrieveVehicle(Ticket t) {
		if(compactParkingSpaces.containsKey(t.getSpaceId())) {
			this.garage.setCompactSpaces(this.garage.getCompactSpaces()+1);
			return compactParkingSpaces.get(t.getSpaceId()).retrieveVehicle();
		}
		if(fullParkingSpaces.containsKey(t.getSpaceId())) {
			this.garage.setFullSizeSpaces(this.garage.getFullSizeSpaces()+1); 
			return fullParkingSpaces.get(t.getSpaceId()).retrieveVehicle();
		}
		throw new IllegalStateException("No Vehicle found for given ticket");
	}
	
	@Override
	public int totalSpacesAvailable() {
		return this.garage.getFullSizeSpaces() + this.garage.getCompactSpaces();
	}

	@Override
	public int spacesAvailableByType(VehicleSize size) {
		return size == VehicleSize.Compact ? 
				this.garage.getCompactSpaces(): this.garage.getFullSizeSpaces();
	}
	
}
