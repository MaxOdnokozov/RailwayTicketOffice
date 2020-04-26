package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminEditStationFormCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AdminEditStationFormCommand.class);
    
    private static final String REQUEST_EDIT = "edit";
    private static final String REQUEST_STATION_ID = "stationId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminEditStationFormCommand");
        long stationId = Long.valueOf(request.getParameter(REQUEST_STATION_ID));
        request.setAttribute(REQUEST_EDIT, REQUEST_EDIT);
        request.setAttribute(REQUEST_STATION_ID, stationId);
        return PagesConstants.ADMIN_EDIT_STATION_PAGE;
    }
}
