package ua.nure.odnokozov.railway.ticket.office.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket extends Entity {

    private static final long serialVersionUID = -5581355713742226459L;

    private Route route;
    private User user;
    private Seat seat;
    private BigDecimal price;
    private boolean isPaid;
    private long departureStopId;
    private long arrivalStopId;
    private LocalDateTime createdDateTime;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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

    public long getDepartureStopId() {
        return departureStopId;
    }

    public void setDepartureStopId(long departureStopId) {
        this.departureStopId = departureStopId;
    }

    public long getArrivalStopId() {
        return arrivalStopId;
    }

    public void setArrivalStopId(long arrivalStopId) {
        this.arrivalStopId = arrivalStopId;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Ticket [id=").append(getId())
                .append(", routeId=").append(route.getId())
                .append(", userId=").append(user.getId())
                .append(", seatId=").append(seat.getId())
                .append(", price=").append(price)
                .append(", isPaid=").append(isPaid)
                .append(", departureStopId=").append(departureStopId)
                .append(", arrivalStopId=").append(arrivalStopId)
                .append("]").toString();
    }
}
