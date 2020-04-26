package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;
import ua.nure.odnokozov.railway.ticket.office.service.RouteService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ShowRouteCarriagesCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ShowRouteCarriagesCommand.class);
    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_DEPARTURE_STATION_ID = "departureStationId";
    private static final String REQUEST_ARRIVAL_STATION_ID = "arrivalStationId";
    private static final String REQUEST_ROUTE = "route";
    
    private RouteService routeService = new RouteService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start ShowRouteCarriagesCommand");
        long routeId = Long.valueOf(request.getParameter(REQUEST_ROUTE_ID));
        long departureStationId = Long.valueOf(request.getParameter(REQUEST_DEPARTURE_STATION_ID));
        long arrivalStationId = Long.valueOf(request.getParameter(REQUEST_ARRIVAL_STATION_ID));
        RouteDTO route = routeService.getRouteByIdAndStationsIds(routeId, departureStationId, arrivalStationId);
        request.setAttribute(REQUEST_ROUTE, route);
        return PagesConstants.SHOW_ROUTE_CARRIAGES_PAGE;
    }

}
