package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.dto.TicketDTO;
import ua.nure.odnokozov.railway.ticket.office.dto.UserDTO;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientTicketsPaymentCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientTicketsPaymentCommand.class);

    private static final String REQUEST_ROUTE_ID = "routeId";
    private static final String REQUEST_TICKETS = "tickets";
    private static final String REQUEST_TOTAL_PRICE = "totalPrice";
    private static final String SESSION_USER = "user";

    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start ClientTicketsPaymentCommand");
        HttpSession session = request.getSession();
        
        if (session.getAttribute(REQUEST_ROUTE_ID) != null) {
            long routeId = (Long) session.getAttribute(REQUEST_ROUTE_ID);
            long userId = ((UserDTO) session.getAttribute(SESSION_USER)).getId();

            List<TicketDTO> tickets = ticketService.getAllUnpaidTickets(routeId, userId);
            if (tickets.size() != 0) {
                BigDecimal totalPrice = ticketService.getTotalPrice(tickets);
                request.setAttribute(REQUEST_TOTAL_PRICE, totalPrice);
                request.setAttribute(REQUEST_TICKETS, tickets);
                return PagesConstants.CLIENT_TICKETS_PAYMENT_PAGE;
            }
        }
        return PagesConstants.REDIRECT_CLIENT_ERROR_TICKETS_PAYMENT;
    }
}
