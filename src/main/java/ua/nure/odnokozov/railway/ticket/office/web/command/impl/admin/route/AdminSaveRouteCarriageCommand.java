package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.route;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.RouteCarriageBuilder;
import ua.nure.odnokozov.railway.ticket.office.service.CarriageService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveRouteCarriageCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveRouteCarriageCommand.class);

    private static final String REQUEST_CARRIAGE_ID = "carriageId";
    private static final String SESSION_ROUTE_CARRIAGES = "routeCarriages";

    private CarriageService carriageService = new CarriageService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminSaveRouteCarriageCommandCommand");
        long carriageId = Long.valueOf(request.getParameter(REQUEST_CARRIAGE_ID));

        HttpSession session = request.getSession();
        List<RouteCarriage> routeCarriages = getRouteCarriages(session);
        Optional<Carriage> carriage = carriageService.getCarriage(carriageId);
        if (carriage.isPresent()) {
            routeCarriages.add(RouteCarriageBuilder.getInstance()
                    .carriage(carriage.get())
                    .number(routeCarriages.size() + 1)
                    .build());
        }
        session.setAttribute(SESSION_ROUTE_CARRIAGES, routeCarriages);
        return PagesConstants.REDIRECT_ADMIN_ADD_ROUTE;
    }

    @SuppressWarnings("unchecked")
    private List<RouteCarriage> getRouteCarriages(HttpSession session) {
        return session.getAttribute(SESSION_ROUTE_CARRIAGES) == null ? new ArrayList<RouteCarriage>()
                : (ArrayList<RouteCarriage>) session.getAttribute(SESSION_ROUTE_CARRIAGES);
    }
}