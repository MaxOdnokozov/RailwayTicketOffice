package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminViewAllCarriagesCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminViewAllCarriagesCommand.class);

    private static final String REQUEST_CARRIAGES = "carriages";

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Execute AdminViewAllCarriagesCommand");
        List<Carriage> carriages = carriageService.getAll();
        request.setAttribute(REQUEST_CARRIAGES, carriages);
        return PagesConstants.ADMIN_VIEW_ALL_CARRIAGES_PAGE;
    }
}
