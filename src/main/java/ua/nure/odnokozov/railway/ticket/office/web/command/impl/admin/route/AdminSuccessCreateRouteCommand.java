package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSuccessCreateRouteCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagesConstants.ADMIN_SUCCESS_CREATE_ROUTE_PAGE;
    }
}
