package oosequence;

import java.util.ArrayList;

public class Itinerary {
    private String name;
    private ArrayList<Flight> flights;

    public Itinerary(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        int index = 0;
        for (Flight f : flights) {
            if (flight.getDeparture().before(f.getArrival())) {
                break;
            }
            index++;
        }
        flights.add(index, flight);
    }
    

    public int getTotalLayover() {
        int totalLayover = 0;
        for (int i = 1; i < flights.size(); i++) {
            Flight previousFlight = flights.get(i - 1);
            Flight currentFlight = flights.get(i);
            long layoverInMilliseconds = currentFlight.getDeparture().getTime() - previousFlight.getArrival().getTime();
            totalLayover += layoverInMilliseconds / 60000;
        }
        return totalLayover;
    }
}