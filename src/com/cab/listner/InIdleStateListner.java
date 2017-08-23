package com.cab.listner;

import java.util.Date;
import java.util.Map;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.Cab;
import com.cab.model.CabState;
import com.cab.model.Trip;
import com.cab.state.Listener;

public class InIdleStateListner implements Listener {

	private CabMangDao cabMangDao = new CabMangDaoImpl();

	@Override
	public void init() {
		CabState.IDLE.registerListener(new InIdleStateListner());
	}

	@Override
	public void onStateChange(Map<String, Object> payload, CabState source) {
		String tripId = (String) payload.get("cabId");
		Trip trip = cabMangDao.getTrip(tripId);
		Cab cab = trip.getCab();
		trip.setTripComplete(true);
		trip.setEndTime(new Date());
		cab.setCurrentCity(trip.getDestination());
		cabMangDao.updateCab(cab);
		cabMangDao.updateTrip(trip);
	}
}
