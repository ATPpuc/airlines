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

    public void saveFlight() {
        //reune as infos do voo

        System.out.println("Distância do vôo:");
        double distance = in.nextDouble();
        System.out.println("Quantidade de assentos disponíveis");
        int seats = in.nextInt();
        System.out.println("Id do Vôo:");
        int id = in.nextInt();
        Flight flight = new Flight(id, distance, seats);
        //salva o voo na lista de voos, usando o repositorio
        boolean result = airlineRepository.saveFlight(flight);

        if (result) {
            System.out.println("Salvo com sucesso!");
        } else
            System.out.println("Erro ao salvar");
    }

    public void savePassanger() {
        //reune as infos do passanger
        Passanger passanger = new Passanger();
        System.out.println("Id do Passageiro:");
        passanger.setId(in.nextInt());
        System.out.println("Nome do Passageiro");
        passanger.setName(in.next());
        in.nextLine();

        //salva o voo na lista de voos, usando o repositorio
        boolean result = airlineRepository.savePassanger(passanger);
        if (result) {
            System.out.println("Salvo com sucesso!");
        } else
            System.out.println("Erro ao salvar");
    }

    //metodo que retorna todos os voos, imprimindo suas infos
    public void getAllFlights() {
        airlineRepository.getSavedFlightsToString();
    }

    //retorna todas as informaçoes de todos os passageiros
    public void getAllPassangers() {
        airlineRepository.getAllPassangers();
    }

    //pega o voo que tem menos passageiros
    public void getLessPassangersFlight() {
        Flight flight = airlineRepository.getLessPassangerFlight();
        System.out.println(flight.toString());
    }

    //deleta um voo
    public void dropFlight() {
        System.out.println("Apagar o vôo deletará os passageiros, continuar? \n1-Sim \n2-Nao");
        int op = in.nextInt();
        if (op == 0) {
            return;
        } else if (op == 1) {
            System.out.println("Id do vôo que deseja excluir: ");
            Integer flightDrop = in.nextInt();
            boolean result = airlineRepository.dropFlight(flightDrop);
            if (result) {
                System.out.println("Deletado com sucesso!");
            } else
                System.out.println("Erro ao deletar");
        } else System.out.println("Opção inválida");
    }

    //deleta um passageiro
    public void dropPassanger() {
        System.out.println("Id do passageiro que deseja excluir: ");
        int passangerDrop = in.nextInt();
        boolean result = airlineRepository.dropPassanger(passangerDrop);
        if (result) {
            System.out.println("Deletado com sucesso!");
        } else
            System.out.println("Erro ao deletar");
    }

    //atualiza um passageiro
    public void updatePassanger() {
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

    //atualiza um voo
    public void updateFlight() {

        System.out.println("Insira o Id do voo que deseja alterar: ");
        int id = in.nextInt();
        System.out.println("Insira a nova distância do vôo");
        double distance = in.nextDouble();
        System.out.println("Insira a nova quantidade de assentos: ");
        int seats = in.nextInt();

        boolean result = airlineRepository.updateFlight(id, distance, seats);
        //mediante ao retorno, ele exibe a mensagem correta
        if (result)
            System.out.println("Alterado com sucesso!");
        else
            System.out.println("Erro ao alterar");
    }

    //procura todos os passageiros a partir de um voo especifico
    public void findAllPassengers() {
        System.out.println("Qual a id do voo que o sr quer buscar todos os passageiros?");
        int idFlight = in.nextInt();
        airlineRepository.findAllPassengersByFlight(idFlight);
    }

    //pega o voo que tem a maior distancia
    public void getMaxDistanceFlight() {
        System.out.println("Analisando o voo mais longo..");
        Flight maxDistanceFlight = airlineRepository.getMaxDistanceFlight();
        System.out.println(maxDistanceFlight.toString());
    }

    //pega o voo que tem a menor distancia
    public void getShortDistanceFlight() {
        System.out.println("Analisando o voo mais curto..");
        Flight shortDistanceFlight = airlineRepository.getShortDistanceFlight();
        System.out.println(shortDistanceFlight.toString());
    }


/*    public void getAverageValues(){
        var x = getAllFlights();
        var y = getAllPassangers();
        var z = y/x;
        return z;
    }

    //ver a média de ocupação
    public void getAverageOccupiedSeatsFlight() {
        System.out.println("Analisando a média de ocupação dos vôos...");
        Flight flight = airlineRepository.getAvaregeOccupiedSeatsFlight();
        System.out.println("Total de voos: " + getAllFlights());
        System.out.println("Total de passageiros: " + getAllPassangers());
        System.out.println("A média de passageiros por voo é: " + getAverageValues());
    }
 */

    //pega o voo que tem a maior taxa de ocupaçao
    public void getMaxOccupiedSeatsFlight() {
        System.out.println("Analisando voo com mais assentos ocupados...");
        Flight flight = airlineRepository.getMaxOccupiedSeatsFlight();
        System.out.println("Id do Voo: " + flight.getId());
        System.out.println("Distancia do voo: " + flight.getDistance());
        System.out.println("Assentos ocupados: " + flight.getOccupiedSeats());
        System.out.println("Passageiros: " + Arrays.toString(flight.getPassangers()));
    }

    public void getAverageOccupiedSeatsFlight() {
        double average = airlineRepository.getAverageOccupiedSeatsFlight();
        System.out.println("Quantidade de voos: " + airlineRepository.getFlightsNumber());
        System.out.println("Quantidade de assentos ocupados: " + airlineRepository.getOccupiedSeatsNumber());
        System.out.println("Ocupção média de voos (Quantidade de assentos ocupados / número de voos) \n: " + average);
    }
}
