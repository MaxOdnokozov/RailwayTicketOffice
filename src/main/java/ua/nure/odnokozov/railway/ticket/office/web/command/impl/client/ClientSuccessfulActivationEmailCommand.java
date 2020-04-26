package ua.nure.odnokozov.railway.ticket.office.web.command.impl.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class ClientSuccessfulActivationEmailCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(ClientSuccessfulActivationEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start SuccessfulActivationEmailCommand");
        return PagesConstants.CLIENT_SUCCESSFUL_ACTIVATION_EMAIL_PAGE;
    }
}
