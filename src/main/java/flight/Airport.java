package flight;

import java.util.ArrayList;

public class Airport {
    private ArrayList<Plane> airportPlanes;
    private String destination;
    private String flightId;
    private ArrayList<Passenger> registeredPassengers;

    public Airport(ArrayList<Plane> planes) {
        airportPlanes = planes;
        flightId = "";
        destination = "";
        registeredPassengers = new ArrayList<>();
    }

    public void registrationOfAircrafts(ArrayList<Plane> planes) {
        for (Plane p : planes) {
            if (p.getPlaneID().equals("Airbus")) {
                p.setFlightID("PS001");
                p.setDestination("London");
            } else if (p.getPlaneID().equals("Boeing")) {
                p.setFlightID("PS002");
                p.setDestination("Paris");
            } else if (p.getPlaneID().equals("Private Jet")) {
                p.setFlightID("PS003");
                p.setDestination("London");
            }
        }
    }

    public void boardingPassengers(ArrayList<Passenger> registrationList, Plane plane) {
        for (Passenger passenger : registrationList) {
            if (passenger.getFlightId().equals(plane.getFlightID())) {
                checkingSeats(passenger, plane);
            }
        }
    }

    public void checkingSeats(Passenger passenger, Plane plane) {
        if (plane.getPassengersList().isEmpty()) {
            plane.getPassengersList().put(passenger.getSeatNumber(), passenger.getName());
            System.out.println("Boarding completed for " + passenger.getName() + " on flight " + plane.getFlightID() + " to " + plane.getDestination());
        }
        else if (plane.getPassengersList().containsKey(passenger.getSeatNumber()) || passenger.getSeatNumber() >= plane.getSeatAmount()) {
            System.out.println("There is some problem with boarding for passenger " + passenger.getName());
            plane.getPassengersWithoutSeats().add(passenger.getName());
            plane.findFreeSeatInThisPlane(plane.getPassengersList(), plane.getPassengersWithoutSeats());
        }
        else {
            plane.getPassengersList().put(passenger.getSeatNumber(), passenger.getName());
            System.out.println("Boarding completed for " + passenger.getName() + " on flight " + plane.getFlightID() + " to " + plane.getDestination());
        }
    }

    public void findAlternativePlane(ArrayList<Plane> planes, Passenger passenger, ArrayList<String> passengersWithProblem) {
        for (Plane p : planes) {
            if (p.getDestination().equals(passenger.getDestination()) && !p.getFlightID().equals(passenger.getFlightId())) {
                p.findFreeSeatInThisPlane(p.getPassengersList(), passengersWithProblem);
            }
        }
        System.out.println("There are no more free seats on flights to " + passenger.getDestination());
    }

    public Passenger findPassengerByName(ArrayList<Passenger> registrationList, String name) {
        for (Passenger passenger : registrationList) {
            if (passenger.getName().equals(name)) {
                return passenger;
            }
        }
        return null;
    }
}




