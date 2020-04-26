package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Seat;

public interface SeatDao {

    List<Seat> getAllByRouteCarriageId(long routeCarriageId);

    Seat getById(long seatId);
}
