package oosequence;

import java.util.Date;

public class TripComponent {

	private Date start;
	private Date end;

	public TripComponent(Date givenDeparture, Date givenArrival) {
		
		if (givenDeparture == null || givenArrival == null) {
			start = givenDeparture;
			end = givenArrival;   
		}
		
		if(givenDeparture != null && givenArrival != null) {
			
			if(givenDeparture.after(givenArrival)||givenDeparture.equals(givenArrival)) {
				start = givenDeparture;
				end = null;
			}
			else if(givenDeparture.before(givenArrival)){
				start = givenDeparture;
				end = givenArrival;
			}
		}
	}
	
	public TripComponent() {
		// TODO Auto-generated constructor stub
		Date temp = new Date();
		start = temp;
		end=new Date(temp.getTime() + 3600000);
		
		
	}

	public TripComponent(TripComponent c) {
		// TODO Auto-generated constructor stub
		start=c.start;
		end=c.end;
	}

	public String getStart() {
		if(start==null)return "";
		if(start!=null)return start.toString();
		return "";
	}
	public String getEnd() {
		if(end==null)return "";
		if(end!=null)return end.toString();
		return "";
	}
	public void setStart(Date date) {	
		if(date == null || end == null)start = date;

		else if (date.before(end))start = date;
	}
	public void setEnd(Date date) { 
		
		if(date == null || start ==null)end = date;
		
		else if (start.before(date))end = date;
	}

	protected long lengthInSeconds() {
		long diff = 0;
		if(start==null||end==null) return diff;
		if(start!=null & end!=null ) {
			long departTime = start.getTime();
			long arriveTime = end.getTime();
			return diff = (arriveTime-departTime)/1000;
		}
		return diff;			
	}
	public Boolean isBefore(TripComponent m) {
		
		if (this.end.getTime()<m.start.getTime()) {	
			return true;
		}
		return false;
		}
	
	public Boolean overlapsWith(TripComponent m) {
		if( !(start==null) && !(end==null) && !(m.start==null) && !(m.end==null)) {
		return !( this.isBefore(m));
		}
		//if(end.after(m.start)||m.start.after(start)&&m.end.before(end)||start.before(m.start)&&end.after(m.end))return true;
			
			//else if(start||m.start.after(end)&&m.end.after(end))return false;
		
		return false;
	}
}