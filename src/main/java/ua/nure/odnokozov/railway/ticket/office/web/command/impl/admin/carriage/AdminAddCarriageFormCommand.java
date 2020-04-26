package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminAddCarriageFormCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(AdminAddCarriageFormCommand.class); 

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminAddCarriageFormCommand");
        return PagesConstants.ADMIN_ADD_CARRIAGE_PAGE;
    }
}
