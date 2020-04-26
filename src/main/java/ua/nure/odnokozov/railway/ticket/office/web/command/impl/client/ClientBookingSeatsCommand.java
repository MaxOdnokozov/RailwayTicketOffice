package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.service.RouteService;
import ua.nure.odnokozov.railway.ticket.office.service.SeatService;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientBookingSeatsCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientBookingSeatsCommand.class);

    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_DEPARTURE_STATION_ID = "departureStationId";
    private static final String REQUEST_ARRIVAL_STATION_ID = "arrivalStationId";
    private static final String REQUEST_ROUTE_CARRIAGE_NUMBER = "routeCarriageNumber";
    private static final String REQUEST_SELECTED_SEATS = "selectedSeats";
    private static final String SESSION_USER = "user";
    private static final String REQUEST_MESSAGE = "message";
    
    private TicketService ticketService = new TicketService();
    private RouteService routeService = new RouteService();
    private SeatService seatService = new SeatService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start BookingSeatsCommand");
        long userId = ((UserDTO) request.getSession().getAttribute(SESSION_USER)).getId();
        long routeId = Long.valueOf(request.getParameter(REQUEST_ROUTE_ID));
        int routeCarriageNumber = Integer.valueOf(request.getParameter(REQUEST_ROUTE_CARRIAGE_NUMBER));
        long departureStationId = Long.valueOf(request.getParameter(REQUEST_DEPARTURE_STATION_ID));
        long arrivalStationId = Long.valueOf(request.getParameter(REQUEST_ARRIVAL_STATION_ID));
        
        if(request.getParameterValues(REQUEST_SELECTED_SEATS) == null) {
            request.setAttribute(REQUEST_MESSAGE, "You have not chosen a place, please try again");
            return PagesConstants.WELCOME_PAGE; 
        }
        
        List<Long> seatsIds = mapSeatsIds(request.getParameterValues(REQUEST_SELECTED_SEATS)); 
        LOG.trace("seatsIds =" + seatsIds);
        
        RouteDTO route = routeService.getRouteByIdAndStationsIds(routeId, departureStationId, arrivalStationId);        
        if (seatService.bookSeats(route, seatsIds)) {
            LOG.debug("Booking seats was success");
            if (ticketService.createTickets(route, userId, routeCarriageNumber, seatsIds)) {
                request.getSession().setAttribute(REQUEST_ROUTE_ID, routeId);
                return PagesConstants.REDIRECT_CLIENT_TICKETS_PAYMENT;
            }
            LOG.warn("Tickets wasn't created");
            seatService.cancelBookSeats(route, seatsIds);
        }
        LOG.debug("Booking seats was failed");
        request.setAttribute(REQUEST_MESSAGE, "Somebody already booked this seats, please try again");
        return PagesConstants.WELCOME_PAGE;
    }

    private List<Long> mapSeatsIds(String[] selectedSeatsIds) {
        return Arrays.asList(selectedSeatsIds).stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
    }
}
