package flight;

import java.util.ArrayList;
import java.util.HashMap;

public class Plane {

    private String planeId;
    private int seatAmount;
    private String flightID;
    private String destination;
    private HashMap<Integer, String> passengersList;
    private ArrayList<String> passengersWithoutSeats;

    public Plane (String planeId, int seatAmount) {
        this.planeId = planeId;
        this.seatAmount = seatAmount;
        flightID = "";
        destination = "";
        passengersList = new HashMap<Integer, String>();
        passengersWithoutSeats = new ArrayList<>();
    }

    public void findFreeSeatInThisPlane(HashMap<Integer, String> passengersList, ArrayList<String> passengersWithoutSeats) {
        for (int i = 0; i < getSeatAmount(); i++) {
            if (!passengersList.containsKey(i) && !passengersWithoutSeats.isEmpty()) {
                passengersList.put(i, passengersWithoutSeats.get(0));
                System.out.println("A free seat for passenger " + getPassengersList().get(i) + " on the flight " + getFlightID() + " to " + getDestination() + " is founded");
                passengersWithoutSeats.remove(0);
            }
        }
    }

    public String getPlaneID () {
        return planeId;
    }

    public void setFlightID (String flightID) {
        this.flightID = flightID;
    }

    public String getFlightID () {
        return flightID;
    }

    public void setDestination (String destination) {
        this.destination = destination;
    }

    public String getDestination () {
        return destination;
    }

    public int getSeatAmount () {
        return seatAmount;
    }

    public HashMap<Integer, String> getPassengersList () {
        return passengersList;
    }

    public ArrayList<String> getPassengersWithoutSeats() {
        return passengersWithoutSeats;
    }
}
