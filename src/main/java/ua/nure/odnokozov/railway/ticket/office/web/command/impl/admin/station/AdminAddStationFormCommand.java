package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminAddStationFormCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminAddStationFormCommand.class);
    
    private static final String REQUEST_ADD = "add";
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminAddStationFormCommand");
        request.setAttribute(REQUEST_ADD, REQUEST_ADD);
        return PagesConstants.ADMIN_EDIT_STATION_PAGE;
    }

}
