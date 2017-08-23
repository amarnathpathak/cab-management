package com.cab.model;

import java.util.Date;

public class Trip {
	private String tripNumber;
	private City source;
	private City destination;
	private Cab cab;
	private Date startTime;
	private Date endTime;
	private boolean tripComplete;

	public Trip(String tripNumber, City source, City destination, Cab cab) {
		super();
		this.tripNumber = tripNumber;
		this.source = source;
		this.destination = destination;
		this.cab = cab;
	}

	public String getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}

	public City getSource() {
		return source;
	}

	public void setSource(City source) {
		this.source = source;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isTripComplete() {
		return tripComplete;
	}

	public void setTripComplete(boolean tripComplete) {
		this.tripComplete = tripComplete;
	}

	@Override
	public String toString() {
		return "Trip [tripNumber=" + tripNumber + ", source=" + source.getCityId() + ", destination=" + destination.getCityId() + ", startTime="
				+ startTime + ", endTime=" + endTime + ", tripStart=" + tripComplete + "]";
	}

	

}
