package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.SeatConditions;

public interface SeatConditionsDao {

    SeatConditions getBySeatId(Long seatId);

    boolean updateBySeatsIdsAndStopNumbers(List<Long> seatsIds, int departureStopNumber, int arrivalStopNumber,
            boolean condition);
}
