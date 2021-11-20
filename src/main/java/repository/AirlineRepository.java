package repository;

import entity.Flight;
import entity.Passanger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AirlineRepository {
    Flight[] flights = new Flight[0];
//    List<Flight> savedFlights = new ArrayList<>();
//    List<Passanger> savedPassanger = new ArrayList<>();

    public Passanger findPassanger(int id){
        for (int i = 0; i < flights.length; i++){
            int result = flights[i].findPassanger(id);
            if (result != -1){ //se achou, retorna o que achou :)
                return flights[i].getPassangers()[result];
            }
        }
        return null;
    }

    public Flight findFlight(int id){
        for(int i =0; i< flights.length; i++){
            if (flights[i].getId() == id){
                return flights[i];
            }
        }
        return null;
    }

    public void saveFlight(Flight flight){
        // cria um array com o tamanho do anterior incrementado de 1, e adiciona no fim o voo passado por parametro
        Flight[] flightsTemp = new Flight[flights.length+1];
        for (int i = 0; i < flights.length; i++){
          flightsTemp[i] = flights[i];
        }
        flightsTemp[flightsTemp.length-1] = flight;
        flights = flightsTemp;
    }

    //so salva um passageiro se existe um voo disponivel pra ele
    public void savePassanger(Passanger passanger){
        int targetFlight = -1;
        for(int i=0; i < flights.length;i++){
            if (flights[i].getSeats()>flights[i].getOccupiedSeats()){
                targetFlight =i;
                break;
            }
        }
        if (targetFlight==-1){
            throw new RuntimeException("Sem voos disponiveis :)");
        }
        flights[targetFlight].addPassanger(passanger);
    }

    public boolean dropPassanger(int id) {

        for (int i = 0; i < flights.length; i++) {
            if (flights[i].removePassanger(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean dropFlight(int id){
        int target = -1;

        for (int i = 0; i< flights.length;i++){
            if (flights[i].getId() == id){
                target = i;
                break;
            }
        }

        if (target==-1){
            return false;
        }

        Flight[] flightTemp= new Flight[flights.length-1];

        for (int i = 0; i<flightTemp.length ;i++){
            if (i>= target){
                flightTemp[i] = flights[i+1];
            }
            else{
                flightTemp[i] = flights[i];
            }
        }
        flights = flightTemp;
        return true;
    }

    public Flight[] getSavedFlights() {
       return flights;
    }

    public Flight getLessPassangerFlight(){
        Flight lessPassangerFlight = null;

        for(int i = 0; i < flights.length; i++){
            if (lessPassangerFlight == null ||
                    flights[i].getOccupiedSeats() < lessPassangerFlight.getOccupiedSeats()){
               lessPassangerFlight = flights[i];
            }
        }
        return lessPassangerFlight;
    }

    public boolean updatePassanger(int id, String name) {
        Passanger passanger = findPassanger(id);
        if (passanger!=null){
            passanger.setName(name);
            return true;
        }
        return false;
    }
}
