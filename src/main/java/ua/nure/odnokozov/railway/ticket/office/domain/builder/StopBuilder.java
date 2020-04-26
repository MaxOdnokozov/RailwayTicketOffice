package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import ua.nure.odnokozov.railway.ticket.office.domain.Stop;

public class StopBuilder {

 private Stop stop;
    
    private StopBuilder() {
        this.stop = new Stop();
    }
    
    public static StopBuilder getInstance() {
        return new StopBuilder();
    }
    
    public StopBuilder id(long id) {
        stop.setId(id);
        return this;
    }
    
    public StopBuilder routeId(long routeId) {
        stop.setRouteId(routeId);
        return this;
    }
    
    public StopBuilder stationId(long stationId) {
        stop.setStationId(stationId);
        return this;
    }
    
    public StopBuilder stopNumber(int stopNumber) {
        stop.setStopNumber(stopNumber);
        return this;
    }
    
    public StopBuilder arrivalDate(LocalDate arrivalDate) {
        stop.setArrivalDate(arrivalDate);
        return this;
    }

    public StopBuilder arrivalTime(LocalTime arrivalTime) {
        stop.setArrivalTime(arrivalTime);
        return this;
    }
    
    public StopBuilder departureDate(LocalDate departureDate) {
        stop.setDepartureDate(departureDate);
        return this;
    }

    public StopBuilder departureTime(LocalTime departureTime) {
        stop.setDepartureTime(departureTime);
        return this;
    }
    
    public StopBuilder price(BigDecimal price) {
        stop.setPrice(price);
        return this;
    }
    
    public Stop build() {
        return stop;
    }    
}