package ua.nure.odnokozov.railway.ticket.office.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDTO {

    private long id;
    private RouteDTO route;
    private UserDTO user;
    private long seatId;
    private int seatNumber;
    private long routeCarriageId;
    private int carriageNumber;
    private BigDecimal price;
    private boolean isPaid;
    private LocalDateTime createdDateTime;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TicketDTO ticket;
        
        private Builder() {
            this.ticket = new TicketDTO();
        }

        public Builder id(long id) {
            ticket.setId(id);
            return this;
        }

        public Builder route(RouteDTO route) {
            ticket.setRoute(route);
            return this;
        }

        public Builder user(UserDTO user) {
            ticket.setUser(user);
            return this;
        }

        public Builder seatId(long seatId) {
            ticket.setSeatId(seatId);
            return this;
        }
        
        public Builder seatNumber(int seatNumber) {
            ticket.setSeatNumber(seatNumber);
            return this;
        }
        
        public Builder routeCarriageId(long carriageId) {
            ticket.setRouteCarriageId(carriageId);
            return this;
        }
        
        public Builder carriageNumber(int carriageNumber) {
            ticket.setCarriageNumber(carriageNumber);
            return this;
        }

        public Builder price(BigDecimal price) {
            ticket.setPrice(price);
            return this;
        }

        public Builder isPaid(boolean isPaid) {
            ticket.setPaid(isPaid);
            return this;
        }
        
        public Builder createdDateTime(LocalDateTime createdDateTime) {
            ticket.setCreatedDateTime(createdDateTime);
            return this;
        }
        
        public TicketDTO build() {
            return ticket;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public long getRouteCarriageId() {
        return routeCarriageId;
    }

    public void setRouteCarriageId(long routeCarriageId) {
        this.routeCarriageId = routeCarriageId;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
