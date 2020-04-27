package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.StopBuilder;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveRouteStopCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveRouteStopCommand.class);

    private static final String REQUEST_STATION = "station";
    private static final String REQUEST_ARRIVAL_DATE = "arrivalDate";
    private static final String REQUEST_ARRIVAL_TIME = "arrivalTime";
    private static final String REQUEST_DEPARTURE_DATE = "departureDate";
    private static final String REQUEST_DEPARTURE_TIME = "departureTime";
    private static final String REQUEST_PRICE = "price";
    private static final String SESSION_STOPS = "stops";
    private static final String SESSION_MIN_DATE = "minDate";
    private static final String SESSION_MIN_TIME = "minTime";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminSaveRouteStopCommand");

        if (request.getParameter(REQUEST_ARRIVAL_TIME) == null
                || request.getParameter(REQUEST_ARRIVAL_DATE) == null
                || request.getParameter(REQUEST_DEPARTURE_DATE) == null
                || request.getParameter(REQUEST_DEPARTURE_TIME) == null
                || request.getParameter(REQUEST_PRICE).isEmpty()) {
            LOG.debug("Request parameter are invalid");
            return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
        }

        HttpSession session = request.getSession();

        List<Stop> stops = getStops(session);
        Stop stop = getStop(request);
        stop.setStopNumber(stops.size() + 1);
        stops.add(stop);

        String minDate = stop.getDepartureDate().toString();
        String minTime = stop.getDepartureTime().toString();

        session.setAttribute(SESSION_MIN_DATE, minDate);
        session.setAttribute(SESSION_MIN_TIME, minTime);
        session.setAttribute(SESSION_STOPS, stops);

        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }

    @SuppressWarnings("unchecked")
    private List<Stop> getStops(HttpSession session) {
        return session.getAttribute(SESSION_STOPS) == null ? new LinkedList<Stop>()
                : (LinkedList<Stop>) session.getAttribute(SESSION_STOPS);
    }

    private Stop getStop(HttpServletRequest request) {
        return StopBuilder.getInstance().stationId(Long.valueOf(request.getParameter(REQUEST_STATION)))
                .arrivalDate(LocalDate.parse(request.getParameter(REQUEST_ARRIVAL_DATE)))
                .arrivalTime(LocalTime.parse(request.getParameter(REQUEST_ARRIVAL_TIME)))
                .departureDate(LocalDate.parse(request.getParameter(REQUEST_DEPARTURE_DATE)))
                .departureTime(LocalTime.parse(request.getParameter(REQUEST_DEPARTURE_TIME)))
                .price(BigDecimal.valueOf(Double.valueOf(request.getParameter(REQUEST_PRICE)))).build();
    }
}
