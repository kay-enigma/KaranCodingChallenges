package oosequence;

import java.util.Date;

public class Flight {

	private Date departure;
	private Date arrival;

	public Flight(Date givenDep, Date givenArr) {
		if (givenDep == null&& givenArr == null) {
			this.departure=givenDep;
			this.arrival=givenArr;
		}
		
		else if (givenDep == null&& givenArr != null) {
			this.departure = givenDep;
			this.arrival = new Date(givenArr.getTime());   
		}
		else if (givenDep != null&& givenArr == null) {
			this.departure = new Date(givenDep.getTime());
			this.arrival = givenArr;   
		}
		
		else if (givenDep.before(givenArr)) {
			setDeparture(givenDep);
			setArrival(givenArr);
			
			this.departure = getDeparture();
			this.arrival = getArrival();
		}
	}
	
	public Date getDeparture() {
		if(departure!=null) return new Date(departure.getTime());
		return departure;
	}
	public Date getArrival() {
		if(arrival!=null) return new Date(arrival.getTime());
		return arrival;
	}
	public void setDeparture(Date date) {	
		if(date == null || arrival == null)this.departure = date;

		else if (date.before(arrival)) {
			this.departure = date;
		}
	} 
	public void setArrival(Date date) { 
		if(date == null || departure ==null)this.arrival = date;
		
		else if (departure.before(date)) {
			this.arrival = date;
		}
	}
	public Flight(Flight c) {
		this.departure = c.departure;
		this.arrival = c.arrival;
	}
	public long length() {
		if(departure==null) return 0;
		if(departure!=null & arrival!=null ) {
			long departTime = this.departure.getTime();
			long arriveTime = this.arrival.getTime();
			long diff = (arriveTime-departTime)/60000;
			return diff;
		}		
		return 0;
	}
}