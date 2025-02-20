package com.interview.garage;

import com.interview.garage.model.Garage;
import com.interview.garage.model.GarageImpl;
import com.interview.garage.model.Ticket;
import com.interview.garage.model.Vehicle;
import com.interview.garage.model.VehicleImpl;
import com.interview.garage.model.VehicleSize;
import com.interview.garage.service.GarageUtil;
import com.interview.garage.service.GarageUtilImpl;

public class GarageAppDriver {

	/**
	 * Driver Program to exercise Garage Class
	 * 
	 * 1) Create Instance of Garage class and print out it's state
	 * 2) Create a couple car instances of different sizes and park them
	 * 3) Print out state of garage
	 * 4) retrieve previously parked cars
	 * 5) Print out state of garage
	 */
	public static void main(String[] args) {
		
		//Initialize Garage
		Garage garage = new GarageImpl(10, 20);
		
		GarageUtil garageUtil = new GarageUtilImpl(garage);
		
		System.out.println(garage.getGarageState());
		
		//Init a few Vehicles of different sizes;
		Vehicle v1 = new VehicleImpl("ABC4801", "TX", VehicleSize.Compact);
		Vehicle v2 = new VehicleImpl("EFG4422", "NC", VehicleSize.Full);
		Vehicle v3 = new VehicleImpl("DDD5555", "TX", VehicleSize.Compact);
		System.out.printf("Vehicles to park: v1: %s, v2: %s, V3: %s", v1, v2, v3);

		Ticket t1 = garageUtil.park(v1);
		Ticket t2 = garageUtil.park(v2);
		Ticket t3 = garageUtil.park(v3);
		System.out.printf("\nTickets of parked vehicles: t1: %s, t2: %s, t3: %s", t1, t2, t3);
		
		System.out.println(garage.getGarageState());
		
		Vehicle vv1 = garageUtil.retrieveVehicle(t1);
		Vehicle vv2 = garageUtil.retrieveVehicle(t2);
		Vehicle vv3 = garageUtil.retrieveVehicle(t3);
		System.out.printf("\nRetrieved vehicles: v1: %s, v2: %s, v3: %s", vv1, vv2, vv3);
		
		System.out.println(garage.getGarageState());
		
	}
}
