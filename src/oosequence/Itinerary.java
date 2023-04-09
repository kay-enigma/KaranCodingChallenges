
package oosequence;


import java.util.ArrayList;



public class Itinerary {
	
	private String name;
	private ArrayList<TripComponent> flightlist=new ArrayList<TripComponent>();
	
	public Itinerary(String givenName) {
		name=givenName;
		flightlist=new ArrayList<TripComponent>();
	}
	
	public String getName() {return name;}
	
	public ArrayList<TripComponent> getTripComponents() {return flightlist;}
	
	public void addTripComponent(TripComponent trip) {
		if(flightlist.size()==0)	{		
			flightlist.add(trip);
			return;
		}
		
		else {
			for (int i = 0; i < flightlist.size(); i++) {
				if(trip.isBefore(flightlist.get(0))) {
					flightlist.add(0,trip);
					return;
				}
				if(flightlist.get(flightlist.size()-1).isBefore(trip)) {
					flightlist.add(trip);
					return;
				}
				if(flightlist.size()>1) {
					if(!(flightlist.get(i).overlapsWith(trip))) {
					if(!(trip.overlapsWith(flightlist.get(i+1)))) {
						flightlist.add(i+1,trip);
						return;
					}
				}
				}
			}	
		}
	}
	
	
	
	public String toString() {
		String temp="";
		String[] strArr = new String[flightlist.size()];
		for(int i=0;i<flightlist.size();i++) {
				if(i<6) {
				temp=+i+"\t"+flightlist.get(i).getStart().toString()+"\t"+flightlist.get(i).getEnd().toString()+"\n"; 
				strArr[i]=temp;
				}
			
		}
		 StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < strArr.length; i++) {
	         sb.append(strArr[i]);
	      }
		temp = sb.toString();
		System.out.println(name+"\n"+temp);
		return name+"\n"+temp;
		
	}
	
}
