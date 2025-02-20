package com.interview.garage.model;

public class TicketImpl implements Ticket {
	
	private long spaceId;
	
	public TicketImpl(long spaceId) {
		this.spaceId = spaceId;
	}

	@Override
	public long getSpaceId() {
		return spaceId;
	}

	@Override
	public String toString() {
		return "TicketImpl [spaceId=" + spaceId + "]";
	}
	
}
