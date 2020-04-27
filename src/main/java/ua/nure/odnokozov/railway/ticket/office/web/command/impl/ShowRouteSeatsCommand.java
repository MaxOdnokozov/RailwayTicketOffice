package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteCarriageDTO;
import ua.nure.odnokozov.railway.ticket.office.service.RouteCarriageService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ShowRouteSeatsCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ShowRouteSeatsCommand.class);
    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_DEPARTURE_STATION_ID = "departureStationId";
    private static final String REQUEST_ARRIVAL_STATION_ID = "arrivalStationId";
    private static final String REQUEST_ROUTE_CARRIAGE_NUMBER = "routeCarriageNumber";
    private static final String REQUEST_ROUTE_CARRIAGE = "routeCarriage";

    private RouteCarriageService routeCarriageService = new RouteCarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start ShowRouteSeatsCommand");
        if (request.getParameter(REQUEST_ROUTE_ID) == null
                || request.getParameter(REQUEST_ROUTE_CARRIAGE_NUMBER) == null
                || request.getParameter(REQUEST_DEPARTURE_STATION_ID) == null
                || request.getParameter(REQUEST_ARRIVAL_STATION_ID) == null) {
            LOG.debug("Request parameters are invalid");
            return PagesConstants.WELCOME_PAGE;
        }
        
        long routeId = Long.valueOf(request.getParameter(REQUEST_ROUTE_ID));
        int routeCarriageNumber = Integer.valueOf(request.getParameter(REQUEST_ROUTE_CARRIAGE_NUMBER));
        long departureStationId = Long.valueOf(request.getParameter(REQUEST_DEPARTURE_STATION_ID));
        long arrivalStationId = Long.valueOf(request.getParameter(REQUEST_ARRIVAL_STATION_ID));
        
        RouteCarriageDTO routeCarriage = routeCarriageService.getByRouteIdAndNumberAndStationsIds(routeId,
                routeCarriageNumber, departureStationId, arrivalStationId);
        request.setAttribute(REQUEST_ROUTE_CARRIAGE, routeCarriage);
        request.setAttribute(REQUEST_ROUTE_ID, routeId);
        request.setAttribute(REQUEST_DEPARTURE_STATION_ID, departureStationId);
        request.setAttribute(REQUEST_ARRIVAL_STATION_ID, arrivalStationId);
        return PagesConstants.SHOW_ROUTE_SEATS_PAGE;
    }
}
