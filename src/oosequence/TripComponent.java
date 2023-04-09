package oosequence;

import java.util.Date;

public class TripComponent {

	private Date start;
	private Date end;

	public TripComponent(Date departure, Date arrival) {
		
		if (departure == null || arrival == null) {
			start = departure;
			end = arrival;   
		}
		
		if(departure != null && arrival != null) {
			
			if(departure.after(arrival)||departure.equals(arrival)) {
				start = departure;
				end = null;
			}
			else if(departure.before(arrival)){
				start = departure;
				end = arrival;
			}
		}

    }
	
	
	public TripComponent() {
		Date temp = new Date();
		start = temp;
		end=new Date(temp.getTime() + 3600000);	
	}

	public TripComponent(TripComponent c) {
		start=c.start;
		end=c.end;
	}

	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	
	public void setStart(Date date) {	
		if(date == null || end == null)
			start = date;
		else if (date.before(end))
			start = date;
	}
	
	public void setEnd(Date date) { 
		if(date == null || start ==null)
			end = date;
		else if(start.before(date))
			end = date;
	}

	public long lengthInSeconds() {
		long l;
        if (start!= null && end != null) {
        	long departTime = start.getTime();
			long arriveTime = end.getTime();
			l = (arriveTime-departTime)/1000;
        } else {
            l=0;
        }
        return l;
	}
}	