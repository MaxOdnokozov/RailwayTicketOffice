package ua.nure.odnokozov.railway.ticket.office.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.RouteDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.RouteDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.dto.DTOConverter;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;

public class RouteService {

    private static final Logger LOG = Logger.getLogger(RouteService.class);

    private RouteDao routeDao = new RouteDaoImpl();

    public boolean addRoute(Route entity) {
        LOG.info("Adding route");
        return routeDao.create(entity);
    }

    public List<RouteDTO> getAllByStationsAndDate(long departureStationId, long arrivalStationId, LocalDate date) {
        LOG.info("Getting routes");
        List<Route> routes = routeDao.getRoutesOnDate(departureStationId, arrivalStationId, date);
        LOG.trace("Routes :: " + routes);
        return DTOConverter.toRoutesDTO(routes, departureStationId, arrivalStationId);
    }
    
    public RouteDTO getRouteByIdAndStationsIds(long routeId, long departureStationId, long arrivalStationId) {
        LOG.info("Getting route by id :: " + routeId);
        Route route = routeDao.getById(routeId);
        RouteDTO routeDTO = DTOConverter.toRouteDTO(route, departureStationId, arrivalStationId);
        return routeDTO;
    }
    
    public List<RouteDTO> getAllActualsByStationsAndDate(long departureStationId, long arrivalStationId, LocalDate date) {
        List<RouteDTO> routes = getAllByStationsAndDate(departureStationId, arrivalStationId, date);
        LOG.info("Getting actual routes");
        return routes.stream()
                .filter(route -> LocalDate.now().compareTo(route.getDepartureStop().getDepartureDate()) <= 0)
                .collect(Collectors.toList());
    }

    public boolean delete(long routeId) {
        LOG.info("Deleting routes");
        return routeDao.delete(routeId);
    }

    public boolean cancelRoute(long routeId) {
        LOG.info("Canceling route");
        return routeDao.updateRouteCanceledStatus(routeId, true);
    }
}
