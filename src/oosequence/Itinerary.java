package oosequence;


import java.util.ArrayList;

import java.util.Date;


public class Itinerary {
	
	private String flight_name;
	private ArrayList<Flight> List_flights;
	
	public Itinerary(String StringName) {
	   flight_name=StringName;
	}
	
	public String getName() {
		return flight_name;
	}
	
	public ArrayList<Flight> getFlights() {
		return List_flights;
	}
	
	public void addFlight(Flight added_flight) {
		ArrayList<Flight> temp = new ArrayList<Flight>();
		if(List_flights == null)	{	
			temp.add(added_flight);
			List_flights = temp;
		}
		if(List_flights.size() < 6 && List_flights.get(0) != added_flight) {
			List_flights.add(added_flight);
			for (int a = 0; a < List_flights.size(); a++) {
				Date firstDate = List_flights.get(a).getDeparture();
				
				for (int b = List_flights.size() - 1; b > a; b--) {
		        	  Date lastDate = List_flights.get(b).getDeparture();
		              if (firstDate.after(lastDate)) {
		                  Flight tmp = List_flights.get(a);
		                  List_flights.set(a,List_flights.get(b)) ;
		                  List_flights.set(b,tmp);
		              }
		        }
		   }	
		}
	}
	public long getTotalLayover() {
		long layover_dur = 0;
		if(List_flights == null) return 0;

		if(List_flights.size() > 1) {
			long F_Arrival = 0;
			long  N_Departure = 0;
			
			for (int a = 0; a < List_flights.size(); a++) {
				F_Arrival = List_flights.get(a).getArrival().getTime();  		
				if(a+1 < List_flights.size()) {	
					N_Departure = List_flights.get(a+1).getDeparture().getTime();		
					layover_dur = layover_dur + ((N_Departure-F_Arrival)/60000);
				}
			}return layover_dur;	
				
		}return 0;
	}

}
