package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.RouteCarriageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.StopDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.RouteCarriageDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.StopDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.dto.DTOConverter;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteCarriageDTO;

public class RouteCarriageService {
    
    private static final Logger LOG = Logger.getLogger(RouteCarriageService.class);
    
    private RouteCarriageDao routeCarriageDao = new RouteCarriageDaoImpl();
    private StopDao stopDao = new StopDaoImpl();
    
    public RouteCarriageDTO getByRouteIdAndNumberAndStationsIds(long routeId, int number, long departureStationId, long arrivalStationId) {
        LOG.info("Getting all routeCarriages by route id :: " + routeId);
        List<Stop> stops = stopDao.getAllByRouteId(routeId);
        RouteCarriage routeCarriage = routeCarriageDao.getByRouteIdAndCarriageNumber(routeId, number);
        return DTOConverter.toRouteCarriageDTO(routeCarriage, stops, departureStationId, arrivalStationId);
    }
}
