package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.service.RouteService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteRoutesCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteRoutesCommand.class);
    
    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_ERROR_DELETE = "errorDelete";

    
    private RouteService routeService = new RouteService();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteRoutesCommand");
        long routeId = Long.valueOf((String) request.getParameter(REQUEST_ROUTE_ID));
        if (routeService.delete(routeId)) {            
            LOG.debug("Deleting route was success");
            return PagesConstants.ADMIN_VIEW_ROUTES_PAGE;
        }
        if(routeService.cancelRoute(routeId)) {
            LOG.debug("Canceling route was success");            
            return PagesConstants.ADMIN_VIEW_ROUTES_PAGE;
        };
        LOG.debug("Deleting route was failed");
        request.setAttribute(REQUEST_ERROR_DELETE, REQUEST_ERROR_DELETE);
        return PagesConstants.ADMIN_VIEW_ROUTES_PAGE;
    }
}
