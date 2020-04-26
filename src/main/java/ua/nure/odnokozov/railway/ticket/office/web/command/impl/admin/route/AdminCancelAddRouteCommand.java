package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminCancelAddRouteCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(AdminCancelAddRouteCommand.class);
    
    private static final String SESSION_STOPS = "stops";
    private static final String SESSION_ROUTE_CODE = "code";
    private static final String SESSION_ROUTE_CARRIAGES = "routeCarriages";
    private static final String SESSION_MIN_DATE = "minDate";
    private static final String SESSION_MIN_TIME = "minTime";
    private static final String SESSION_MAX_TIME = "maxTime";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminCancelAddRouteCommand");
        
        HttpSession session = request.getSession(); 
        
        session.removeAttribute(SESSION_STOPS);
        session.removeAttribute(SESSION_ROUTE_CODE);
        session.removeAttribute(SESSION_ROUTE_CARRIAGES);
        session.removeAttribute(SESSION_MIN_DATE);
        session.removeAttribute(SESSION_MIN_TIME);
        session.removeAttribute(SESSION_MAX_TIME);
        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }
}
