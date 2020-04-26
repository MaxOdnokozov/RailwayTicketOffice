package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.StopDTO;
import ua.nure.odnokozov.railway.ticket.office.service.StopService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminEditRouteFormCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(AdminEditRouteFormCommand.class);

    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_ROUTE_CODE = "routeCode";
    private static final String REQUEST_STOPS = "stops";
    private static final String REQUEST_STOP_ID = "stopId";
    private static final String REQUEST_EDIT_STOP_ID = "editStopId";

    private StopService stopService = new StopService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminEditRouteCommand");
        long routeId = Long.valueOf(request.getParameter(REQUEST_ROUTE_ID));
        String routeCode = request.getParameter(REQUEST_ROUTE_CODE);
        List<StopDTO> stops = stopService.getAllStopsByRouteId(routeId);
        request.setAttribute(REQUEST_STOPS, stops);
        request.setAttribute(REQUEST_ROUTE_CODE, routeCode);
        if(request.getParameter(REQUEST_STOP_ID) != null) {
            long stopId = Long.valueOf(request.getParameter(REQUEST_STOP_ID));
            request.setAttribute(REQUEST_EDIT_STOP_ID, stopId);
        }
        request.setAttribute(REQUEST_ROUTE_ID, routeId);
        return PagesConstants.ADMIN_EDIT_ROUTE_FORM;
    }
}
