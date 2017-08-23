package com.cab.listner;

import java.util.Map;
import java.util.UUID;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.Cab;
import com.cab.model.CabState;
import com.cab.model.City;
import com.cab.model.Trip;
import com.cab.state.Listener;

public class ToBookedStateListner implements Listener{
	private CabMangDao cabMangDao = new CabMangDaoImpl();

	@Override
	public void init() {
		CabState.BOOKED.registerListener(new ToBookedStateListner());
		
	}

	@Override
	public void onStateChange(Map<String, Object> payload, CabState source) {
		String cabId = (String) payload.get("cabId");
		String srcCityId = (String) payload.get("srcCityId");
		String descCityId = (String) payload.get("descCityId");
		Cab cab = cabMangDao.getCab(cabId);
		City srcCity = cabMangDao.getCity(srcCityId);
		City desCity = cabMangDao.getCity(descCityId);
		Trip trip = new Trip(UUID.randomUUID().toString(), srcCity, desCity, cab);
		cab.addTrip(trip);
		cabMangDao.addTrip(trip);
		cabMangDao.updateCab(cab);
		
	}
}
