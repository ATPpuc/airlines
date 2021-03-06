package entity;

import java.util.Arrays;

public class Flight {
    private int id;
    private double distance;
    private int seats;
    private Passanger[] passangers;
    private int occupiedSeats;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", distance=" + distance +
                ", seats=" + seats +
                ", passangers=" + Arrays.toString(passangers) +
                ", occupiedSeats=" + occupiedSeats +
                '}';
    }

    //adiciona um passageiro a um voo
    public void addPassanger(Passanger passanger) {
        if (occupiedSeats >= seats) {
            throw new RuntimeException("no seats left :)");
        }
        passangers[occupiedSeats] = passanger;
        occupiedSeats++;
    }

    //remove um passageiro de um voo
    public boolean removePassanger(int id) {
        int target = findPassanger(id);

        if (target == -1) {
            return false;
        }//acaba aq caso ele nao encontre nada
        //senao, o codigo segue
        //aponta a posiçao do passageiro a ser removido pra null
        passangers[target] = null;
        //itera os voos, reorganizando-os
        for (int i = target + 1; i < occupiedSeats; i++) {
            passangers[i - 1] = passangers[i];
        }
        //apaga a ultima posiçao
        passangers[occupiedSeats - 1] = null;
        //reduz a quantidade de assentos ocupados
        occupiedSeats--;
        return true;
    }

    //procura por um passageiro a partir do id passado por parametro
    public int findPassanger(int id) {
        //percorre o vetor de passageiros do voo buscando pelo id do passageiro
        for (int i = 0; i < occupiedSeats; i++) {
            if (passangers[i].getId() == id) {
                //retorna a posiçao se encontrar
                return i;
            }
        }
        //retorna -1 se esta ruim
        return -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Passanger[] getPassangers() {
        return passangers;
    }

    public void setPassangers(Passanger[] passangers) {
        this.passangers = passangers;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public Flight(int id, double distance, int seats) {
        this.passangers = new Passanger[seats];
        this.id = id;
        this.distance = distance;
        this.seats = seats;
    }
}
