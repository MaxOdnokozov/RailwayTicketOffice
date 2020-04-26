package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.dao.CarriageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.PriceDao;
import ua.nure.odnokozov.railway.ticket.office.dao.RouteCarriageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.SeatDao;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.RouteCarriageBuilder;

public class RouteCarriageDaoImpl extends AbstractDao<RouteCarriage> implements RouteCarriageDao {

    private static final String SQL_SELECT_ALL_ROUTE_CARRIAGES_BY_ROUTE_ID = "SELECT * FROM route_carriages WHERE route_id=?";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROUTE_ID = "route_id";
    private static final String COLUMN_CARRIAGE_ID = "carriage_id";
    private static final String COLUMN_NUMBER = "number";
    private static final String SQL_SELECT_ROUTE_CARRIAGE_BY_ROUTE_ID_AND_CARRIAGE_NUMBER = "SELECT * FROM route_carriages "
            + "WHERE route_id=? AND number=? LIMIT 1";

    private CarriageDao carriageDao = new CarriageDaoImpl();
    private PriceDao priceDao = new PriceDaoImpl(); 
    private SeatDao seatDao = new SeatDaoImpl();
    
    @Override
    public List<RouteCarriage> getAllByRouteId(long routeId) {
        return getAllByParameter(SQL_SELECT_ALL_ROUTE_CARRIAGES_BY_ROUTE_ID, ps -> ps.setLong(1, routeId), getMapper());
    }

    @Override
    public List<RouteCarriage> getAllByRouteIdWithoutSeats(long routeId) {
        return getAllByParameter(SQL_SELECT_ALL_ROUTE_CARRIAGES_BY_ROUTE_ID, ps -> ps.setLong(1, routeId), getMapperWithoutSeats());
    }

    private EntityMapper<RouteCarriage> getMapper() {
        return resultSet -> RouteCarriageBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .routeId(resultSet.getLong(COLUMN_ROUTE_ID))
                .carriage(carriageDao.getById(resultSet.getLong(COLUMN_CARRIAGE_ID)))
                .number(resultSet.getInt(COLUMN_NUMBER))
                .price(priceDao.getByRouteCarriageId(resultSet.getLong(COLUMN_ID)))
                .seats(seatDao.getAllByRouteCarriageId(resultSet.getLong(COLUMN_ID)))
                .build();
    }
    
    private EntityMapper<RouteCarriage> getMapperWithoutSeats() {
        return resultSet -> RouteCarriageBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .routeId(resultSet.getLong(COLUMN_ROUTE_ID))
                .carriage(carriageDao.getById(resultSet.getLong(COLUMN_CARRIAGE_ID)))
                .number(resultSet.getInt(COLUMN_NUMBER))
                .price(priceDao.getByRouteCarriageId(resultSet.getLong(COLUMN_ID)))
                .build();
    }

    @Override
    public RouteCarriage getByRouteIdAndCarriageNumber(long routeId, int number) {
        return getByParameter(SQL_SELECT_ROUTE_CARRIAGE_BY_ROUTE_ID_AND_CARRIAGE_NUMBER, ps ->{
            int count = 1;
            ps.setLong(count++, routeId);
            ps.setInt(count++, number);
        }, getMapper());
    }
}
