package flight;

public class Passenger {

    private String name;
    private String flightId;
    private  String destination;
    private int seatNumber;

    public Passenger (String name, String flightId, String destination, int seatNumber) {
        this.name = name;
        this.flightId = flightId;
        this.destination = destination;
        this.seatNumber = seatNumber;
    }

    public String getDestination () {
        return destination;
    }

    public String getFlightId () {
        return flightId;
    }

    public String getName () {
        return name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
