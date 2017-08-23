package com.cab.dao;

import java.util.Collection;

import com.cab.model.Cab;
import com.cab.model.City;
import com.cab.model.Trip;

public interface CabMangDao {
	Cab registerCab(Cab cab);
	City registerCity(City city);
	Cab getCab(String cabId);
	Trip getTrip(String tripId);
	City getCity(String cityId);
	Collection<Cab> getAllCabs();
	Collection<City> getAllCity();
	Collection<Trip> getAllRunningTrip();
	Collection<Cab> getIdealCabs(String city);
	Cab updateCab(Cab cab);
	Trip updateTrip(Trip trip);
	void addTrip(Trip trip);

}
