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

public class AdminAddStationCommand implements Command {
    
    private static final Logger LOG = Logger.getLogger(AdminAddStationCommand.class);

    private static final String REQUEST_STATION_NAMES = "stationNames";
    private static final String REQUEST_ERROR_CREATE = "errorCreate";
    
    private StationService stationService = new StationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Start AdminAddStationCommand");
        
        if (isValidRequestParameter(request)) {
            LOG.debug("Requests parameters are valid");
            Map<Long, String> stationNames = mapStationNames(request);
            
            Station station = new Station(stationNames);

            if (stationService.createStation(station)) {
                LOG.debug("Updating station into database was success");
                updateServletContext(station, request);
                return PagesConstants.REDIRECT_ADMIN_VIEW_ALL_STATIONS_PAGE;
            }
        }

        LOG.debug("Requests parameters are invalid");
        request.setAttribute(REQUEST_ERROR_CREATE, REQUEST_ERROR_CREATE);
        return PagesConstants.ADMIN_VIEW_ALL_STATIONS_PAGE;
    }

    private boolean isValidRequestParameter(HttpServletRequest request) {
        if (request.getParameterValues(REQUEST_STATION_NAMES) != null) {
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
        List<Station> contextStations = stationService.getAll();
        request.getServletContext().setAttribute(ApplicationConstants.CONTEXT_STATIONS, contextStations);
    }
}
