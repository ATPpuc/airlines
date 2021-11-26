package service;

import entity.Flight;
import entity.Passanger;
import jdk.jfr.FlightRecorderListener;
import repository.AirlineRepository;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AirlinesService {
    public AirlineRepository airlineRepository = new AirlineRepository();
    public Scanner in = new Scanner(System.in);

    public void saveFlight(){
        //reune as infos do voo

        System.out.println("Distância do vôo:");
        double distance = in.nextDouble();
        System.out.println("Quantidade de assentos disponíveis");
        int seats = in.nextInt();
        System.out.println("Id do Vôo:");
        int id = in.nextInt();
        Flight flight = new Flight(id, distance, seats);
        //salva o voo na lista de voos, usando o repositorio
        airlineRepository.saveFlight(flight);
    }

    public void savePassanger(){
        //reune as infos do passanger
        Passanger passanger = new Passanger();
        System.out.println("Id do Passageiro:");
        passanger.setId(in.nextInt());
        System.out.println("Nome do Passageiro");
        passanger.setName(in.next());
        in.nextLine();

        //salva o voo na lista de voos, usando o repositorio
        airlineRepository.savePassanger(passanger);
    }

    public void getAllFlights(){
        airlineRepository.getSavedFlightsToString();
    }

    public void getAllPassangers(){
        airlineRepository.getAllPassangers();
    }

    public void getLessPassangersFlight() {
        Flight flight = airlineRepository.getLessPassangerFlight();
        System.out.println(flight.toString());
    }

    public void dropFlight() {
        System.out.println("Id do vôo que deseja excluir: ");
        Integer flightDrop = in.nextInt();
        boolean result = airlineRepository.dropFlight(flightDrop);
        if(result){
            System.out.println("Deletado com sucesso!");
        }
        else
            System.out.println("Erro ao deletar");
    }

    public void dropPassanger() {
        System.out.println("Id do passageiro que deseja excluir: ");
        int passangerDrop = in.nextInt();
        boolean result = airlineRepository.dropPassanger(passangerDrop);
        if(result){
            System.out.println("Deletado com sucesso!");
        }
        else
            System.out.println("Erro ao deletar");
    }

    public void updatePassanger(){
        System.out.println("Insira o Id do passageiro que deseja alterar:");
        int id = in.nextInt();
        in.nextLine();
        System.out.println("insira o novo nome:");
        String nome = in.nextLine();

        boolean result = airlineRepository.updatePassanger(id, nome);
        if (result)
            System.out.println("Alterado com sucesso!");
        else
            System.out.println("Erro ao alterar");
    }

    public void updateFlight(){

    }

    public void findAllPassengers(){
        System.out.println("Qual a id do voo que o sr quer buscar todos os passageiros?");
        int idFlight = in.nextInt();
        airlineRepository.findAllPassengersByFlight(idFlight);
    }

    public void getMaxDistanceFlight(){
        System.out.println("Analisando o voo mais longo..");
        Flight maxDistanceFlight = airlineRepository.getMaxDistanceFlight();
        System.out.println(maxDistanceFlight.toString());
    }

    public void getMaxOccupiedSeatsFlight() {
        System.out.println("Analisando voo com mais assentos ocupados...");
        Flight flight = airlineRepository.getMaxOccupiedSeatsFlight();
        System.out.println("Id do Voo: " + flight.getId());
        System.out.println("Distancia do voo: " + flight.getDistance());
        System.out.println("Assentos ocupados: "+ flight.getOccupiedSeats());
        System.out.println("Passageiros: " + Arrays.toString(flight.getPassangers()));
    }
}
