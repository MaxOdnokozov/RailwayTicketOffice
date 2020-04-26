package ua.nure.odnokozov.railway.ticket.office.dto;

import java.math.BigDecimal;
import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class RouteCarriageDTO {

    private long carriageId;
    private ComfortType comfortType;
    private int number;
    private BigDecimal price;
    private int totalFreeSeats;
    private List<SeatDTO> seats;
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private RouteCarriageDTO routeCarriageDTO;
        
        private Builder() {
            this.routeCarriageDTO = new RouteCarriageDTO();
        }
        
        public Builder carriageId(long carriageId) {
            routeCarriageDTO.setCarriageId(carriageId);
            return this;
        }
        
        public Builder comfortType(ComfortType comfortType) {
            routeCarriageDTO.setComfortType(comfortType);
            return this;
        }
        
        public Builder number(int number) {
            routeCarriageDTO.setNumber(number);
            return this;
        }
        
        public Builder price(BigDecimal price) {
            routeCarriageDTO.setPrice(price);
            return this;
        }
        
        public Builder totalFreeSeats(int totalFreeSeats) {
            routeCarriageDTO.setTotalFreeSeats(totalFreeSeats);
            return this;
        }
        
        public Builder seats(List<SeatDTO> seats) {
            routeCarriageDTO.setSeats(seats);
            return this;
        }
        
        public RouteCarriageDTO build() {
            return routeCarriageDTO;
        }
    }

    public long getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(long carriageId) {
        this.carriageId = carriageId;
    }

    public ComfortType getComfortType() {
        return comfortType;
    }

    public void setComfortType(ComfortType comfortType) {
        this.comfortType = comfortType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTotalFreeSeats() {
        return totalFreeSeats;
    }

    public void setTotalFreeSeats(int totalFreeSeats) {
        this.totalFreeSeats = totalFreeSeats;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
