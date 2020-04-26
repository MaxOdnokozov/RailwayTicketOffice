package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.StationDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Station;

public class StationDaoImpl extends AbstractDao<Station> implements StationDao {

    private static final Logger LOG = Logger.getLogger(StationDaoImpl.class);

    private static final String SQL_SELECT_STATION_BY_ID = "SELECT * FROM stations_names WHERE station_id=? LIMIT 1";
    private static final String SQL_INSERT_INTO_STATIONS = "INSERT INTO stations VALUES()";
    private static final String SQL_INSERT_INTO_STATIONS_NAMES = "INSERT INTO stations_names VALUES(?, ?, ?)";
    private static final String SQL_DELETE_STATION_BY_ID = "DELETE FROM stations WHERE NOT EXISTS (SELECT * FROM stops "
            + "WHERE stations.id = stops.station_id) AND id=? LIMIT 1";
    private static final String SQL_SELECT_ALL_STATIONS = "SELECT * FROM stations_names ORDER BY station_id";
    private static final String SQL_UPDATE_STATION = "UPDATE stations_names SET name=? WHERE station_id=? AND language_id=?";

    private static final String COLUMN_ID = "station_id";
    private static final String COLUMN_LANGUAGE_ID = "language_id";
    private static final String COLUMN_NAME = "name";

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public Station getById(long id) {
        LOG.debug("Getting station by id from the database :: id = " + id);
        return getByParameter(SQL_SELECT_STATION_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    public EntityMapper<Station> getMapper() {
            LOG.trace("Mapping station(s) from ResultSet");
        return resultSet -> {
            Station station = new Station();
            Map<Long, String> names = new HashMap<>();
            station.setId(resultSet.getLong(COLUMN_ID));
            names.put(resultSet.getLong(COLUMN_LANGUAGE_ID), resultSet.getString(COLUMN_NAME));
            while (resultSet.next()) {
                if (resultSet.getLong(COLUMN_ID) != station.getId()) {
                    resultSet.previous();
                    break;
                } else {
                    names.put(resultSet.getLong(COLUMN_LANGUAGE_ID), resultSet.getString(COLUMN_NAME));
                }
            }
            station.setNames(names);
                LOG.debug("Mapped station :: " + station);
            return station;
        };
    }

    @Override
    public boolean create(Station entity) {
        LOG.debug("Creating a new station in the database :: " + entity);
        long generatedId = 0;
        int insertedRows = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                generatedId = insertStation(connection);
                if (generatedId > 0) {
                    entity.setId(generatedId);
                    insertedRows = insertStationsNames(connection, entity);
                }
                if (entity.getNames().size() == insertedRows) {
                    connection.commit();
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback changes! Exception while creating a new station", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to create a new station", e);
        }
        return false;
    }

    private Long insertStation(Connection connection) throws SQLException {
        long generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_STATIONS,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    generatedId = resultSet.getLong(1);
                }
            }
        }
        return generatedId;
    }

    private int insertStationsNames(Connection connection, Station entity) throws SQLException {
        int insertedRows;
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_STATIONS_NAMES)) {
            for (Map.Entry<Long, String> entry : entity.getNames().entrySet()) {
                int count = 1;
                statement.setLong(count++, entity.getId());
                statement.setLong(count++, entry.getKey());
                statement.setString(count, entry.getValue());
                statement.addBatch();
            }
            insertedRows = statement.executeBatch().length;
        }
        return insertedRows;
    }

    @Override
    public boolean update(Station entity) {
        LOG.debug("Updating station in the database :: " + entity);
        int updatedRows;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATION)) {
                    for (Map.Entry<Long, String> entry : entity.getNames().entrySet()) {
                        int count = 1;
                        statement.setString(count++, entry.getValue());
                        statement.setLong(count++, entity.getId());
                        statement.setLong(count, entry.getKey());
                        statement.addBatch();
                    }
                    updatedRows = statement.executeBatch().length;
                }
                if (entity.getNames().size() == updatedRows) {
                    connection.commit();
                    LOG.debug("Updated  succesful! counts of updated rows :: " + updatedRows);
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback changes! Exception while updating an entity", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to update entity", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Deleting station from the database by id :: id = " + id);
        int updatedRow = updateDelete(SQL_DELETE_STATION_BY_ID, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    @Override
    public List<Station> getAll() {
        LOG.debug("Getting all stations from the database");
        return getAll(SQL_SELECT_ALL_STATIONS, getMapper());
    }
}
