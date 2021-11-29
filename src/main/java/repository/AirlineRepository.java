package repository;

import entity.Flight;
import entity.Passanger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AirlineRepository {
    Flight[] flights = new Flight[0];


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
        for(int i =0; i < flights.length; i++){
            if (flights[i].getId() == id){
                return flights[i];
            }
        }
        return null;
    }

    public boolean saveFlight(Flight flight) {
        // cria um array com o tamanho do anterior incrementado de 1, e adiciona no fim o voo passado por parametro
        //verifica a existencia de algum voo com o mesmo id
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getId() == flight.getId()) {
                return false;
            }
        }

        Flight[] flightsTemp = new Flight[flights.length + 1];
        for (int i = 0; i < flights.length; i++) {
            flightsTemp[i] = flights[i];
        }
        flightsTemp[flightsTemp.length - 1] = flight;
        flights = flightsTemp;
        return true;

    }

    //so salva um passageiro se existe um voo disponivel pra ele
    public boolean savePassanger(Passanger passanger){

        Passanger optionalPassanger = findPassanger(passanger.getId());
        if (optionalPassanger != null){
            return false;
        }
        if (flights.length==0){
            return false;
        }

        int targetFlight = -1;
        for(int i=0; i < flights.length;i++){
            if (flights[i].getSeats() > flights[i].getOccupiedSeats()){
                targetFlight =i;
                break;
            }
        }
        if (targetFlight==-1){
            return false;
        }
        flights[targetFlight].addPassanger(passanger);
        return true;
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

    public void getAllPassangers() {
        int passangersNumber = 0;

        for (int i =0;i < flights.length; i++) {
            passangersNumber += flights[i].getOccupiedSeats();
        }
        if (passangersNumber == 0){
            System.out.println("Sem passageiros :(");
            return;
        }

        Passanger[] passangers = new Passanger[passangersNumber];
        int passangerIndex = 0;

        for (int i=0; i < flights.length; i++){
            for(int j = 0; j < flights[i].getOccupiedSeats(); j++){
                passangers[passangerIndex] = flights[i].getPassangers()[j];
                passangerIndex++;
            }
        }
        for (int i = 0; i< passangers.length; i++){
            System.out.println("Id: " + passangers[i].getId());
            System.out.println("Nome: " + passangers[i].getName());
            System.out.println("---------------------------------------------------");
        }
    }

    public void getSavedFlightsToString() {
        Flight[] flights = getSavedFlights();
        if (flights.length == 0){
            System.out.println("Sem vôos salvos :(");
            return;
        }
        for (int i = 0; i< flights.length; i++){
            System.out.println("Id: " + flights[i].getId());
            System.out.println("Distancia: " + flights[i].getDistance());
            System.out.println("Assentos: " + flights[i].getSeats());
            System.out.println("Assentos Ocupados: " + flights[i].getOccupiedSeats());
            System.out.println("Passageiros: " + Arrays.toString(flights[i].getPassangers()));
            System.out.println("---------------------------------------------------");
        }
    }

    public void findAllPassengersByFlight(int idFlight){
        Flight flight = findFlight(idFlight);
        System.out.println("Ahh, achei, olha aqui seu voo:" +
                        Arrays.toString(flight.getPassangers()));
    }

    public Flight getMaxDistanceFlight(){
        Flight maior = null;

        if (flights.length == 0){
            return null;
        }

        maior=flights[0];

        for (int i = 0 ; i < flights.length ; i++) {
            if(flights[i].getDistance() > maior.getDistance()) {
                maior = flights[i];
            }
        }
        return maior;
    }

    public Flight getMaxOccupiedSeatsFlight() {
        Flight maxOccupiedSeats = flights[0];

        for (int i=0; i < flights.length; i++){
            if (flights[i].getOccupiedSeats() > maxOccupiedSeats.getOccupiedSeats()){
                maxOccupiedSeats = flights[i];
            }
        }
        return maxOccupiedSeats;
    }

    public boolean updateFlight(int id, double distance, int seats) {
        Flight flight = findFlight(id);

        //em caso de nao encontrar, retorna false
        if (flight == null){
            return false;
        }

        //se a quantidade de assentos ja ocupados for maior que a de assentos enviados para modificaçao, ele retorna false
        else{
            if (flight.getOccupiedSeats() > seats){
                return false;
            }
            else{
                for (int i =0; i < flights.length; i++){
                    //percorre o vetor de voos salvos, quando encontrar ele modifica as informaçoes e retorna true
                    if (flights[i].getId() == flight.getId() ){
                        flights[i].setDistance(distance);
                        flights[i].setSeats(seats);
                    }
                }
            }
        }
        return true;
    }
}
