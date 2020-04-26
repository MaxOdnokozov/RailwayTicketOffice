package ua.nure.odnokozov.railway.ticket.office.dto;

public class SeatDTO {

    private long id;
    private int number;
    private boolean isFree;
    
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        
        private SeatDTO seatDTO;
        
        private Builder() {
            this.seatDTO = new SeatDTO();
        }
        
        public Builder id(long id) {
            seatDTO.setId(id);
            return this;
        }
        
        public Builder number(int number) {
            seatDTO.setNumber(number);
            return this;
        }
        
        public Builder setFree(boolean isFree) {
            seatDTO.setFree(isFree);
            return this;
        }
        
        public SeatDTO build() {
            return seatDTO;
        }
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
}
