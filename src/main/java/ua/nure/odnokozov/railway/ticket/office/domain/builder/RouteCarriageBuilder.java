package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Price;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Seat;

public class RouteCarriageBuilder {

    private RouteCarriage routeCarriage;
    
    private RouteCarriageBuilder() {
        this.routeCarriage = new RouteCarriage();
    }
    
    public static RouteCarriageBuilder getInstance() {
        return new RouteCarriageBuilder();
    }
    
    public RouteCarriageBuilder id(long id) {
        routeCarriage.setId(id);
        return this;
    }
    
    public RouteCarriageBuilder routeId(long routeId) {
        routeCarriage.setRouteId(routeId);
        return this;
    }
    
    public RouteCarriageBuilder carriage(Carriage carriage) {
        routeCarriage.setCarriage(carriage);
        return this;
    }
    
    public RouteCarriageBuilder price(Price price) {
        routeCarriage.setPrice(price);
        return this;
    }

    public RouteCarriageBuilder number(int number) {
        routeCarriage.setNumber(number);
        return this;
    }
    
    public RouteCarriageBuilder seats(List<Seat> seats) {
        routeCarriage.setSeats(seats);
        return this;
    }
    
    public RouteCarriage build() {
        return routeCarriage;
    }    
}
