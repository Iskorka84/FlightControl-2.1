package flight;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {

        // Створення парку літаків
        ArrayList<Plane> planes = new ArrayList<Plane>();
        planes.add(new Plane("Airbus", 4));
        planes.add(new Plane("Boeing", 4));
        planes.add(new Plane("Private Jet", 3));

        Airport airport = new Airport(planes);
        airport.registrationOfAircrafts(planes);

        // Реэстрація пасажирів
        ArrayList<Passenger> registrationList = new ArrayList<Passenger>();
        registrationList.add(new Passenger("Alice", "PS001", "London", 0));
        registrationList.add(new Passenger("Bob", "PS001", "London", 2));
        registrationList.add(new Passenger("Tom", "PS001", "London", 4));

        registrationList.add(new Passenger("John", "PS002", "Paris",1));
        registrationList.add(new Passenger("Anna", "PS002", "Paris",3));
        registrationList.add(new Passenger("Emma", "PS002", "Paris", 3));

        registrationList.add(new Passenger("Mike", "PS003", "London", 0));
        registrationList.add(new Passenger("Lisa", "PS003", "London", 1));
        registrationList.add(new Passenger("Alex", "PS003","London", 2));
        registrationList.add(new Passenger("Nick", "PS003", "London", 1));
        registrationList.add(new Passenger("Bill", "PS003", "London", 2));

        for (Plane p: planes) {
            //Посадка пасажирів
            airport.boardingPassengers(registrationList, p);
            //Якщо хтось є в списку пасажирів, що мають проблему з посадочним місцем - шукаємо для них вільні місця в літаку
            if (!p.getPassengersWithoutSeats().isEmpty()) {
                p.findFreeSeatInThisPlane(p.getPassengersList(), p.getPassengersWithoutSeats());
            }
            //Якщо в проблемному списку ще залишилися пасажири - шукаємо для них альтернативні рейси
            if (!p.getPassengersWithoutSeats().isEmpty()) {
                Passenger passengerWithoutSeat = airport.findPassengerByName(registrationList, p.getPassengersWithoutSeats().get(0));
                airport.findAlternativePlane(planes, passengerWithoutSeat, p.getPassengersWithoutSeats());
            }
            //Друк схеми розміщення пасажирів в літаку
            for (Plane o : planes) {
                System.out.println(o.getPassengersList());
            }
        }
    }
}
