package entity;

public class Flight {
    private Integer id;
    private Double distance;
    private Integer seats;

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", seats=" + seats +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Flight() {
    }

    public Flight(Integer id, Double distance, Integer seats) {
        this.id = id;
        this.distance = distance;
        this.seats = seats;
    }
}
