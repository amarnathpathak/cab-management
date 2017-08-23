package com.cab.services;

import java.util.HashMap;
import java.util.Map;

import com.cab.listner.InIdleStateListner;
import com.cab.listner.OnBreakStateListner;
import com.cab.listner.OnTripStateListner;
import com.cab.listner.ToBookedStateListner;
import com.cab.model.CabState;

public class CamMangmentStart {
	public static void main(String[] args) throws InterruptedException {
		CabMangment cms = new CabMangementServices();
		CabState.ONTRIP.registerListener(new OnTripStateListner());
		CabState.BOOKED.registerListener(new ToBookedStateListner());
		CabState.IDLE.registerListener(new InIdleStateListner());
		CabState.BREAK.registerListener(new OnBreakStateListner());

		cms.onBoardCity("110030", "Delhi");
		//cms.onBoardCity("110031", "Chennai");
		cms.onBoardCity("110032", "Punjab");
		cms.showSystemStatus();

		Map<Integer, Integer> map = new HashMap<>();
		map.keySet();
		int[][] res = new int[5][];
		cms.registerCab("121", "Toyota1", "110030", "Delhi");
		cms.registerCab("122", "Toyota2", "110031", "Chennai");
		//cms.registerCab("123", "Toyota3", "110031", "Chennai");
		/*cms.registerCab("124", "Toyota4", "110031", "Chennai");
		cms.registerCab("125", "Toyota5", "110032", "Punjab");
		cms.registerCab("126", "Toyota6", "110032", "Punjab");
		cms.registerCab("127", "Toyota7", "110032", "Punjab");
		cms.registerCab("128", "Toyota8", "110030", "Delhi");
		cms.registerCab("129", "Toyota9", "110030", "Delhi");
		cms.registerCab("130", "Toyota10", "110040", "Bangalore");*/
		cms.showSystemStatus();

		cms.changeCabCurrentCity("121", "110032");
		cms.showSystemStatus();
		String tripId = cms.requestCab("110030", "110031");
		cms.showSystemStatus();
		cms.startTrip(tripId);
		cms.showSystemStatus();
		
		Thread t =new Thread();
		t.sleep(10000);
		cms.finishTrip(tripId);
		cms.showSystemStatus();

	}

}
