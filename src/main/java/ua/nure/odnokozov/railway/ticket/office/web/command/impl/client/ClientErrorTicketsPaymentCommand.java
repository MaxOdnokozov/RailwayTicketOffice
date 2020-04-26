package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientErrorTicketsPaymentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesConstants.CLIENT_ERROR_TICKETS_PAYMENT;
    }
}
