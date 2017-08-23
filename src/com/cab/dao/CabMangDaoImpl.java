package com.cab.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.cab.model.Cab;
import com.cab.model.CabState;
import com.cab.model.City;
import com.cab.model.Trip;

public class CabMangDaoImpl implements CabMangDao {
	private static Map<String, City> onBoardCities = new HashMap<>();
	private static Map<String, Cab> onBoardCabs = new HashMap<>();
	private static Map<String, Trip> runningTrip = new HashMap<>();

	@Override
	public Cab registerCab(Cab cab) {
		onBoardCabs.put(cab.getCabId(), cab);
		return cab;

	}

	@Override
	public City registerCity(City city) {
		onBoardCities.put(city.getCityId(), city);
		return city;
	}

	@Override
	public Cab getCab(String cabId) {
		return onBoardCabs.get(cabId);
	}

	@Override
	public City getCity(String cityId) {
		return onBoardCities.get(cityId);
	}

	@Override
	public Collection<Cab> getAllCabs() {
		return onBoardCabs.values();
	}

	@Override
	public Collection<City> getAllCity() {
		return onBoardCities.values();

	}

	@Override
	public void addTrip(Trip trip) {
		runningTrip.put(trip.getTripNumber(), trip);

	}
	@Override
	public Collection<Trip> getAllRunningTrip() {
		return runningTrip.values();

	}

	@Override
	public Trip getTrip(String tripId) {
		return runningTrip.get(tripId);
	}

	@Override
	public Collection<Cab> getIdealCabs(String cityId) {
		return getAllCabs().stream().filter(cab -> cab.getCurrentCity().getCityId().
				equals(cityId)).filter(cab -> cab.getState().
				equals(CabState.IDLE)).collect(Collectors.toList());
	}

	@Override
	public Cab updateCab(Cab cab) {
		// TODO Auto-generated method stub
		return cab;
	}

	@Override
	public Trip updateTrip(Trip trip) {
		// TODO Auto-generated method stub
		return trip;
	}

}
