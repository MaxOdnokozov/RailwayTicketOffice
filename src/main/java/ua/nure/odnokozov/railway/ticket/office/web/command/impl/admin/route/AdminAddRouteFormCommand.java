package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminAddRouteFormCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminAddRouteFormCommand.class);

    private static final String SESSION_STOPS = "stops";
    private static final String SESSION_MIN_DATE = "minDate";
    private static final String SESSION_MAX_DATE = "maxDate";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminAddRouteFormCommand");
        
        HttpSession session = request.getSession();
        if (session.getAttribute(SESSION_STOPS) == null) {
            String minDate = LocalDate.now().toString();
            String maxDate = LocalDate.now().plusYears(1).toString(); 
            session.setAttribute(SESSION_MIN_DATE, minDate);
            session.setAttribute(SESSION_MAX_DATE, maxDate);           
        }
        
        return PagesConstants.ADMIN_ADD_ROUTE_PAGE;
    }
}
