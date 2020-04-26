package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminCancelEditStationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminCancelEditStationCommand.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminCancelEditStationCommand");
        return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_STATIONS_PAGE;
    }

}
