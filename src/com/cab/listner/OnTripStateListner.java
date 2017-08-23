package com.cab.listner;

import java.util.Date;
import java.util.Map;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.CabState;
import com.cab.model.Trip;
import com.cab.state.Listener;

public class OnTripStateListner implements Listener {
	private CabMangDao cabMangDao = new CabMangDaoImpl();

	@Override
	public void init() {
		CabState.ONTRIP.registerListener(new OnTripStateListner());
    }
	@Override
	public void onStateChange(Map<String, Object> payload, CabState source) {
		String tripId = (String) payload.get("tripId");
		Trip trip = cabMangDao.getTrip(tripId);
		trip.setTripComplete(false);
		trip.setStartTime(new Date());
		cabMangDao.updateTrip(trip);

	}

}
