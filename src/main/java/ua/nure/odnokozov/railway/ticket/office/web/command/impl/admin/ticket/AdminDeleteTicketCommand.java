package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.ticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteTicketCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteTicketCommand.class);

    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_TICKET_ID = "ticketId";

    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteTicketCommand");

        String ticketId = request.getParameter(REQUEST_TICKET_ID);

        if (ticketId != null) {
            if (ticketService.deleteTicket(Long.valueOf(ticketId))) {
                request.setAttribute(REQUEST_MESSAGE, "Success");
                return PagesConstants.ADMIN_HOME_PAGE;
            }
        }
        request.setAttribute(REQUEST_MESSAGE, "Failed");
        return PagesConstants.ADMIN_HOME_PAGE;
    }
}
