package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;
import ua.nure.odnokozov.railway.ticket.office.service.StationService;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminDeleteStationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminDeleteStationCommand.class);

    private static final String REQUEST_STATION_ID = "stationId";

    private static final String REQUEST_ERROR_DELETE = "errorDelete";

    private StationService stationService = new StationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminDeleteStationCommand");

        long stationId = Long.valueOf(request.getParameter(REQUEST_STATION_ID));

        if (stationService.delete(stationId)) {
            LOG.debug("Deleting station from database was succes");
            deleteFromServletContext(stationId, request);
            return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_STATIONS_PAGE;
        }
        LOG.info("Deleting station was fail");
        request.setAttribute(REQUEST_ERROR_DELETE, REQUEST_ERROR_DELETE);
        return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_STATIONS_PAGE;
    }

    private void deleteFromServletContext(long stationId, HttpServletRequest request) {
        LOG.debug("Deleting station fron ServletContext");
        ServletContext context = request.getServletContext();
        @SuppressWarnings("unchecked")
        List<Station> contextStations = (List<Station>) context.getAttribute(ApplicationConstants.CONTEXT_STATIONS);
        for (Station station : contextStations) {
            if (station.getId() == stationId) {
                contextStations.remove(station);
                LOG.info("Deleting station was success");
            }
        }
        context.setAttribute(ApplicationConstants.CONTEXT_STATIONS, contextStations);
    }

}
