package repository;

import entity.Flight;
import entity.Passanger;

import java.util.Arrays;


public class AirlineRepository {
    Flight[] flights = new Flight[0];

    //procura por um passageiro, mediante ao Id passado ao método.
    public Passanger findPassanger(int id) {
        //itera pelos voos buscando o passageiro por meio do metodo findPassagner da classe Flight
        for (int i = 0; i < flights.length; i++) {
            int result = flights[i].findPassanger(id);

            if (result != -1) { //se achou, retorna o que achou :)
                return flights[i].getPassangers()[result];
            }
        }
        //retorna nulo na busca que nao encontrou nada
        return null;
    }

    //procura por um voo por meio do Id fornecido
    public Flight findFlight(int id) {
        //itera ate encontrar o voo com aquela id
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getId() == id) {
                return flights[i];
            }
        }
        //retorna nulo se nao encontrar
        return null;
    }

    //salva um voo
    public boolean saveFlight(Flight flight) {
        // cria um array com o tamanho do anterior incrementado de 1, e adiciona no fim o voo passado por parametro
        //verifica a existencia de algum voo com o mesmo id
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getId() == flight.getId()) {
                //retorna false se encontrar
                return false;
            }
        }
        //cria um vetor temporario com o tamanho do vetor antigo somado de 1, para colocar um novo voo disponivel
        Flight[] flightsTemp = new Flight[flights.length + 1];
        for (int i = 0; i < flights.length; i++) {
            //transporta todos os voos do vetor antigo para o vetor novo (vetor antigo +1)
            flightsTemp[i] = flights[i];
        }
        //adiciona no fim do novo vetor o novo voo
        flightsTemp[flightsTemp.length - 1] = flight;
        //depois de salvar o novo voo no final do vetor, diz que o vetor de voos recebe o vetor com o novo voo e retorna true
        flights = flightsTemp;
        return true;
    }

    //so salva um passageiro se existe um voo disponivel pra ele
    public boolean savePassanger(Passanger passanger) {

        //procura por um passageiro com o mesmo id do que pretende ser salvo
        Passanger optionalPassanger = findPassanger(passanger.getId());
        if (optionalPassanger != null) {
            return false;
            //se achar, retorna false
        }
        if (flights.length == 0) {
            //se nao houver nenhum voo, retorna false
            return false;
        }

        int targetFlight = -1;
        //verifica a disponibilidade de voos com assentos disponiveis
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getSeats() > flights[i].getOccupiedSeats()) {
                targetFlight = i;
                break;
            }
        }
        //se nao achar voo disponivel, retorna false
        if (targetFlight == -1) {
            return false;
        }
        //se encontrar um voo disponivel, adiciona o passageiro naquele voo e retorna true
        flights[targetFlight].addPassanger(passanger);
        return true;
    }

    public boolean dropPassanger(int id) {
        //passa para o vetor de voos o passageiro a ser removido, se retornar sucesso ele retorna true
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].removePassanger(id)) {
                return true;
            }
        }
        //em caso de falha, retorna false
        return false;
    }

    public boolean dropFlight(int id) {
        int target = -1;
        //procura pelo voo a ser deletado
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getId() == id) {
                target = i;
                //se encontrar, atribui o valor da posiçao no vetor ao target
                break;
            }
        }

        if (target == -1) {
            return false;
            //se nao encontrou, retorna false
        }

        //cria um vetor novo com o tamanho do vetor de voos -1
        Flight[] flightTemp = new Flight[flights.length - 1];

        //itera o vetor de voos -1
        for (int i = 0; i < flightTemp.length; i++) {
            //se a posiçao no vetor for maior ou igual que target, coloca o voo na posiçao +1 no indice atual do vetor temporario
            if (i >= target) {
                flightTemp[i] = flights[i + 1];
            } else {
                //senao, atribui normalmente
                flightTemp[i] = flights[i];
            }
        }
        //atribui o valor de flightTemp para o vetor de flights e retorna true
        flights = flightTemp;
        return true;
    }

    //retorna os voos salvos
    public Flight[] getSavedFlights() {
        return flights;
    }

    //retorna o voo com a menor quantidade de passageiros
    public Flight getLessPassangerFlight() {
        Flight lessPassangerFlight = null;

        //percorre o vetor de voos procurando pelo voo com a menor quantidade de assentos ocupados
        for (int i = 0; i < flights.length; i++) {
            if (lessPassangerFlight == null ||
                    flights[i].getOccupiedSeats() < lessPassangerFlight.getOccupiedSeats()) {
                lessPassangerFlight = flights[i];
            }
        }
        //retorna esse voo
        return lessPassangerFlight;
    }

    //atualiza um passageiro
    public boolean updatePassanger(int id, String name) {
        //procura pelo passageiro e armazena
        Passanger passanger = findPassanger(id);
        if (passanger != null) {
            //setta no nome do passageiro com o nome passado por parametro
            passanger.setName(name);
            return true; //retorna true
        }
        //retorna false se algo der errado
        return false;
    }

    //retorna todos os passageiros
    public void getAllPassangers() {
        int passangersNumber = 0;

        //armazena a quantidade total de passageiros
        for (int i = 0; i < flights.length; i++) {
            passangersNumber += flights[i].getOccupiedSeats();
        }
        if (passangersNumber == 0) {
            System.out.println("Sem passageiros :(");
            return; //retorna se a quantidade de passageiros for 0
        }

        //cria um vetor de passageiros com a quantidade de passageiros encontrada
        Passanger[] passangers = new Passanger[passangersNumber];
        int passangerIndex = 0;

        //percorre o vetor de voos e de passageiros, colocando na posiçao corrente do vetor aquele passageiro
        for (int i = 0; i < flights.length; i++) {
            for (int j = 0; j < flights[i].getOccupiedSeats(); j++) {
                passangers[passangerIndex] = flights[i].getPassangers()[j];
                passangerIndex++;
            }
        }
        //percorre o vetor de passageiros criado printando os valores armazenados nele
        for (int i = 0; i < passangers.length; i++) {
            System.out.println("Id: " + passangers[i].getId());
            System.out.println("Nome: " + passangers[i].getName());
            System.out.println("---------------------------------------------------");
        }
    }

    //retorna todos os valores dos voos salvos em formato string
    public void getSavedFlightsToString() {
        Flight[] flights = getSavedFlights();
        if (flights.length == 0) {
            System.out.println("Sem vôos salvos :(");
            return;
        }
        for (int i = 0; i < flights.length; i++) {
            System.out.println("Id: " + flights[i].getId());
            System.out.println("Distancia: " + flights[i].getDistance());
            System.out.println("Assentos: " + flights[i].getSeats());
            System.out.println("Assentos Ocupados: " + flights[i].getOccupiedSeats());
            System.out.println("Passageiros: " + Arrays.toString(flights[i].getPassangers()));
            System.out.println("---------------------------------------------------");
        }
    }

    //procura todos os passageiros de um voo especifico
    public void findAllPassengersByFlight(int idFlight) {
        Flight flight = findFlight(idFlight);
        System.out.println("Ahh, achei, olha aqui os passageiros do seu voo: \n" +
                Arrays.toString(flight.getPassangers()));
    }

    //retorna o voo com a maior distancia
    public Flight getMaxDistanceFlight() {
        Flight maior = null;

        //retorna nulo se os voos nao existirem
        if (flights.length == 0) {
            return null;
        }

        maior = flights[0];

        //percorre comparando a distancia, retorna o maior
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getDistance() > maior.getDistance()) {
                maior = flights[i];
            }
        }
        return maior;
    }

    //retorna o voo com a menor distancia
    public Flight getShortDistanceFlight() {
        Flight menor = null;

        if (flights.length == 0) {
            return null;
        }

        menor = flights[0];

        //percorre o vetor comparando os valores
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getDistance() < menor.getDistance()) {
                menor = flights[i];
            }
        }
        return menor;
    }

    //procura pelo voo com a maior ocupoaçao
    public Flight getMaxOccupiedSeatsFlight() {
        Flight maxOccupiedSeats = flights[0];

        for (int i = 0; i < flights.length; i++) {
            if (flights[i].getOccupiedSeats() > maxOccupiedSeats.getOccupiedSeats()) {
                maxOccupiedSeats = flights[i];
            }
        }
        //retorna o objeto de voo com a maior taxa de ocupaçao
        return maxOccupiedSeats;
    }

    //altera um voo
    public boolean updateFlight(int id, double distance, int seats) {
        Flight flight = findFlight(id);

        //em caso de nao encontrar, retorna false
        if (flight == null) {
            return false;
        }

        //se a quantidade de assentos ja ocupados for maior que a de assentos enviados para modificaçao, ele retorna false
        else {
            if (flight.getOccupiedSeats() > seats) {
                return false;
            } else {
                for (int i = 0; i < flights.length; i++) {
                    //percorre o vetor de voos salvos, quando encontrar ele modifica as informaçoes e retorna true
                    if (flights[i].getId() == flight.getId()) {
                        flights[i].setDistance(distance);
                        flights[i].setSeats(seats);
                    }
                }
            }
        }
        return true;
    }

    //retorna a media de ocupaçao dos voos
    public double getAverageOccupiedSeatsFlight() {
        double occupiedSeats = 0;
        double flightNumber = flights.length;

        for (int i = 0; i < flights.length; i++) {
            occupiedSeats += flights[i].getOccupiedSeats();
        }
        //divide a quantidade de assentos ocupados pela quantidade de voos
        double avgOccupiedSeats = occupiedSeats / flightNumber;

        return avgOccupiedSeats;
    }

    //retorna a quantidade de assentos ocupados
    public double getOccupiedSeatsNumber(){
        double occupiedSeats = 0;
        for (int i = 0; i < flights.length; i++) {
            occupiedSeats += flights[i].getOccupiedSeats();
        }
        return occupiedSeats;
    }

    //retorna a quantidade de voos
    public double getFlightsNumber(){
        return flights.length;
    }
}
