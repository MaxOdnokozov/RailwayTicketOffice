package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class NoCommand implements Command {

    private static final Logger LOG = Logger.getLogger(NoCommand.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.trace("start no-command");
        request.setAttribute("error", "404");
        return PagesConstants.WELCOME_PAGE;
    }
}
