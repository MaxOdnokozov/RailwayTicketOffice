package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteRouteCarriageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteRouteCarriageCommand.class);

    private static final String REQUEST_ROUTE_CARRIAGE_ID = "routeCarriageNumber";
    private static final String SESSION_ROUTE_CARRIAGES = "routeCarriages";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteRouteCarriageCommand");

        if (request.getParameter(REQUEST_ROUTE_CARRIAGE_ID) != null) {
            int routeCarriageNumber = Integer.valueOf((String) request.getParameter(REQUEST_ROUTE_CARRIAGE_ID));
            HttpSession session = request.getSession();
            List<RouteCarriage> routeCarriages = getRouteCarriages(session);
            routeCarriages.remove(routeCarriageNumber);
            session.setAttribute(SESSION_ROUTE_CARRIAGES, routeCarriages);
            return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
        }
        LOG.debug("Request attribute is empty");
        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }

    @SuppressWarnings("unchecked")
    private List<RouteCarriage> getRouteCarriages(HttpSession session) {
        return session.getAttribute(SESSION_ROUTE_CARRIAGES) == null ? new ArrayList<RouteCarriage>()
                : (ArrayList<RouteCarriage>) session.getAttribute(SESSION_ROUTE_CARRIAGES);
    }
}
