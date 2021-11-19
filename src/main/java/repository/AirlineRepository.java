package repository;

import entity.Flight;
import entity.Passanger;

import java.util.ArrayList;
import java.util.List;

public class AirlineRepository {

    List<Flight> savedFlights = new ArrayList<>();
    List<Passanger> savedPassanger = new ArrayList<>();

    public void saveFlight(Flight flight){
        savedFlights.add(flight);
    }

    public void savePassanger(Passanger passanger){
        savedPassanger.add(passanger);
    }

    public void dropPassanger(Passanger passanger){
        savedPassanger.removeIf(p -> p.getId().equals(passanger.getId()));
    }

    public void dropFlight(Flight flight){
        savedFlights.removeIf(f -> f.getId().equals(flight.getId()));
    }

    public List<Flight> getSavedFlights() {
        return savedFlights;
    }

    public void setSavedFlights(List<Flight> savedFlights) {
        this.savedFlights = savedFlights;
    }

    public List<Passanger> getSavedPassanger() {
        return savedPassanger;
    }

    public void setSavedPassanger(List<Passanger> savedPassanger) {
        this.savedPassanger = savedPassanger;
    }

}
