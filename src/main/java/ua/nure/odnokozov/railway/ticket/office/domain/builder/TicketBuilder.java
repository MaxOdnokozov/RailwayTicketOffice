package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.domain.Seat;
import ua.nure.odnokozov.railway.ticket.office.domain.Ticket;
import ua.nure.odnokozov.railway.ticket.office.domain.User;

public class TicketBuilder {
    
    private Ticket ticket;

    private TicketBuilder() {
        this.ticket = new Ticket();
    }
    
    public static TicketBuilder getInstance() {
        return new TicketBuilder();
    }
    
    public TicketBuilder id(long id) {
        ticket.setId(id);
        return this;
    }
    
    public TicketBuilder route(Route route) {
        ticket.setRoute(route);
        return this;
    }
    
    public TicketBuilder user(User user) {
        ticket.setUser(user);
        return this;
    }
    
    public TicketBuilder seat(Seat seat) {
        ticket.setSeat(seat);
        return this;
    }

    public TicketBuilder departureStopId(long departureStopId) {
        ticket.setDepartureStopId(departureStopId);
        return this;
    }
    
    public TicketBuilder arrivalStopId(long arrivalStopId) {
        ticket.setArrivalStopId(arrivalStopId);
        return this;
    }
    
    public TicketBuilder price(BigDecimal price) {
        ticket.setPrice(price);
        return this;
    }
    
    public TicketBuilder isPaid(boolean isPaid) {
        ticket.setPaid(isPaid);
        return this;
    }

    public TicketBuilder createdDateTime(LocalDateTime createdDateTime) {
        ticket.setCreatedDateTime(createdDateTime);
        return this;
    }

    public Ticket build() {
        return ticket;
    }
}
