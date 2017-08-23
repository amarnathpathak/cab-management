package com.cab.state;

import java.util.Map;

import com.cab.model.CabState;

public interface Listener {
	void init();
	void onStateChange(Map<String, Object> payload, CabState source);
}
