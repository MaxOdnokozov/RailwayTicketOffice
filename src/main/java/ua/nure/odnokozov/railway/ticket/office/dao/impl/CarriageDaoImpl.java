package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.CarriageDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Carriage;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.CarriageBuilder;
import ua.nure.odnokozov.railway.ticket.office.enums.ComfortType;

public class CarriageDaoImpl extends AbstractDao<Carriage> implements CarriageDao {
    
    private static final Logger LOG = Logger.getLogger(CarriageDaoImpl.class);
    
    private static final String SQL_SELECT_CARRIAGE_BY_ID = "SELECT * FROM carriages WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_ALL_CARRIAGES = "SELECT * FROM carriages";
    private static final String SQL_INSERT_CARRIAGE = "INSERT INTO carriages VALUES(default, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_CARRIAGE_BY_ID = "DELETE FROM carriages WHERE id=? LIMIT 1";
    private static final String SQL_UPDATE_CARRIAGE = "UPDATE carriges Set model=?, comfort_type=?, "
            + "total_seats=? price_coefficient=? WHERE id=? LIMIT 1";
  
    private static final String COLUMN_CARRIAGE_ID = "id";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_COMFORT_TYPE = "comfort_type";
    private static final String COLUMN_TOTAL_SEATS = "total_seats";
    private static final String COLUMN_PRICE_COEFFICIENT = "price_coefficient";

    public Carriage getById(long id) {
        LOG.debug("Getting carriage by id from the database :: id = " + id);
        return getByParameter(SQL_SELECT_CARRIAGE_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    public EntityMapper<Carriage> getMapper() {
        LOG.debug("Mapping carriage from ResultSet");
        return resultSet -> CarriageBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_CARRIAGE_ID))
                .model(resultSet.getString(COLUMN_MODEL))
                .image(resultSet.getString(COLUMN_IMAGE))
                .totalSeats(resultSet.getInt(COLUMN_TOTAL_SEATS))
                .comfortType(ComfortType.valueOf(resultSet.getString(COLUMN_COMFORT_TYPE)))
                .priceCoefficient(resultSet.getBigDecimal(COLUMN_PRICE_COEFFICIENT))
                .build();
    }

    public Carriage create(Carriage carriage) {
        LOG.debug("Creating a new carriage in the database :: " + carriage);
        long generatedId = create(SQL_INSERT_CARRIAGE, ps -> {
            int count = 1;
            ps.setString(count++, carriage.getModel());
            ps.setString(count++, carriage.getImage());
            ps.setInt(count++, carriage.getTotalSeats());
            ps.setString(count++, carriage.getComfortType().getName());
            ps.setBigDecimal(count, carriage.getPriceCoefficient());
        });
        carriage.setId(generatedId);
        return carriage;
    }

    public boolean update(Carriage carriage) {
        LOG.debug("Updating carriage in the database :: " + carriage);
        int updatedRow = updateDelete(SQL_UPDATE_CARRIAGE, ps -> {
            int count = 1;
            ps.setString(count++, carriage.getModel());
            ps.setString(count++, carriage.getImage());
            ps.setInt(count++, carriage.getTotalSeats());
            ps.setString(count++, carriage.getComfortType().getName());
            ps.setBigDecimal(count, carriage.getPriceCoefficient());
            ps.setLong(count, carriage.getId());
        });
        return updatedRow == 1;
    }

    public boolean delete(long id) {
        LOG.debug("Deleting carriage from the database by id :: id = " + id);
        int updatedRow = updateDelete(SQL_DELETE_CARRIAGE_BY_ID, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    public List<Carriage> getAll() {
        LOG.debug("Getting all carriages from the database");
        return getAll(SQL_SELECT_ALL_CARRIAGES, getMapper());
    }
}