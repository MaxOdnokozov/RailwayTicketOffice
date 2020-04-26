package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.StopDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.StopDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.dto.DTOConverter;
import ua.nure.odnokozov.railway.ticket.office.dto.StopDTO;

public class StopService {
    
    private static final Logger LOG = Logger.getLogger(StopService.class);
    
    private StopDao stopDao = new StopDaoImpl();
    
    public List<StopDTO> getAllStopsByRouteId(long routeId){
        LOG.debug("Getting all stops of route by id :: " + routeId);
        return DTOConverter.toStopsDTO(stopDao.getAllByRouteId(routeId));
    }

    public void updateStopDateTime(Stop stop) {
        LOG.debug("Updating date and time of stop");
        stopDao.updateDateTime(stop);
    }

}
