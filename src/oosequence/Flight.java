package oosequence;

import java.util.Date;

public class Flight extends TripComponent {

	private String departureAirport;
	private String arrivalAirport;
	
	public Flight(Date Start, Date End, String departure, String arrival) {
		// TODO Auto-generated constructor stub
		setDepartureAirport(departure);
		setStart(Start);
		departureAirport=getDepartureAirport();
		setArrivalAirport(arrival);
		setEnd(End);
		arrivalAirport=getArrivalAirport();
	}
	public Flight(Flight ch) {
		// TODO Auto-generated constructor stub
		super(ch);
		departureAirport=ch.departureAirport;
		arrivalAirport=ch.arrivalAirport;
	}
	public void setArrivalAirport(String givenAirport) {
	 	if (givenAirport==null)arrivalAirport="";	
	 	if (givenAirport!=null) {
	 		if (givenAirport.length()<3||givenAirport.length()>3) arrivalAirport="";
	 		else arrivalAirport=givenAirport;
	 	}
	}
	public void setDepartureAirport(String givenAirport) {
		 if (givenAirport==null)departureAirport="";
		 if (givenAirport!=null) {
			 if (givenAirport.length()<3||givenAirport.length()>3)departureAirport="";	
			 else departureAirport=givenAirport;
		 }
	}
	
	public String getArrivalAirport() {return arrivalAirport;}
	public String getDepartureAirport() {return departureAirport;}
	@Override
	public String getStart() {
		return departureAirport+" "+super.getStart();
		
	}
	@Override
	public String getEnd(){
		return arrivalAirport+" "+super.getEnd();
	}

}
