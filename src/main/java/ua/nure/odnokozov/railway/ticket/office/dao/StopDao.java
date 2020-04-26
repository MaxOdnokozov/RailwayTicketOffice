package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Stop;

public interface StopDao {

    Stop getById(long id);
    
    boolean create(Stop entity);

    boolean update(Stop entity);

    boolean delete(long id);
    
    List<Stop> getAllByRouteId(Long id);

    List<Stop> getAllByRouteCode(String name);

    Stop getByRouteIdAndStopNumber(Long trainId, int stationNumber);

    boolean createAllStops(List<Stop> stops);

    boolean updateDateTime(Stop stop);
}
