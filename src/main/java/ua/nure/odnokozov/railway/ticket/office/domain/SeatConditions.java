package ua.nure.odnokozov.railway.ticket.office.domain;

import java.util.Map;

public class SeatConditions extends Entity {

    private static final long serialVersionUID = 2501117560778888020L;

    private long seatId;
    private Map<Integer, Boolean> seatConditions;

    public SeatConditions() {
    }
    
    public SeatConditions(long seatId, Map<Integer, Boolean> seatConditions) {
        this.seatId = seatId;
        this.seatConditions = seatConditions;
    }

    public Map<Integer, Boolean> getSeatConditions() {
        return seatConditions;
    }

    public void setSeatConditions(Map<Integer, Boolean> seatConditions) {
        this.seatConditions = seatConditions;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public void addSeatCondition(Integer stopNumber, Boolean isFree) {
        seatConditions.put(stopNumber, isFree);
    }

    public Boolean isFree(Integer stopNumber) {
        return seatConditions.get(stopNumber);
    }

    public Boolean isFree(Integer departureStopNumber, Integer arrivalStopNumber) {
        for (int i = departureStopNumber; i < arrivalStopNumber; i++) {
            if (seatConditions.get(i) != true) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SeatConditions[seatId =").append(seatId);
        for (Map.Entry<Integer, Boolean> entry : seatConditions.entrySet()) {
            result.append(", stopNumber=").append(entry.getKey()).append(", isFree=").append(entry.getValue());
        }
        return result.append("]").toString();
    }
}
