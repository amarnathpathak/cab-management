package com.cab.services;

import java.util.Date;

import com.cab.model.City;

public interface CabMangment {
	public City onBoardCity(String cityId, String name);
	public void registerCab(String cabId, String cabName, String cityId, String cityName);
	public void changeCabCurrentCity(String cabId, String cityId);
	public String requestCab(String sourceCityId, String destCityId);
	public void startTrip(String tripId);
	public void finishTrip(String tripId);
	public void showSystemStatus();

}
