package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.RouteCarriageDao;
import ua.nure.odnokozov.railway.ticket.office.dao.RouteDao;
import ua.nure.odnokozov.railway.ticket.office.dao.StopDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Seat;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.RouteBuilder;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.SeatBuilder;

public class RouteDaoImpl extends AbstractDao<Route> implements RouteDao {

    private static final Logger LOG = Logger.getLogger(RouteDaoImpl.class);

    private static final String SQL_SELECT_ROUTE_BY_ID = "SELECT * FROM routes WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_ROUTE_BY_CODE = "SELECT * FROM routes WHERE code=? LIMIT 1";
    private static final String SQL_SELECT_ALL_ROUTES = "SELECT * FROM routes";
    private static final String SQL_INSERT_INTO_ROUTES = "INSERT INTO routes(code) VALUES(?)";
    private static final String SQL_UPDATE_ROUTE_CODE = "UPDATE routes Set code=? WHERE id=? LIMIT 1";
    private static final String SQL_UPDATE_ROUTE_CANCELED_STATUS = "UPDATE routes Set is_canceled=? WHERE id=? LIMIT 1";
    private static final String SQL_DELETE_ROUTE = "DELETE FROM routes WHERE NOT EXISTS (SELECT * FROM tickets "
            + "WHERE routes.id = tickets.route_id) AND routes.is_canceled = 'true' AND id=? LIMIT 1";
    private static final String SQL_INSERT_INTO_STOPS = "INSERT INTO stops(route_id, station_id, stop_number, "
            + "arrival_date, arrival_time, departure_date, departure_time, price) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_INTO_SEATS = "INSERT INTO seats(route_carriage_id, seat_number) VALUES(?, ?)";
    private static final String SQL_INSERT_INTO_ROUTE_CARRIAGES = "INSERT INTO route_carriages(route_id, carriage_id, "
            + "number) VALUES(?, ?, ?)";
    private static final String SQL_INSERT_INTO_SEATS_CONDITIONS = "INSERT INTO seats_conditions(seat_id, stop_id) VALUES(?, ?)";
    private static final String SQL_INSERT_INTO_PRICES = "INSERT INTO prices(route_carriage_id, stop_id, price) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL_ROUTES_BY_DATE_AND_STATIONS_IDS = "call select_routes_by_stations_and_date(?, ?, ?)";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CODE = "code";
    private static final String COLUMN_IS_CANCELED = "is_canceled";



    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    private StopDao stopDao = new StopDaoImpl();
    private RouteCarriageDao routeCarriageDao = new RouteCarriageDaoImpl();

    @Override
    public Route getById(long id) {
        LOG.trace("Obtaining route by id from the database");
        return getByParameter(SQL_SELECT_ROUTE_BY_ID, ps -> ps.setLong(1, id), getMapper());
    }

    @Override
    public Route getByIdWithoutSeats(long id) {
        LOG.trace("Obtaining route by id from the database");
        return getByParameter(SQL_SELECT_ROUTE_BY_ID, ps -> ps.setLong(1, id), getMapperWithoutSeats());
    }

    public EntityMapper<Route> getMapper() {
        return resultSet -> RouteBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .code(resultSet.getString(COLUMN_CODE))
                .isCanceled(Boolean.valueOf(resultSet.getString(COLUMN_IS_CANCELED)))
                .stops(stopDao.getAllByRouteId(resultSet.getLong(COLUMN_ID)))
                .routeCarriages(routeCarriageDao.getAllByRouteId(resultSet.getLong(COLUMN_ID)))
                .build();
    }
    
    public EntityMapper<Route> getMapperWithoutSeats() {
        return resultSet -> RouteBuilder.getInstance()
                .id(resultSet.getLong(COLUMN_ID))
                .code(resultSet.getString(COLUMN_CODE))
                .isCanceled(Boolean.valueOf(resultSet.getString(COLUMN_IS_CANCELED)))
                .stops(stopDao.getAllByRouteId(resultSet.getLong(COLUMN_ID)))
                .routeCarriages(routeCarriageDao.getAllByRouteIdWithoutSeats(resultSet.getLong(COLUMN_ID)))
                .build();
    }

    @Override
    public List<Route> getAllByCode(String code) {
        LOG.trace("Obtaining route by code from the database");
        return getAllByParameter(SQL_SELECT_ROUTE_BY_CODE, ps -> ps.setString(1, code), getMapper());
    }

    @Override
    public List<Route> getAll() {
        LOG.trace("Obtaining all routes from the database");
        return getAll(SQL_SELECT_ALL_ROUTES, getMapper());
    }

