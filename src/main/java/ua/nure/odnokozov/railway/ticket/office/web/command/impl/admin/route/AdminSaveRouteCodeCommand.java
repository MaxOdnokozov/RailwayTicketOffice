package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveRouteCodeCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveRouteCodeCommand.class);

    private static final String REQUEST_ROUTE_CODE = "code";
    private static final String ERROR_ROUTE_CODE = "errorRouteCode";

    private static final int ROUTE_CODE_MAX_LENGHT = 10;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminSaveRouteCodeCommand");
        String code = request.getParameter(REQUEST_ROUTE_CODE).toUpperCase();
        if (!isValidRouteCode(code)) {
            LOG.debug("routeCode is invalid");
            request.setAttribute(ERROR_ROUTE_CODE, ERROR_ROUTE_CODE);
            return PagesConstants.ADMIN_ADD_ROUTE_CODE_PAGE;
        }
        request.getSession().setAttribute(REQUEST_ROUTE_CODE, code);
        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }

    private boolean isValidRouteCode(String code) {
        if (!Validator.isFilled(code, ROUTE_CODE_MAX_LENGHT) && !code.matches("[A-Za-z0-9]+")) {
            return false;
        }
        return true;
    }
}
