package com.cab.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.Cab;
import com.cab.model.CabState;
import com.cab.model.City;
import com.cab.model.Trip;
import com.cab.services.FindCabStrategies.Strategy;
import com.cab.state.Handler;

public class CabMangementServices implements CabMangment {

	private Handler handler = new Handler();
	private CabMangDao cabMangDao = new CabMangDaoImpl();
	private FindCabStrategies cabStrategy = new FindCabStrategies();

	@Override
	public City onBoardCity(String cityId, String name) {
		City city = cabMangDao.getCity(cityId);
		if (city != null) {
			System.out.println("City Already on board");
		} else {
			city = cabMangDao.registerCity(new City(cityId, name));
		}
		return city;

	}

	@Override
	public void registerCab(String cabId, String cabName, String cityId, String cityName) {
		if (cabMangDao.getCab(cabId) != null) {
			System.out.println("Cab Already on board");
		} else {
			Cab cab = new Cab(cabId, cabName);
			City city = cabMangDao.getCity(cityId);
			if (city == null) {
				city = onBoardCity(cityId, cityName);

			}
			cab.setCurrentCity(city);
			cabMangDao.registerCab(cab);
		}

	}

	@Override
	public void changeCabCurrentCity(String cabId, String cityId) {
		Cab cab = cabMangDao.getCab(cabId);
		City city = cabMangDao.getCity(cityId);
		if (cab == null) {
			System.out.println("Cab does not exist");
			return;
		}
		if (city == null) {
			System.out.println("City does not exist");
			return;
		}
		try {
			Map<String, Object> data = new HashMap<>();
			data.put("cabId", cab.getCabId());
			data.put("cityId", cityId);
			handler.handle(cab.getCabId(), data, cab.getState(), CabState.IDLE);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public String requestCab(String sourceCityId, String destCityId) {
		System.out.println("Requesting cab from city : " + sourceCityId);
		Cab cab = cabStrategy.getCab(Strategy.MOST_IDEAL_CAB, sourceCityId);
		try {
			if (cab == null) {
				throw new RuntimeException("Cab does not exist");
			}
			Map<String, Object> data = new HashMap<>();
			data.put("cabId", cab.getCabId());
			data.put("srcCityId", sourceCityId);
			data.put("descCityId", destCityId);
			handler.handle(cab.getCabId(), data, cab.getState(), CabState.BOOKED);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return cab.getLastTrip().getTripNumber();

	}

	@Override
	public void startTrip(String tripNumber) {
		System.out.println("starting trip : " + tripNumber);
		Trip trip = cabMangDao.getTrip(tripNumber);
		try {
			if (trip == null) {
				throw new RuntimeException("trip does not exist");
			}
			Cab cab = trip.getCab();
			Map<String, Object> data = new HashMap<>();
			data.put("tripId", trip.getTripNumber());
			handler.handle(cab.getCabId(), data, cab.getState(), CabState.ONTRIP);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@Override
	public void finishTrip(String tripNumber) {
		System.out.println("completing trip : "+ tripNumber);
		Trip trip = cabMangDao.getTrip(tripNumber);
		try {
			if (trip == null) {
				throw new RuntimeException("trip does not exist");
			}
			Cab cab = trip.getCab();
			Map<String, Object> data = new HashMap<>();
			data.put("tripId", trip.getTripNumber());
			handler.handle(cab.getCabId(), data, cab.getState(), CabState.IDLE);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void showSystemStatus() {
		System.out.println("<- Supported Cities are - >");
		int count = 1;
		for (City city : cabMangDao.getAllCity()) {
			System.out.print(" " + count++ + ". " + city + "");
		}
		System.out.println();
		if (cabMangDao.getAllCabs().isEmpty()) {
			return;
		}
		count = 1;
		System.out.println("Registed Cab Status are");
		for (Cab cab : cabMangDao.getAllCabs()) {
			System.out.print(" " + count++ + ". " + cab + "");
		}
		System.out.println();
		if (cabMangDao.getAllRunningTrip().isEmpty()) {
			return;
		}
		count = 1;
		System.out.println("Registed Cab Status are");
		for (Trip trip : cabMangDao.getAllRunningTrip()) {
			System.out.print(" " + count++ + ". " + trip + "");
		}
	}

}
