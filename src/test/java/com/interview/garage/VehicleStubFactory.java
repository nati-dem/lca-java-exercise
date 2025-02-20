package com.interview.garage;

import com.interview.garage.model.Vehicle;
import com.interview.garage.model.VehicleImpl;
import com.interview.garage.model.VehicleSize;

/**
 * Stub factory that to easily get new Vehicle instances
 */
public class VehicleStubFactory {
	
	private static String licensePlateNum = "TES1234";
	private static String licensePlateState = "NC";
	
	private static Vehicle buildVehicleOfSize(VehicleSize size) {
		return new VehicleImpl(licensePlateNum, licensePlateState, size);
	}

	private static Vehicle buildVehicleOfSize(String licensePlateNum, 
			String licensePlateState, VehicleSize size) {
		return new VehicleImpl(licensePlateNum, licensePlateState, size);
	}
	
	public static Vehicle buildCompactVehicle() {
		return buildVehicleOfSize(licensePlateNum, licensePlateState, VehicleSize.Compact);
	}

	public static Vehicle buildFullSizeVehicle() {
		return buildVehicleOfSize(licensePlateNum, licensePlateState, VehicleSize.Full);
	}
	
}
