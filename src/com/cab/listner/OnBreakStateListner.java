package com.cab.listner;

import java.util.Map;

import com.cab.model.CabState;
import com.cab.state.Listener;

public class OnBreakStateListner implements Listener {

	@Override
	public void init() {
		CabState.BREAK.registerListener(new OnBreakStateListner());

	}

	@Override
	public void onStateChange(Map<String, Object> payload, CabState source) {
		String cabId = (String) payload.get("cabId");
		String cityId = (String) payload.get("cityId");

	}

}
