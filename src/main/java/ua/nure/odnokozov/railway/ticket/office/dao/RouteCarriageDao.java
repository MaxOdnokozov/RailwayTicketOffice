package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;

public interface RouteCarriageDao {

    List<RouteCarriage> getAllByRouteId(long routeId);

    RouteCarriage getByRouteIdAndCarriageNumber(long routeId, int number);

    List<RouteCarriage> getAllByRouteIdWithoutSeats(long routeId);

}
