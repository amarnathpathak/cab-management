package com.cab.services;

import java.util.Collection;
import java.util.Date;
import java.util.PriorityQueue;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.Cab;
import com.cab.model.Trip;

public class FindCabStrategies {

	private CabMangDao cabMangDao = new CabMangDaoImpl();

	public enum Strategy {
		MOST_IDEAL_CAB, MIN_TRIP_CAB;
	}

	Cab getCab(Strategy str, String city) {
		Cab cab = null;
		switch (str) {
		case MOST_IDEAL_CAB:
			cab = getMostIdealCab(city);
			break;
		case MIN_TRIP_CAB:
			cab = getMinTripCab(city);
			break;
		default:
			cab = getMinTripCab(city);
			break;

		}
		return cab;
	}

	private Cab getMinTripCab(String city) {
		PriorityQueue<Cab> q = new PriorityQueue<>();
		Collection<Cab> cabs = cabMangDao.getIdealCabs(city);
		int minTripCount = Integer.MAX_VALUE;
		Cab minTripCab = null;
		for (Cab cab : cabs) {
			int cabTripCount = cab.getTrips().size();
			if (cabTripCount < minTripCount) {
				minTripCount = cabTripCount;
				minTripCab = cab;
			}
		}
		return minTripCab;

	}

	private Cab getMostIdealCab(String city) {
		Collection<Cab> cabs = cabMangDao.getIdealCabs(city);
		Date date = new Date();
		Cab mostIdealCab = null;
		for (Cab cab : cabs) {
			Trip trip = cab.getLastTrip();
			if (trip == null) {
				return cab;
			}
			Date endDate = trip.getEndTime();
			if (date.compareTo(endDate) >= 0) {
				date = endDate;
				mostIdealCab = cab;
			}
		}
		return mostIdealCab;
	}

}
