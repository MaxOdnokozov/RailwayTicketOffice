package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.SeatConditionsDao;
import ua.nure.odnokozov.railway.ticket.office.domain.SeatConditions;

public class SeatConditionsDaoImpl extends AbstractDao<SeatConditions> implements SeatConditionsDao {

    private static final Logger LOG = Logger.getLogger(SeatConditionsDaoImpl.class);

    private static final String SQL_SELECT_SEAT_CONDITIONS_BY_SEAT_ID = "SELECT seat_id, stop_number, is_free "
            + "FROM seats_conditions INNER JOIN stops ON stops.id=seats_conditions.stop_id WHERE seat_id=?";
    private static final String SQL_UPDATE_SEAT_CONDITIONS_BY_SEAT_ID_AND_STOPS_NUMBERS = "UPDATE seats_conditions "
            + "INNER JOIN stops ON stops.id=seats_conditions.stop_id AND stops.stop_number >= ? AND stops.stop_number < ? "
            + "SET is_free=?  WHERE seat_id=?;";

    private static final String COLUMN_SEAT_ID = "seat_id";
    private static final String COLUMN_STOP_NUMBER = "stop_number";
    private static final String COLUMN_IS_FREE = "is_free";

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public SeatConditions getBySeatId(Long seatId) {
        LOG.debug("Getting seatConditions by seatId :: " + seatId);
        return getByParameter(SQL_SELECT_SEAT_CONDITIONS_BY_SEAT_ID, ps -> ps.setLong(1, seatId), getMapper());
    }

    public EntityMapper<SeatConditions> getMapper() {
        return resultSet -> {
            Map<Integer, Boolean> conditions = new HashMap<>();
            long seatId = resultSet.getLong(COLUMN_SEAT_ID);
            resultSet.previous();
            while (resultSet.next()) {
                conditions.put(resultSet.getInt(COLUMN_STOP_NUMBER),
                        Boolean.valueOf(resultSet.getString(COLUMN_IS_FREE)));
            }
            return new SeatConditions(seatId, conditions);
        };
    }

    @Override
    public boolean updateBySeatsIdsAndStopNumbers(List<Long> seatsIds, int departureStopNumber,
            int arrivalStopNumber, boolean condition) {
        LOG.debug("Updating seats conditions");
        int updatingRows = seatsIds.size();
        LOG.trace("Updating row :: " + updatingRows);
        int[] result;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SEAT_CONDITIONS_BY_SEAT_ID_AND_STOPS_NUMBERS)) {
                    for(Long seatId : seatsIds) {
                        int count = 1;                        
                        statement.setInt(count++, departureStopNumber);
                        statement.setInt(count++, arrivalStopNumber);
                        statement.setString(count++, String.valueOf(condition));
                        statement.setLong(count, seatId);
                        statement.addBatch();
                    }
                    result = statement.executeBatch();
                    LOG.trace("result.lenght =" + result.length);
                }
                if(updatingRows == result.length) {
                    connection.commit();
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when updating seats conditions", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to update seats conditions", e);
        }
        LOG.debug("Rollback of all changes - updatingRows != result.length");
        return false;
    }
}
