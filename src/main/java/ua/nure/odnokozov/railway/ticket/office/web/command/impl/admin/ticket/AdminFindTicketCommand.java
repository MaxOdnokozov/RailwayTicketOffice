package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.ticket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.TicketDTO;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminFindTicketCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminFindTicketCommand.class);

    private static final String REQUEST_EMAIL = "email";
    private static final String REQUEST_LAST_NAME = "lastName";
    private static final String REQUEST_FIRST_NAME = "firstName";
    private static final String REQUEST_CANCELED = "canceled";
    private static final String REQUEST_REMAIL = "emailForLocale";
    private static final String REQUEST_RLAST_NAME = "lastNameForLocale";
    private static final String REQUEST_RFIRST_NAME = "firstNameForLocale";
    private static final String REQUEST_RCANCELED = "canceledForLocale";
    private static final String REQUEST_MESSAGE = "message";
    private static final String REQUEST_TICKETS = "tickets";

    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminFindTicketCommand");
        String email = request.getParameter(REQUEST_EMAIL);
        String lastName = request.getParameter(REQUEST_LAST_NAME);
        String firstName = request.getParameter(REQUEST_FIRST_NAME);
        String canceled = request.getParameter(REQUEST_CANCELED);

        List<TicketDTO> tickets = null;

        if (canceled != null) {
            tickets = ticketService.getAllCanceledTickets();
            LOG.debug("Find tickets of canceled routes");
        } else if (lastName != null && firstName != null) {
            tickets = ticketService.getAllByName(firstName, lastName);
            LOG.debug("Find tickets by name");
        } else if (email != null) {
            tickets = ticketService.getAllByEmail(email);
            LOG.debug("Find tickets by email");
        }
        
        if (tickets == null || tickets.size() == 0) {
            request.setAttribute(REQUEST_MESSAGE, "Not found");
        }
        LOG.debug("Tickets was Founds");
        setRequestAttributesForLocale(request);
        request.setAttribute(REQUEST_TICKETS, tickets);
        return PagesConstants.ADMIN_HOME_PAGE;
    }
    
    private void setRequestAttributesForLocale(HttpServletRequest request) {
        request.setAttribute(REQUEST_RFIRST_NAME, request.getParameter(REQUEST_FIRST_NAME));
        request.setAttribute(REQUEST_RLAST_NAME, request.getParameter(REQUEST_LAST_NAME));
        request.setAttribute(REQUEST_REMAIL, request.getParameter(REQUEST_EMAIL));
        request.setAttribute(REQUEST_RCANCELED, request.getParameter(REQUEST_CANCELED));
    }

}
