package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.StationDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.StationDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;

public class StationService {

    private static Logger LOG = Logger.getLogger(StationService.class);

    private StationDao stationDao = new StationDaoImpl();

    public List<Station> getAll() {
        LOG.info("Getting all stations");
        return stationDao.getAll();
    }
    
    public boolean delete(long stationId) {
        LOG.info("Deletig station by id :: " + stationId);
        return stationDao.delete(stationId);
    }

    public boolean updateStation(Station station) {
        LOG.info("Updating station");        
        return stationDao.update(station);
    }

    public boolean createStation(Station station) {
        LOG.info("Creating station");        
        return stationDao.create(station);
    }
    
}
