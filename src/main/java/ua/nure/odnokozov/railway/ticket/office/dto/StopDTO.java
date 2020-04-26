package ua.nure.odnokozov.railway.ticket.office.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import ua.nure.odnokozov.railway.ticket.office.domain.Stop;

public class StopDTO {
    
    private long id;
    private long stationId;
    private int stopNumber;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private LocalDate departureDate;
    private LocalTime departureTime;
    
    public StopDTO() {
    }

    public StopDTO(Stop stop) {
        this.id = stop.getId();
        this.stationId = stop.getStationId();
        this.stopNumber = stop.getStopNumber();
        this.arrivalDate = stop.getArrivalDate();
        this.arrivalTime = stop.getArrivalTime();
        this.departureDate = stop.getDepartureDate();
        this.departureTime = stop.getDepartureTime();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
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
}
