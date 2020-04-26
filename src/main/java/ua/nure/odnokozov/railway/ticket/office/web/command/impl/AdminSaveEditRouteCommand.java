package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.StopBuilder;
import ua.nure.odnokozov.railway.ticket.office.dto.StopDTO;
import ua.nure.odnokozov.railway.ticket.office.service.StopService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveEditRouteCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveEditRouteCommand.class);

    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_STOP_ID = "stopId";
    private static final String REQUEST_ROUTE_CODE = "routeCode";
    private static final String REQUEST_STOPS = "stops";
    private static final String REQUEST_DEPARTURE_DATE = "departureDate";
    private static final String REQUEST_ARRIVAL_DATE = "arrivalDate";
    private static final String REQUEST_DEPARTURE_TIME = "departureTime";
    private static final String REQUEST_ARRIVAL_TIME = "arrivalTime";

    private StopService stopService = new StopService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminSaveEditRouteCommand");
        long routeId = Long.valueOf(request.getParameter(REQUEST_ROUTE_ID));
        String routeCode = request.getParameter(REQUEST_ROUTE_CODE);
        long stopId = Long.valueOf(request.getParameter(REQUEST_STOP_ID));
        String departureDate = request.getParameter(REQUEST_DEPARTURE_DATE);
        String arrivalDate = request.getParameter(REQUEST_ARRIVAL_DATE);
        String departureTime = request.getParameter(REQUEST_DEPARTURE_TIME);
        String arrivalTime = request.getParameter(REQUEST_ARRIVAL_TIME);

        Stop stop = StopBuilder.getInstance()
                .id(stopId)
                .arrivalDate(LocalDate.parse(arrivalDate))
                .departureDate(LocalDate.parse(departureDate))
                .arrivalTime(LocalTime.parse(arrivalTime))
                .departureTime(LocalTime.parse(departureTime))
                .build();
        
        stopService.updateStopDateTime(stop);
        
        List<StopDTO> stops = stopService.getAllStopsByRouteId(routeId);
        request.setAttribute(REQUEST_STOPS, stops);
        request.setAttribute(REQUEST_ROUTE_CODE, routeCode);
        request.setAttribute(REQUEST_ROUTE_ID, routeId);
        return PagesConstants.ADMIN_EDIT_ROUTE_FORM;
    }
}
