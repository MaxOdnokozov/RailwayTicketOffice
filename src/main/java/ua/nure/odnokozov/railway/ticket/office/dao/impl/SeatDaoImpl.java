package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.SeatConditionsDao;
import ua.nure.odnokozov.railway.ticket.office.dao.SeatDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Seat;

import ua.nure.odnokozov.railway.ticket.office.domain.builder.SeatBuilder;

public class SeatDaoImpl extends AbstractDao<Seat> implements SeatDao {

    private static final Logger LOG = Logger.getLogger(SeatDaoImpl.class);
    
    private SeatConditionsDao seatConditionsDao = new SeatConditionsDaoImpl();

    private static final String SQL_SELECT_ALL_SEATS_BY_ID = "SELECT * FROM seats WHERE route_carriage_id=?";
    private static final String SQL_SELECT_SEAT_BY_ID = "SELECT id, route_carriage_id, seat_number "
            + "FROM seats WHERE seats.id=? LIMIT 1";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROUTE_CARRIAGE_ID = "route_carriage_id";
    private static final String COLUMN_SEAT_NUMBER = "seat_number";

    @Override
    public List<Seat> getAllByRouteCarriageId(long id) {
        LOG.trace("Getting seat of route by route id :: " + id);
        return getAllByParameter(SQL_SELECT_ALL_SEATS_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    public EntityMapper<Seat> getMapper() {
        return resultSet -> SeatBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .routeCarriageId(resultSet.getLong(COLUMN_ROUTE_CARRIAGE_ID))
                .number(resultSet.getInt(COLUMN_SEAT_NUMBER))
                .seatConditions(seatConditionsDao.getBySeatId(resultSet.getLong(COLUMN_ID)))
                .build();
    }

    @Override
    public Seat getById(long seatId) {
        LOG.trace("Getting seat by id :: " + seatId);
        return getByParameter(SQL_SELECT_SEAT_BY_ID, ps -> ps.setLong(1, seatId), getMapper());
    }
}
