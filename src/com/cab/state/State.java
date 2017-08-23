package com.cab.state;

public interface State {
	Integer[] transisitions();

	void registerListener(Listener listener);

	Listener listener();

}
