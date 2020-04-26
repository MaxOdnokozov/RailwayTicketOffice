package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.carriage;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminEditCarriageFormCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminEditCarriageFormCommand.class);

    private static final String REQUEST_CARRIAGE_ID = "carriageId";
    private static final String REQUEST_CARRIAGE = "carriage";
    private static final String ERROR_NOT_FOUND_UPDATE = "errorNotFoundUpdate";

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Start AdminEditCarriageFormCommand ");
        Optional<Carriage> carriage = carriageService
                .getCarriage(Long.valueOf(request.getParameter(REQUEST_CARRIAGE_ID)));
        if(carriage.isPresent()) {
            request.setAttribute(REQUEST_CARRIAGE, carriage.get());
            return PagesConstants.ADMIN_EDIT_CARRIAGE_PAGE;
        }
        request.setAttribute(ERROR_NOT_FOUND_UPDATE, ERROR_NOT_FOUND_UPDATE);
        return PagesConstants.ADMIN_VIEW_ALL_CARRIAGES_PAGE;
    }

}
