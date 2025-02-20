package com.interview.garage;


import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.interview.garage.model.GarageImpl;
import com.interview.garage.model.Ticket;
import com.interview.garage.model.Vehicle;
import com.interview.garage.model.VehicleSize;
import com.interview.garage.service.GarageUtil;
import com.interview.garage.service.GarageUtilImpl;

public class GarageUtilTest {

	private GarageUtil garage;
	
	private static final int INIT_COMPACT_SPACES = 3;
	private static final int INIT_FULLSIZE_SPACES = 3;
	private static final int INIT_TOTAL_SPACES = INIT_COMPACT_SPACES + INIT_FULLSIZE_SPACES;
	
	@BeforeEach
	public void setup() {
		//TODO - Initialize a tiny garage using the INIT_ sizes above
		this.garage = new GarageUtilImpl( new GarageImpl(INIT_COMPACT_SPACES, INIT_FULLSIZE_SPACES));
	}
	
	@Test
	public void testParkCompact() {
		
		Ticket t = garage.park(VehicleStubFactory.buildCompactVehicle());
		// Verify we get back a non-null ticket with a valid parking space #
		assertNotNull(t);
		assertTrue(t.getSpaceId() > 0);
		
		assertEquals(INIT_COMPACT_SPACES - 1, garage.spacesAvailableByType(VehicleSize.Compact));
		assertEquals(INIT_FULLSIZE_SPACES, garage.spacesAvailableByType(VehicleSize.Full));
		assertEquals(INIT_TOTAL_SPACES -1, garage.totalSpacesAvailable());
		
	}

	@Test
	public void testParkFullSize() {
		
		Ticket t = garage.park(VehicleStubFactory.buildFullSizeVehicle());
		// Verify we get back a non-null ticket with a valid parking space #
		assertNotNull(t);
		assertTrue(t.getSpaceId() > 0);
		
		assertEquals(INIT_COMPACT_SPACES, garage.spacesAvailableByType(VehicleSize.Compact));
		assertEquals(INIT_FULLSIZE_SPACES - 1, garage.spacesAvailableByType(VehicleSize.Full));
		assertEquals(INIT_TOTAL_SPACES -1, garage.totalSpacesAvailable());
		
	}

	@Test
	public void testParkForCompactOverflow() {
	
		// "Fill up" all compact spaces
		for (int i = 0; i < INIT_COMPACT_SPACES; i++) {
			garage.park(VehicleStubFactory.buildCompactVehicle());
		}
		// This shouldn't fail as compact should be able to "overflow" into full size spaces
		assertNotNull(garage.park(VehicleStubFactory.buildCompactVehicle()));
		
	}

	@Test()
	public void testParkForFullSizeOverflow() {
		
		// "Fill up" all compact spaces
		for (int i = 0; i < INIT_FULLSIZE_SPACES; i++) {
			garage.park(VehicleStubFactory.buildFullSizeVehicle());
		}

		// This *should* fail because the only spaces left are compact and we're trying to park a full size vehicle
		assertThatExceptionOfType(IllegalStateException.class)
			.isThrownBy(()->{garage.park(VehicleStubFactory.buildFullSizeVehicle());});
		
		
	}

	@Test()
	public void testParkForGarageFull() {
		
		// "Fill up" all compact spaces
		for (int i = 0; i < INIT_COMPACT_SPACES; i++) {
			garage.park(VehicleStubFactory.buildCompactVehicle());
		}

		// "Fill up" all fullsize spaces
		for (int i = 0; i < INIT_FULLSIZE_SPACES; i++) {
			garage.park(VehicleStubFactory.buildFullSizeVehicle());
		}
		
		// This should fail because there's no spaces left
		assertThatExceptionOfType(IllegalStateException.class)
			.isThrownBy(()->{garage.park(VehicleStubFactory.buildFullSizeVehicle());});
		
		
	}

	@Test
	public void testRetrieveVehicle() {
		
		Vehicle v = VehicleStubFactory.buildCompactVehicle();
		
		Ticket t = garage.park(v);
		
		int initialCompactSize = garage.spacesAvailableByType(VehicleSize.Compact);
		int initialTotalSize = garage.totalSpacesAvailable();
		
		// Make sure we get back the same vehicle we parked
		assertEquals(v, garage.retrieveVehicle(t));
		
		// After unparking a vehicle, counters should be incremented
		assertEquals(initialCompactSize + 1, garage.spacesAvailableByType(VehicleSize.Compact));
		assertEquals(initialTotalSize + 1, garage.totalSpacesAvailable());
		
	}


}
