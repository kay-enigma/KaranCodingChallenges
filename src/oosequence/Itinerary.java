package oosequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class Itinerary {
	private String name;
	private ArrayList<Flight> flightlist;
	
	public Itinerary(String givenName) {
	name=givenName;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Flight> getFlightList() {
		return flightlist;
	}
	public void addFlight(Flight m) {
		ArrayList<Flight> temp= new ArrayList<Flight>();
		Flight tempFlight = new Flight(m);
		
		if(flightlist==null)	{	
			temp.add(tempFlight);
			flightlist=temp;
		}
		if(flightlist.size()<6&&flightlist.get(0)!=tempFlight) {
			flightlist.add(tempFlight);
			for (int i = 0; i < flightlist.size(); i++) {
				Date firstDate=flightlist.get(i).getDeparture();
				for (int j = flightlist.size() - 1; j > i; j--) {
		        	  Date lastDate=flightlist.get(j).getDeparture();
		              if (firstDate.after(lastDate)) {
		                  Flight tmp = flightlist.get(i);
		                  flightlist.set(i,flightlist.get(j)) ;
		                  flightlist.set(j,tmp);
		              }
		        }
		   }	
		}
	}
	public long getTotalLayover() {
		long layover=0;
		if(flightlist==null) return 0;

		if(flightlist.size()>1) {
			long firstArr=0;
			long  nextDep=0;
			for (int i=0; i<flightlist.size();i++) {
				firstArr=flightlist.get(i).getArrival().getTime();  		
				if(i+1<flightlist.size()) {	
					nextDep=flightlist.get(i+1).getDeparture().getTime();		
				
					layover = layover+ ((nextDep-firstArr)/60000);
				}
			}
			return layover;	
				
		}
			
			
		return 0;
	}
	
	

}
