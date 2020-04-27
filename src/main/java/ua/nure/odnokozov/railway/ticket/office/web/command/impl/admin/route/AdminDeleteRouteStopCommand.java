package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteRouteStopCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteRouteStopCommand.class);

    private static final String REQUEST_STOP_NUMBER = "stopNumber";
    private static final String SESSION_STOPS = "stops";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteRouteStopCommand");
        
        if(request.getParameter(REQUEST_STOP_NUMBER) ==null) {
            LOG.debug("Request parameter is invalid");
            return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
        }
        int stopNumber = Integer.valueOf((String) request.getParameter(REQUEST_STOP_NUMBER));
        HttpSession session = request.getSession();
        List<Stop> stops = getStops(session);
        stops.remove(stopNumber);
        session.setAttribute(SESSION_STOPS, stops);
        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }

    @SuppressWarnings("unchecked")
    private List<Stop> getStops(HttpSession session) {
        return session.getAttribute(SESSION_STOPS) == null ? new LinkedList<Stop>()
                : (LinkedList<Stop>) session.getAttribute(SESSION_STOPS);
    }
}