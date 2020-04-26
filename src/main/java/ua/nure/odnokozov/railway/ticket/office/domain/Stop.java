package ua.nure.odnokozov.railway.ticket.office.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Stop extends Entity implements Comparable<Stop> {

    private static final long serialVersionUID = -4967233740527717951L;

    private long routeId;
    private long stationId;
    private int stopNumber;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private BigDecimal price;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long station) {
        this.stationId = station;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Stop [id=").append(getId())
                .append(", routeId=").append(routeId)
                .append(", stopNumber=").append(stopNumber)
                .append(", stationId=").append(stationId)
                .append(", arrivalDate=").append(arrivalDate)
                .append(", arrivalTime=").append(arrivalTime)
                .append(", departureDate=").append(departureDate)
                .append(", departureTime=").append(departureTime)
                .append("]").toString();
    }

    @Override
    public int compareTo(Stop other) {
        return stopNumber < other.stopNumber ? 1 : stopNumber == other.stopNumber ? 0 : -1;
    }
}
