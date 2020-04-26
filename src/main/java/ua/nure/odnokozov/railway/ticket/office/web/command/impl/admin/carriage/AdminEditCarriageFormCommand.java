package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminEditCarriageFormCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminEditCarriageFormCommand.class);

    private static final String REQUEST_CARRIAGE_ID = "carriageId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Start AdminEditCarriageFormCommand ");
        long carriageId = Long.valueOf(request.getParameter(REQUEST_CARRIAGE_ID));
        request.setAttribute(REQUEST_CARRIAGE_ID, carriageId);
        return PagesConstants.ADMIN_EDIT_CARRIAGE_PAGE;
    }
}
