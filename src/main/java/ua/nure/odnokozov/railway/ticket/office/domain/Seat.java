package ua.nure.odnokozov.railway.ticket.office.domain;

public class Seat extends Entity {

    private static final long serialVersionUID = -3430354609728593889L;

    private long routeCarriageId;
    private int number;
    private SeatConditions seatConditions;

    public long getRouteCarriageId() {
        return routeCarriageId;
    }

    public void setRouteCarriageId(long routeCarriageId) {
        this.routeCarriageId = routeCarriageId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public SeatConditions getSeatConditions() {
        return seatConditions;
    }

    public void setSeatConditions(SeatConditions seatConditions) {
        this.seatConditions = seatConditions;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Seat [id=").append(getId())
                .append(", routeCarriageId=").append(routeCarriageId)
                .append(", number=").append(number)
                .append("]").toString();
    }
}