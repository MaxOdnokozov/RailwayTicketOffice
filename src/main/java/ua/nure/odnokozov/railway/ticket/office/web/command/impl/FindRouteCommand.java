package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;
import ua.nure.odnokozov.railway.ticket.office.service.RouteService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class FindRouteCommand implements Command {

    private static final Logger LOG = Logger.getLogger(FindRouteCommand.class);
    
    private static final String REQUEST_ARRIVAL_STATION_ID = "arrivalStationId";
    private static final String REQUEST_DEPARTURE_STATION_ID = "departureStationId";
    private static final String REQUEST_DATE = "date";
    private static final String REQUEST_ROUTES = "routes";
    private static final String REQUEST_MESSAGE = "message";

    private RouteService routeService = new RouteService();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start FindRouteCommand");  
        if (!isValidStationId(request.getParameter(REQUEST_DEPARTURE_STATION_ID), request)
                || !isValidStationId(request.getParameter(REQUEST_ARRIVAL_STATION_ID), request)
                || !isValidDate(request.getParameter(REQUEST_DATE), request)) {
            LOG.info("Request parameters are incorect");
            return PagesConstants.WELCOME_PAGE;
        }
        
        long departureStationId = Long.valueOf(request.getParameter(REQUEST_DEPARTURE_STATION_ID));
        long arrivalStationId = Long.valueOf(request.getParameter(REQUEST_ARRIVAL_STATION_ID));
        LocalDate date = LocalDate.parse(request.getParameter(REQUEST_DATE));    
        
        List<RouteDTO> routes = routeService.getAllActualsByStationsAndDate(departureStationId, arrivalStationId, date);
        if(routes.size() != 0) {
            request.setAttribute(REQUEST_ROUTES, routes);
        } else {
            request.setAttribute(REQUEST_MESSAGE, "Sorry, but routes with such parameters are not found.");
        }      
        return PagesConstants.WELCOME_PAGE;
    }
    
    private boolean isValidStationId(String stationId, HttpServletRequest request) {
        if(!Validator.isNumber(stationId)) {
            sendRequestErrorMessage("Station is invalid", request);
            return false;
        }
        return true;
    }

    private boolean isValidDate(String date, HttpServletRequest request) {
        if(!Validator.isLocalDate(date)) {
            LOG.trace(LocalDate.parse(request.getParameter(REQUEST_DATE)));
            sendRequestErrorMessage("Date is invalid", request);
            return false;
        }
        return true;
    }
    
    private void sendRequestErrorMessage(String message, HttpServletRequest request) {
        LOG.debug(message);
        request.setAttribute(REQUEST_MESSAGE, message);
    }
}