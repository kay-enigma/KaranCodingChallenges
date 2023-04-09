package oosequence;

import java.util.Date;

public class Flight extends TripComponent {
	private String departureAirport="";
	private String arrivalAirport="";
	
	public void setDepartureAirport(String givenAirport) {
		 if (givenAirport==null)
			 departureAirport="";
		 if (givenAirport!=null) {
			 if (givenAirport.length()<3||givenAirport.length()>3)
				 departureAirport="";	
			 else 
				 departureAirport=givenAirport;
		 }
	}
	public void setArrivalAirport(String givenAirport) {
	 	if (givenAirport==null)arrivalAirport="";	
	 	if (givenAirport!=null) {
	 		if (givenAirport.length()<3||givenAirport.length()>3) arrivalAirport="";
	 		else arrivalAirport=givenAirport;
	 	}
	}
	public String getDepartureAirport() {
		return departureAirport;
		}

	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public String getDuration() {
		long durationInSeconds = super.lengthInSeconds();
        long durationInMinutes = durationInSeconds / 60;// Called in the superclass rather than duplicate code 
        return durationInMinutes + " minutes";

	}
}