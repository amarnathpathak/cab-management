package com.cab.model;

import com.cab.state.Listener;
import com.cab.state.State;

public enum CabState implements State {
	IDLE(0, 1, 3), BOOKED(2), ONTRIP(0), BREAK(0);
	// if there will be multiple states then multiple transition is supported.
	// for now we are supporting 2 states only;
	private Integer[] transisitions;
	private Listener listener;

	private CabState(Integer... transisitions) {
		this.transisitions = transisitions;
	}

	@Override
	public Integer[] transisitions() {
		return transisitions;
	}

	@Override
	public void registerListener(Listener listener) {
		this.listener = listener;
	}

	@Override
	public Listener listener() {
		return listener;
	}

}
