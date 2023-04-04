package oosequence;

import java.util.Date;

public class Flight {

    public Date departure;
    public Date arrival;

    public Flight(Date departure, Date arrival) {
        if (departure == null || arrival == null || departure.before(arrival)) {
            this.departure = departure;
            this.arrival = arrival;
        }
    }

    public Flight(Flight flight) {
        this(flight.departure, flight.arrival);
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        if (departure == null || this.arrival == null || departure.before(this.arrival)) {
            this.departure = departure;
        }
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        if (arrival == null || this.departure == null || this.departure.before(arrival)) {
            this.arrival = arrival;
        }
    }

    public long length() {
        if (departure != null && arrival != null) {
            long duration = arrival.getTime() - departure.getTime();
            return (long) Math.floor(duration / (1000.0 * 60.0));
        }
        return 0;
    }

}