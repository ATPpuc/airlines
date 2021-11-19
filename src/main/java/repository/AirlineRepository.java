package repository;

import entity.Flight;
import entity.Passanger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AirlineRepository {

    List<Flight> savedFlights = new ArrayList<>();
    List<Passanger> savedPassanger = new ArrayList<>();

    public void saveFlight(Flight flight){
        savedFlights.add(flight);
    }

    public void savePassanger(Passanger passanger){
        savedPassanger.add(passanger);
    }

    public boolean dropPassanger(Integer id){
        return savedPassanger.removeIf(passanger -> passanger.getId().equals(id));
    }

    public boolean dropFlight(Integer id){
        return savedFlights.removeIf(flight -> flight.getId().equals(id));
    }

    public List<Flight> getSavedFlights() {
        return savedFlights;
    }

    public Flight getLessPassangerFlight(){
        Optional<Flight> optionalLessPassangerFlight;
        List<Flight> flights = getSavedFlights();
        optionalLessPassangerFlight = flights.stream().min(Comparator.comparing(Flight::getSeats));
        Flight lessPassangerFlight = optionalLessPassangerFlight.get();
        return lessPassangerFlight;
    }

    public void setSavedFlights(List<Flight> savedFlights) {
        this.savedFlights = savedFlights;
    }

    public List<Passanger> getSavedPassangers() {
        return savedPassanger;
    }

    public void setSavedPassanger(List<Passanger> savedPassanger) {
        this.savedPassanger = savedPassanger;
    }

}
