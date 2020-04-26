package ua.nure.odnokozov.railway.ticket.office.domain;

import java.util.List;

public class RouteCarriage extends Entity {

    private static final long serialVersionUID = -2518800553158036309L;
    
    private Carriage carriage;
    private long routeId;
    private int number;
    private Price price;
    private List<Seat> seats;

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    
    @Override
    public String toString() {
        return new StringBuilder()
                .append("RouteCarriage [id=").append(getId())
                .append(", routeId=").append(routeId)
                .append(", number=").append(number)
                .append(", carriage=").append(carriage)
                .append("]").toString();
    }
}
