package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import ua.nure.odnokozov.railway.ticket.office.domain.Seat;
import ua.nure.odnokozov.railway.ticket.office.domain.SeatConditions;

public class SeatBuilder {

    private Seat seat;
    
    private SeatBuilder() {
        this.seat = new Seat();
    }
    
    public static SeatBuilder getInstance() {
        return new SeatBuilder();
    }
    
    public SeatBuilder id(long id) {
        seat.setId(id);
        return this;
    }
    
    public SeatBuilder routeCarriageId(long routeCarriageId) {
        seat.setRouteCarriageId(routeCarriageId);
        return this;
    }
    
    public SeatBuilder number(int number) {
        seat.setNumber(number);
        return this;
    }
    
    public SeatBuilder seatConditions(SeatConditions seatConditions) {
        seat.setSeatConditions(seatConditions);
        return this;
    }
    
    public Seat build() {
        return seat;
    }
 }
