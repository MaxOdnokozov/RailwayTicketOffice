package ua.nure.odnokozov.railway.ticket.office.dto;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class RouteDTO {
    
    private long id;
    private String code;
    private boolean isCanceled;
    private StopDTO departureStop;
    private StopDTO arrivalStop;
    private LocalTime timeInWay;
    private BigDecimal minPrice;
    private Map<ComfortType, Integer> freeSeats;
    private List<StopDTO> stops;
    private List<RouteCarriageDTO> routeCarriages;
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private RouteDTO routeDTO;
        
        private Builder() {
            this.routeDTO = new RouteDTO();
        }
        
        public Builder id(long id) {
            routeDTO.setId(id);
            return this;
        }

        public Builder code(String code) {
            routeDTO.setCode(code);
            return this;
        }
        
        public Builder isCanceled(boolean isCanceled) {
            routeDTO.setCanceled(isCanceled);
            return this;
        }
        
        public Builder departureStop(StopDTO departureStop) {
            routeDTO.setDepartureStop(departureStop);
            return this;
        }
        
        public Builder arrivalStop(StopDTO arrivalStop) {
            routeDTO.setArrivalStop(arrivalStop);
            return this;
        }
    
        public Builder timeInWay(LocalTime timeInWay) {
            routeDTO.setTimeInWay(timeInWay);
            return this;
        }
        
        public Builder minPrice(BigDecimal minPrice) {
            routeDTO.setMinPrice(minPrice);
            return this;
        }

        public Builder freeSeats(Map<ComfortType, Integer> freeSeats) {
            routeDTO.setFreeSeats(freeSeats);
            return this;
        }
        
        public Builder stops(List<StopDTO> stops) {
            routeDTO.setStops(stops);
            return this;
        }
        
        public Builder routeCarriages(List<RouteCarriageDTO> routeCarriages) {
            routeDTO.setRouteCarriages(routeCarriages);
            return this;
        }
        
        public RouteDTO build() {
            return routeDTO;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public StopDTO getDepartureStop() {
        return departureStop;
    }

    public void setDepartureStop(StopDTO departureStop) {
        this.departureStop = departureStop;
    }

    public StopDTO getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(StopDTO arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public LocalTime getTimeInWay() {
        return timeInWay;
    }

    public void setTimeInWay(LocalTime timeInWay) {
        this.timeInWay = timeInWay;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public Map<ComfortType, Integer> getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Map<ComfortType, Integer> freeSeats) {
        this.freeSeats = freeSeats;
    }

    public List<StopDTO> getStops() {
        return stops;
    }

    public void setStops(List<StopDTO> stops) {
        this.stops = stops;
    }

    public List<RouteCarriageDTO> getRouteCarriages() {
        return routeCarriages;
    }

    public void setRouteCarriages(List<RouteCarriageDTO> routeCarriages) {
        this.routeCarriages = routeCarriages;
    }
}
