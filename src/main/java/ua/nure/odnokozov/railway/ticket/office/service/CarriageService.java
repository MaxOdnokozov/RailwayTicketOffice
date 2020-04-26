package ua.nure.odnokozov.railway.ticket.office.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.CarriageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.CarriageDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;

public class CarriageService {

    private static final Logger LOG = Logger.getLogger(CarriageService.class);
    
    private CarriageDao carriageDao = new CarriageDaoImpl();

    public List<Carriage> getAll() {
        LOG.info("Getting all carriages");
        return carriageDao.getAll();
    }
    
    public Optional<Carriage> addCarriage(Carriage carraige) {
        LOG.info("Adding Carriage");
        return Optional.of(carriageDao.create(carraige));
    }

    public boolean deleteCarriage(long id) {
        LOG.info("Deletion Carriage");
        return carriageDao.delete(id);
    }

    public Optional<Carriage> getCarriage(long id) {
        return Optional.of(carriageDao.getById(id));
    }
}
