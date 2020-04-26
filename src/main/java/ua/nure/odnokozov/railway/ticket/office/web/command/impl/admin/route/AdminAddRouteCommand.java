package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.RouteBuilder;
import ua.nure.odnokozov.railway.ticket.office.service.RouteService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminAddRouteCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminAddRouteCommand.class);
    
    private static final String SESSION_STOPS = "stops";
    private static final String SESSION_ROUTE_CODE = "code";
    private static final String SESSION_ROUTE_CARRIAGES = "routeCarriages";
    private static final String SESSION_MIN_DATE = "minDate";
    private static final String SESSION_MIN_TIME = "minTime";
    private static final String SESSION_MAX_TIME = "maxTime";

    private RouteService routeService = new RouteService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminAddRouteCommand");
        HttpSession session = request.getSession();
        
        Route route = getRoute(session);
        boolean routeIsCreated = routeService.addRoute(route);
        if(routeIsCreated) {
            removeSessionAttributes(session);
            return PagesConstants.REDIRECT_ADMIN_HOME;
        }
        return PagesConstants.ADMIN_ADD_ROUTE_PAGE;
    }

    @SuppressWarnings("unchecked")
    private Route getRoute(HttpSession session) {
        return RouteBuilder.getInstance()
                .code((String)session.getAttribute(SESSION_ROUTE_CODE))
                .stops((List<Stop>) session.getAttribute(SESSION_STOPS))
                .routeCarriages((List<RouteCarriage>) session.getAttribute(SESSION_ROUTE_CARRIAGES))
                .build();
    }

    private void removeSessionAttributes(HttpSession session) {
        session.removeAttribute(SESSION_STOPS);
        session.removeAttribute(SESSION_ROUTE_CODE);
        session.removeAttribute(SESSION_ROUTE_CARRIAGES);
        session.removeAttribute(SESSION_MIN_DATE);
        session.removeAttribute(SESSION_MIN_TIME);
        session.removeAttribute(SESSION_MAX_TIME);
    }
}
