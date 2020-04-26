package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.service.TicketService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientPayTicketsCommand implements Command {

    private static final Logger LOG = Logger.getLogger(ClientPayTicketsCommand.class);
    private static final String REQUEST_TICKETS_IDS = "ticketsIds";
    private static final String SESSION_ROUTE_ID = "routeId";

    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start ClientPayTicketsCommand");
        if (request.getSession().getAttribute(SESSION_ROUTE_ID) != null) {
            request.getSession().removeAttribute(SESSION_ROUTE_ID);
        }
        
        List<Long> ticketsIds = mapSeatsIds(request.getParameterValues(REQUEST_TICKETS_IDS));
        LOG.trace("ticketsIds =" + ticketsIds);
        if (ticketService.payTickets(ticketsIds)) {
            LOG.info("Tickets are paid");
            return PagesConstants.REDIRECT_CLIENT_SUCCESSFUL_TICKETS_PAYMENT;
        }
        LOG.info("Tickets are not paid");
        return PagesConstants.REDIRECT_CLIENT_ERROR_TICKETS_PAYMENT;
    }

    private List<Long> mapSeatsIds(String[] ticketsIds) {
        return Arrays.asList(ticketsIds).stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
    }
}
