package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.StopDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.StopBuilder;

public class StopDaoImpl extends AbstractDao<Stop> implements StopDao {
    
    private static final Logger LOG = Logger.getLogger(StopDaoImpl.class);
    
    private static final String SQL_SELECT_STOP_BY_ID = "SELECT * FROM stops WHERE id=? LIMIT 1";
    private static final String SQL_INSERT_STOP = "INSERT INTO stops(route_id, station_id, stop_number, "
            + "arrival_date, arrival_time, departure_date, departure_time, price) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_STOP = "UPDATE stops SET route_id=?, station_id=?, stop_number=?, "
            + " arrival_date=?, arrival_time=?, departure_date=?, departure_time=?, price=? WHERE id=? LIMIT 1";
    private static final String SQL_DELETE_STOP_BY_ID = "DELETE FROM stops WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_ALL_STOPS_BY_ROUTE_ID = "SELECT * FROM stops WHERE route_id=? "
            + "ORDER BY stop_number";
    private static final String SQL_SELECT_ALL_STOPS_BY_ROUTE_CODE = "SELECT route_id, station_id, stop_number, "
            + "arrival_date, arrival_time, departure_date, departure_time, price FROM stops INNER JOIN routes ON code = ? "
            + "ORDER BY stop_number";
    private static final String SQL_SELECT_STOP_BY_ROUTE_ID_AND_STOP_NUMBER = "SELECT * FROM stops WHERE route_id=? "
            + "AND stop_number=? LIMIT 1";
    private static final String SQL_UPDATE_STOP_DATE_TIME = "UPDATE stops SET arrival_date=?, arrival_time=?, "
            + "departure_date=?, departure_time=? WHERE id=? LIMIT 1";
    
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROUTE_ID = "route_id";
    private static final String COLUMN_STATION_ID = "station_id";
    private static final String COLUMN_STOP_NUMBER = "stop_number";
    private static final String COLUMN_ARRIVAL_DATE = "arrival_date";
    private static final String COLUMN_ARRIVAL_TIME = "arrival_time";
    private static final String COLUMN_DEPARTURE_DATE = "departure_date";
    private static final String COLUMN_DEPARTURE_TIME = "departure_time";
    private static final String COLUMN_PRICE = "price";


    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    
    @Override
    public Stop getById(long id) {
        LOG.debug("Getting Stop by id from the database :: id = " + id);
        return getByParameter(SQL_SELECT_STOP_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    public EntityMapper<Stop> getMapper() {
        LOG.debug("Mapping Stop from ResultSet");
        return resultSet -> StopBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .routeId(resultSet.getLong(COLUMN_ROUTE_ID))
                .stationId(resultSet.getLong(COLUMN_STATION_ID))
                .stopNumber(resultSet.getInt(COLUMN_STOP_NUMBER))
                .arrivalDate(LocalDate.parse(resultSet.getString(COLUMN_ARRIVAL_DATE)))
                .arrivalTime(LocalTime.parse(resultSet.getString(COLUMN_ARRIVAL_TIME)))
                .departureDate(LocalDate.parse(resultSet.getString(COLUMN_DEPARTURE_DATE)))
                .departureTime(LocalTime.parse(resultSet.getString(COLUMN_DEPARTURE_TIME)))
                .price(resultSet.getBigDecimal(COLUMN_PRICE))
                .build();
    };

    @Override
    public boolean create(Stop entity) {
        LOG.debug("Creating a new Stop in the database :: " + entity);
        long generatedId = 0;
        generatedId = create(SQL_INSERT_STOP, ps -> {
            int count = 1;
            ps.setLong(count++, entity.getRouteId());
            ps.setLong(count++, entity.getStationId());
            ps.setInt(count++, entity.getStopNumber());
            ps.setString(count++, entity.getArrivalDate().toString());
            ps.setString(count++, entity.getArrivalTime().toString());
            ps.setString(count++, entity.getDepartureDate().toString());
            ps.setString(count++, entity.getDepartureTime().toString());
            ps.setBigDecimal(count, entity.getPrice());
        });
            return generatedId > 0;
    }

    @Override
    public boolean update(Stop entity) {
        LOG.debug("Updating Stop in the database :: " + entity);
        int updatedRow = updateDelete(SQL_UPDATE_STOP, ps -> {
            int count = 1;
            ps.setLong(count++, entity.getRouteId());
            ps.setLong(count++, entity.getStationId());
            ps.setInt(count++, entity.getStopNumber());
            ps.setString(count++, entity.getArrivalDate().toString());
            ps.setString(count++, entity.getArrivalTime().toString());
            ps.setString(count++, entity.getDepartureDate().toString());
            ps.setString(count++, entity.getDepartureTime().toString());
            ps.setBigDecimal(count++, entity.getPrice());
            ps.setLong(count, entity.getId());
        });
        return updatedRow == 1;
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Deleting Stop from the database :: " + id);
        int updatedRow = updateDelete(SQL_DELETE_STOP_BY_ID, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    @Override
    public List<Stop> getAllByRouteId(Long routeId) {
        LOG.debug("Getting all Stops by route id from the database :: route id = " + routeId);
        return getAllByParameter(SQL_SELECT_ALL_STOPS_BY_ROUTE_ID, ps -> ps.setLong(1, routeId),
                getMapper());
    }

    @Override
    public List<Stop> getAllByRouteCode(String code) {
        LOG.debug("Getting all Stops by route code from the database :: "
                + "route code = " + code);
        return getAllByParameter(SQL_SELECT_ALL_STOPS_BY_ROUTE_CODE,
                ps -> ps.setString(1, code), getMapper());
    }

    @Override
    public Stop getByRouteIdAndStopNumber(Long routeId, int stopNumber) {
        LOG.debug("Getting all Stops by route id and stopNumber from the database :: "
                + "route id = "+ routeId + ", stopNumber = " + stopNumber);
        return getByParameter(SQL_SELECT_STOP_BY_ROUTE_ID_AND_STOP_NUMBER, ps -> {
            int count = 1;
            ps.setLong(count++, routeId);
            ps.setInt(count, stopNumber);
        }, getMapper());
    }
    
    @Override
    public boolean createAllStops(List<Stop> stops) {
        LOG.debug("Creating Stops in the database");
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_STOP,
                        Statement.RETURN_GENERATED_KEYS)) {
                    for (Stop stop : stops) {
                        int count = 1;
                        statement.setLong(count++, stop.getRouteId());
                        statement.setLong(count++, stop.getStationId());
                        statement.setInt(count++, stop.getStopNumber());
                        statement.setString(count++, stop.getArrivalDate().toString());
                        statement.setString(count++, stop.getArrivalTime().toString());
                        statement.setString(count++, stop.getDepartureDate().toString());
                        statement.setString(count++, stop.getDepartureTime().toString());
                        statement.setBigDecimal(count, stop.getPrice());
                        statement.addBatch();
                    }
                    statement.executeBatch();             
                    connection.commit();
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback changes! Exception while creating Stops", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to create s", e);
        }
        return false;
    }

    @Override
    public boolean updateDateTime(Stop entity) {
        LOG.debug("Updating Stop DateTime in the database :: " + entity);
        int updatedRow = updateDelete(SQL_UPDATE_STOP_DATE_TIME, ps -> {
            int count = 1;
            ps.setString(count++, entity.getArrivalDate().toString());
            ps.setString(count++, entity.getArrivalTime().toString());
            ps.setString(count++, entity.getDepartureDate().toString());
            ps.setString(count++, entity.getDepartureTime().toString());
            ps.setLong(count, entity.getId());
        });
        return updatedRow == 1;
    }
}
