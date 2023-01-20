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

    //Посадка пасажирів
    public void boardingPassengers(ArrayList<Passenger> registrationList, Plane plane) {
        for (Passenger passenger : registrationList) {
            // Для кожного пасажира з реєстраційного списку шукаємо літак з відповідним номером рейсу
            if (passenger.getFlightId().equals(plane.getFlightID())) {
                //Перевірка місць
                checkingSeats(passenger, plane);
            }
        }
    }

    //Перевірка місць
    public void checkingSeats(Passenger passenger, Plane plane) {
        //Якщо в літак ще не сів жоден пасажир
        if (plane.getPassengersList().isEmpty()) {
            //Саджаємо пасажира на його місце
            plane.getPassengersList().put(passenger.getSeatNumber(), passenger.getName());
            System.out.println("Boarding completed for " + passenger.getName() + " on flight " + plane.getFlightID() + " to " + plane.getDestination());
        }
        // Якщо в літаку вже зайняте місце, яке отримав при реєстрації пасажир,
        // або номер місця більший, ніж місць в літаку
        else if (plane.getPassengersList().containsKey(passenger.getSeatNumber()) || passenger.getSeatNumber() >= plane.getSeatAmount()) {
            System.out.println("There is some problem with boarding for passenger " + passenger.getName());
            // Додаємо пасажира в список очікування вирішення проблеми
            plane.getPassengersWithoutSeats().add(passenger.getName());
            // Шукаємо вільне місце в цьому ж літаку
            plane.findFreeSeatInThisPlane(plane.getPassengersList(), plane.getPassengersWithoutSeats());
        }
        else {
            //Якщо проблем з місцем немає, то просто саджаємо пасажира на його місце
            plane.getPassengersList().put(passenger.getSeatNumber(), passenger.getName());
            System.out.println("Boarding completed for " + passenger.getName() + " on flight " + plane.getFlightID() + " to " + plane.getDestination());
        }
    }

    //Пошук альтернативного літака
    public void findAlternativePlane(ArrayList<Plane> planes, Passenger passenger, ArrayList<String> passengersWithProblem) {
        for (Plane p : planes) {
            //Якщо пункт призначення літака збігається з напрямом польоту пасажира
            // і номер рейсу відрізняється від того, на який реєструвався пасажир
            if (p.getDestination().equals(passenger.getDestination()) && !p.getFlightID().equals(passenger.getFlightId())) {
                //Шукаємо вільні місця на цьому літаку
                p.findFreeSeatInThisPlane(p.getPassengersList(), passengersWithProblem);
            }
        }
        //Якщо вільних місць не знайдено - повідомляємо
        System.out.println("There are no more free seats on flights to " + passenger.getDestination());
    }

    //Знаходження пасажира в базі за іменем
    public Passenger findPassengerByName(ArrayList<Passenger> registrationList, String name) {
        //Для кожного пасажира в списку реєстрації
        for (Passenger passenger : registrationList) {
            //Якщо ім'я зі списку реєстрації збігається з вказаним шменем пасажира
            if (passenger.getName().equals(name)) {
                //Повертаємо пасажира як об'єкт з усіма даними
                return passenger;
            }
        }
        return null;
    }
}