    @Override
    public boolean create(Route route) {
        LOG.debug("Creating a new route in the database :: " + route);
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                long routeId = create(connection, SQL_INSERT_INTO_ROUTES, ps -> ps.setString(1, route.getCode()));
                route.setId(routeId);
                
                LOG.trace("Creating stops in database");
                List<Stop> stops = createStops(connection, route);

                LOG.trace("Creating routeCarriages in database");
                List<RouteCarriage> routeCarriages = createRouteCarriages(connection, route);
                
                LOG.trace("Creating seats for all routeCarriages in database");
                List<Seat> seats = createAllSeats(connection, routeCarriages);
                
                LOG.trace("Creating prices for all routeCarriages and all stops in database");                
                createPrices(connection, routeCarriages, stops);
                
                LOG.trace("Creating seatsStatuses for all seats and all stops in database");                
                createSeatConditions(connection, seats, stops);

                connection.commit();
                LOG.debug("Creating a new route was success");
                return true;
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when creating an entity", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to create entity", e);
        }
        return false;
    }

    private List<Stop> createStops(Connection connection, Route route) throws SQLException {
        List<Stop> stops = route.getStops();
        List<Long> stopsIds = createAll(connection, SQL_INSERT_INTO_STOPS, ps -> {
            for (Stop stop : stops) {
                int count = 1;
                ps.setLong(count++, route.getId());
                ps.setLong(count++, stop.getStationId());
                ps.setInt(count++, stop.getStopNumber());
                ps.setString(count++, stop.getArrivalDate().toString());
                ps.setString(count++, stop.getArrivalTime().toString());
                ps.setString(count++, stop.getDepartureDate().toString());
                ps.setString(count++, stop.getDepartureTime().toString());
                ps.setBigDecimal(count, stop.getPrice());
                ps.addBatch();
            }
        });
        for (int i = 0; i < stopsIds.size(); i++) {
            stops.get(i).setId(stopsIds.get(i));
        }
        return stops;
    }

    private List<RouteCarriage> createRouteCarriages(Connection connection, Route route) throws SQLException {
        List<RouteCarriage> routeCarriages = route.getRouteCarriages();
        List<Long> routeCarriagesIds = createAll(connection, SQL_INSERT_INTO_ROUTE_CARRIAGES, ps -> {
            for (RouteCarriage routeCarriage : routeCarriages) {
                int count = 1;
                ps.setLong(count++, route.getId());
                ps.setLong(count++, routeCarriage.getCarriage().getId());
                ps.setInt(count, routeCarriage.getNumber());
                ps.addBatch();
            }
        });
        for (int i = 0; i < routeCarriages.size(); i++) {
            routeCarriages.get(i).setId(routeCarriagesIds.get(i));
        }
        return routeCarriages;
    }

    private List<Seat> createAllSeats(Connection connection, List<RouteCarriage> routeCarriages) throws SQLException {
        List<Seat> seats = initSeats(routeCarriages);
        List<Long> seatsIds = createAll(connection, SQL_INSERT_INTO_SEATS, ps -> {
            for (Seat seat : seats) {
                int count = 1;
                ps.setLong(count++, seat.getRouteCarriageId());
                ps.setInt(count, seat.getNumber());
                ps.addBatch();
            }
        });
        for (int i = 0; i < seats.size(); i++) {
            seats.get(i).setId(seatsIds.get(i));
        }
        return seats;
    }

    private List<Seat> initSeats(List<RouteCarriage> routeCarriages) {
        List<Seat> seats = new ArrayList<>();
        for (RouteCarriage routeCarriage : routeCarriages) {
            int totalSeats = routeCarriage.getCarriage().getTotalSeats();
            for (int i = 1; i <= totalSeats; i++) {
                seats.add(SeatBuilder.getInstance().routeCarriageId(routeCarriage.getId()).number(i).build());
            }
        }
        return seats;
    }
    
    private void createPrices(Connection connection, List<RouteCarriage> routeCarriages, List<Stop> stops) throws SQLException {
        createAll(connection, SQL_INSERT_INTO_PRICES, ps-> {
            for(RouteCarriage routeCarriage: routeCarriages ) {
                for (int i = 0; i < stops.size() - 1; i++) {
                    int count = 1;
                    ps.setLong(count++, routeCarriage.getId());
                    ps.setLong(count++, stops.get(i).getId());
                    ps.setBigDecimal(count, routeCarriage.getCarriage().getPriceCoefficient().multiply(stops.get(i).getPrice()));
                    ps.addBatch();
                }
            }
        });        
    }
    
    private void createSeatConditions(Connection connection, List<Seat> seats, List<Stop> stops) throws SQLException {
        createAll(connection, SQL_INSERT_INTO_SEATS_CONDITIONS, ps -> {
            for (Seat seat : seats) {
                for (int i = 0; i < stops.size() - 1; i++) {
                    int count = 1;
                    ps.setLong(count++, seat.getId());
                    ps.setLong(count, stops.get(i).getId());
                    ps.addBatch();
                }
            }
        });
    }

    @Override
    public boolean updateRouteCode(long routeId, String code) {
        LOG.debug("Updating route code in the database");
        int updatedRow = updateDelete(SQL_UPDATE_ROUTE_CODE, ps -> {
            int count = 1;
            ps.setString(count++, code);
            ps.setLong(count, routeId);
        });
        return updatedRow == 1;
    }
    
    @Override
    public boolean updateRouteCanceledStatus(long routeId, boolean isCanceled) {
        LOG.debug("Updating route isCanceled status in the database");
        int updatedRow = updateDelete(SQL_UPDATE_ROUTE_CANCELED_STATUS, ps -> {
            int count = 1;
            ps.setString(count++, String.valueOf(isCanceled));
            ps.setLong(count, routeId);
        });
        return updatedRow == 1;
    }

    @Override
    public boolean delete(long id) {
        LOG.debug("Deleting route from the database by id :: id = " + id);
        int updatedRow = updateDelete(SQL_DELETE_ROUTE, ps -> ps.setLong(1, id));
        return updatedRow == 1;
    }

    @Override
    public List<Route> getRoutesOnDate(long departureStationId, long arrivalStationId, LocalDate date) {
        LOG.debug("Getting all routes by date=" + date +" and stations id " + departureStationId + " and "+ arrivalStationId);
        return getAllByParameter(SQL_SELECT_ALL_ROUTES_BY_DATE_AND_STATIONS_IDS, ps -> {
            int count = 1;
            ps.setLong(count++, departureStationId);
            ps.setLong(count++, arrivalStationId);
            ps.setString(count, date.toString());            
        }, getMapper());
    }

    @Override
    public List<Route> getRoutesOnToday(long departureStationId, long arrivalStationId, LocalDateTime date) {
        // TODO Auto-generated method stub
        return null;
    }
}
