package ua.nure.odnokozov.railway.ticket.office.web.command.impl.admin.station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.constant.ApplicationConstants;
import ua.nure.odnokozov.railway.ticket.office.constant.PagesConstants;
import ua.nure.odnokozov.railway.ticket.office.domain.Language;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;
import ua.nure.odnokozov.railway.ticket.office.service.StationService;
import ua.nure.odnokozov.railway.ticket.office.util.Validator;
import ua.nure.odnokozov.railway.ticket.office.web.command.Command;

public class AdminSaveStationCommand implements Command {

    private static final Logger LOG = Logger.getLogger(AdminSaveStationCommand.class);

    private static final String REQUEST_STATION_NAMES = "stationNames";
    private static final String REQUEST_STATION_ID = "stationId";

    private static final String REQUEST_ERROR_UPDATE = "errorUpdate";

    private StationService stationService = new StationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminSaveStationCommand");

        if (isValidRequestParameter(request)) {
            LOG.debug("Requests parameters are valid");
            long stationId = Long.valueOf(request.getParameter(REQUEST_STATION_ID));
            Map<Long, String> stationNames = mapStationNames(request);

            Station station = new Station(stationId, stationNames);

            if (stationService.updateStation(station)) {
                LOG.debug("Updating station into database was success");
                updateServletContext(station, request);
                return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_STATIONS_PAGE;
            }
        }

        LOG.debug("Requests parameters are invalid");
        request.setAttribute(REQUEST_ERROR_UPDATE, REQUEST_ERROR_UPDATE);
        return PagesConstants.ADMIN_VIEW_ALL_STATIONS_PAGE;
    }

    private boolean isValidRequestParameter(HttpServletRequest request) {
        if (request.getParameterValues(REQUEST_STATION_NAMES) != null
                && request.getParameterValues(REQUEST_STATION_ID) != null) {
            String[] inputStationNames = request.getParameterValues(REQUEST_STATION_NAMES);
            for (String stationName : inputStationNames) {
                if (!Validator.isFilled(stationName)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private Map<Long, String> mapStationNames(HttpServletRequest request) {
        String[] inputStationNames = request.getParameterValues(REQUEST_STATION_NAMES);
        Map<Long, String> stationNames = new HashMap<>();
        @SuppressWarnings("unchecked")
        List<Language> contextLanguages = (List<Language>) request.getServletContext()
                .getAttribute(ApplicationConstants.CONTEXT_LANGUAGES);
        for (int i = 0; i < contextLanguages.size(); i++) {
            stationNames.put(contextLanguages.get(i).getId(), inputStationNames[i]);
        }
        return stationNames;
    }

    private void updateServletContext(Station station, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<Station> contextStations = (List<Station>) request.getServletContext()
                .getAttribute(ApplicationConstants.CONTEXT_STATIONS);
        for (Station contextStation : contextStations) {
            if (contextStation.getId().equals(station.getId())) {
                contextStation.setNames(station.getNames());
                LOG.debug("Updating station in ServletContext was success");
            }
        }
        request.getServletContext().setAttribute(ApplicationConstants.CONTEXT_STATIONS, contextStations);
    }
}
