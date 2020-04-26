package ua.nure.odnokozov.railway.ticket.office.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Route;

public interface RouteDao {
    
    Route getById(long id);     
    
    Route getByIdWithoutSeats(long id);   

    boolean create(Route route);

    boolean updateRouteCode(long routeId, String code);
    
    boolean delete(long id);
    
    List<Route> getAll();
    
    List<Route> getAllByCode(String code);

    List<Route> getRoutesOnDate(long departureStationId, long arrivalStationId,
            LocalDate date);

    List<Route> getRoutesOnToday(long departureStationId, long arrivalStationId, LocalDateTime date);

    boolean updateRouteCanceledStatus(long routeId, boolean isCanceled);
}
