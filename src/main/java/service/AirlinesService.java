package service;

import entity.Flight;
import entity.Passanger;
import repository.AirlineRepository;

import java.util.List;
import java.util.Scanner;

public class AirlinesService {
    public AirlineRepository airlineRepository = new AirlineRepository();
    public Scanner in = new Scanner(System.in);
    public Flight flight = new Flight();
    public Passanger passanger = new Passanger();

    public void saveFlight(){
        //reune as infos do voo
        System.out.println("Distância do vôo:");
        flight.setDistance(in.nextDouble());
        System.out.println("Quantidade de assentos disponíveis");
        flight.setSeats(in.nextInt());
        System.out.println("Id do Vôo:");
        flight.setId(in.nextInt());

        //salva o voo na lista de voos, usando o repositorio
        airlineRepository.saveFlight(flight);
    }

    public void savePassanger(){
        //reune as infos do passanger
        System.out.println("Id do Passageiro:");
        passanger.setId(in.nextInt());
        System.out.println("Nome do Passageiro");
        passanger.setName(in.nextLine());

        //salva o voo na lista de voos, usando o repositorio
        airlineRepository.savePassanger(passanger);
    }

    public List<Flight> getAllFlights(){
        return airlineRepository.getSavedFlights();
    }

    public List<Passanger> getAllPassangers(){
        return airlineRepository.getSavedPassangers();
    }
}
