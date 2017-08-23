package com.cab.model;

import java.util.ArrayList;
import java.util.List;

public class Cab {
	private String cabId;
	private String name;
	private CabState state;
	private City currentCity;
	private List<Trip> trips;

	public Cab() {
		trips = new ArrayList<>();
	}

	public Cab(String cabId, String name) {
		this();
		this.cabId = cabId;
		this.name = name;
		this.state = CabState.IDLE;
	}

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CabState getState() {
		return state;
	}

	public void setState(CabState state) {
		this.state = state;
	}

	public City getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(City currentCity) {
		this.currentCity = currentCity;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public void addTrip(Trip trip) {
		this.trips.add(trip);
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + "state=" + state + ", currentCity=" + currentCity + "]";
	}

	public Trip getLastTrip() {
		if(trips.isEmpty()){
			return null;
		}
		return trips.get(trips.size() - 1);
	}

}
