package ua.nure.odnokozov.railway.ticket.office.web.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class LogoutCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Starts LogoutCommand");
        HttpSession session = request.getSession(false);
        if (session != null) {
                session.invalidate();
        }
        LOG.debug("LogoutCommand finished");
        return PagesConstants.WELCOME_PAGE;
    }
}
