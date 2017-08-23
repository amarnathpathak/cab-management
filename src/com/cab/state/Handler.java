package com.cab.state;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.cab.dao.CabMangDao;
import com.cab.dao.CabMangDaoImpl;
import com.cab.model.CabState;

public class Handler {
	private CabMangDao cabMangDao = new CabMangDaoImpl();
	public void handle(String cabId, Map<String, Object> payload, CabState source, CabState target) {

		if (Arrays.asList(source.transisitions()).contains(target.ordinal())) {
			target.listener().onStateChange(payload, source);
			cabMangDao.getCab(cabId).setState(target);
			System.out.println("Cab " + cabId + " State changed from" + source + " to " + target);
		} else {
			throw new IllegalArgumentException("Cab State cannot be changed from" + source + " to " + target);
		}
	}

}
