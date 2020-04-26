package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.ConnectionFactory;
import ua.nure.odnokozov.railway.ticket.office.dao.RouteDao;
import ua.nure.odnokozov.railway.ticket.office.dao.SeatDao;
import ua.nure.odnokozov.railway.ticket.office.dao.TicketDao;
import ua.nure.odnokozov.railway.ticket.office.dao.UserDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Ticket;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.TicketBuilder;

public class TicketDaoImpl extends AbstractDao<Ticket> implements TicketDao {

    private static final Logger LOG = Logger.getLogger(TicketDaoImpl.class);

    private static final String SQL_SELECT_ALL_BY_USER_ID = "SELECT * FROM tickets WHERE user_id=? AND is_paid='true'";
    private static final String SQL_SELECT_TICKET_BY_ID = "SELECT * FROM tickets WHERE id=? LIMIT 1";
    private static final String SQL_INSERT_INTO_TICKETS = "INSERT INTO tickets(route_id, user_id, seat_id, "
            + "departure_stop_id, arrival_stop_id, price, is_paid, date_created) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_TICKET_BY_ID = "DELETE FROM tickets WHERE id=? LIMIT 1";
    private static final String SQL_SELECT_ALL_TICKETS_BY_USER_ID_AND_ROUTE_ID = "SELECT * FROM tickets WHERE user_id=? "
            + "AND route_id=?";
    private static final String SQL_SELECT_ALL_TICKETS_BY_USER_ID_AND_ROUTE_ID_AND_IS_PAID = "SELECT * FROM tickets "
            + "WHERE user_id=? AND route_id=? AND is_paid=? AND date_created + INTERVAL 5 MINUTE > NOW()";
    private static final String SQL_UPDATE_TICKET_STATUS_IS_PAID = "UPDATE tickets SET is_paid=? WHERE id=? "
            + "AND date_created + INTERVAL 5 MINUTE > NOW()";
    private static final String SQL_SELECT_ALL_CANCELED_TICKETS = "SELECT * FROM tickets INNER JOIN routes "
            + "ON routes.id = tickets.route_id WHERE is_canceled = true";
    private static final String SQL_SELECT_ALL_BY_USER_NAME = "SELECT * FROM tickets INNER JOIN users "
            + "ON users.id=tickets.user_id WHERE users.first_name=? AND users.last_name=?";
    private static final String SQL_SELECT_ALL_BY_USER_EMAIL = "SELECT * FROM tickets INNER JOIN users "
            + "ON users.id=tickets.user_id WHERE users.email=?";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROUTE_ID = "route_id";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_SEAT_ID = "seat_id";
    private static final String COLUMN_IS_PAID = "is_paid";
    private static final String COLUMN_DEPARTURE_STOP_ID = "departure_stop_id";
    private static final String COLUMN_ARRIVAL_STOP_ID = "arrival_stop_id";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DATE_CREATED = "date_created";
    
    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    private RouteDao routeDao = new RouteDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private SeatDao seatDao = new SeatDaoImpl();

    @Override
    public List<Ticket> getAllByUserId(long userId) {
        LOG.trace("Getting all tickets by user id :: " + userId);
        return getAllByParameter(SQL_SELECT_ALL_BY_USER_ID, ps -> ps.setLong(1, userId), getMapper());
    }

    public EntityMapper<Ticket> getMapper() {
        return resultSet -> TicketBuilder.getInstance().id(resultSet.getLong(COLUMN_ID))
                .route(routeDao.getByIdWithoutSeats(resultSet.getLong(COLUMN_ROUTE_ID)))
                .user(userDao.getById(resultSet.getLong(COLUMN_USER_ID)))
                .seat(seatDao.getById(resultSet.getLong(COLUMN_SEAT_ID)))
                .departureStopId(resultSet.getLong(COLUMN_DEPARTURE_STOP_ID))
                .arrivalStopId(resultSet.getLong(COLUMN_ARRIVAL_STOP_ID)).price(resultSet.getBigDecimal(COLUMN_PRICE))
                .isPaid(resultSet.getBoolean(COLUMN_IS_PAID))
                .createdDateTime(LocalDateTime.parse(resultSet.getString(COLUMN_DATE_CREATED),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    @Override
    public Ticket getById(Long ticketId) {
        LOG.trace("Getting ticket by id :: " + ticketId);
        return getByParameter(SQL_SELECT_TICKET_BY_ID, ps -> ps.setLong(1, ticketId), getMapper());
    }

    @Override
    public boolean create(Ticket ticket) {
        LOG.trace("Creating a new ticket into database");
        long generatedId = create(SQL_INSERT_INTO_TICKETS, ps -> {
            int count = 1;
            ps.setLong(count++, ticket.getRoute().getId());
            ps.setLong(count++, ticket.getUser().getId());
            ps.setLong(count++, ticket.getSeat().getId());
            ps.setLong(count++, ticket.getDepartureStopId());
            ps.setLong(count++, ticket.getArrivalStopId());
            ps.setBigDecimal(count++, ticket.getPrice());
            ps.setString(count++, String.valueOf(ticket.isPaid()));
            ps.setString(count, LocalDateTime.now().toString());
        });
        return generatedId > 0;
    }

    @Override
    public boolean createAllTicket(List<Ticket> tickets) {
        LOG.trace("Creating new tickets into database");
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                List<Long> generatedIds = createAll(connection, SQL_INSERT_INTO_TICKETS, ps -> {
                    for (Ticket ticket : tickets) {
                        int count = 1;
                        ps.setLong(count++, ticket.getRoute().getId());
                        ps.setLong(count++, ticket.getUser().getId());
                        ps.setLong(count++, ticket.getSeat().getId());
                        ps.setLong(count++, ticket.getDepartureStopId());
                        ps.setLong(count++, ticket.getArrivalStopId());
                        ps.setBigDecimal(count++, ticket.getPrice());
                        ps.setString(count++, String.valueOf(ticket.isPaid()));
                        ps.setString(count, LocalDateTime.now().toString());
                        ps.addBatch();
                    }
                });
                if (tickets.size() == generatedIds.size()) {
                    connection.commit();
                    LOG.trace("Creating new tickets was success");
                    return true;
                }
            } catch (SQLException e) {
                connection.rollback();
                LOG.error("Rollback of all changes due to an exception when creating tickets", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed creating tickets", e);
        }
        return false;
    }

    @Override
    public boolean delete(long ticketId) {
        LOG.trace("Deleting ticket by id");
        int updatedRow = updateDelete(SQL_DELETE_TICKET_BY_ID, ps -> ps.setLong(1, ticketId));
        return updatedRow == 1;
    }

    @Override
    public List<Ticket> getAllByRouteIdAndUserId(long routeId, long userId) {
        LOG.trace("Getting all tickets bu user id and route id from datebase");
        return getAllByParameter(SQL_SELECT_ALL_TICKETS_BY_USER_ID_AND_ROUTE_ID, ps -> {
            int count = 1;
            ps.setLong(count++, userId);
            ps.setLong(count, routeId);
        }, getMapper());
    }

    @Override
    public List<Ticket> getAllByRouteIdAndUserId(long routeId, long userId, boolean isPaid) {
        LOG.trace("Getting all tickets bu user id and route id from datebase");
        return getAllByParameter(SQL_SELECT_ALL_TICKETS_BY_USER_ID_AND_ROUTE_ID_AND_IS_PAID, ps -> {
            int count = 1;
            ps.setLong(count++, userId);
            ps.setLong(count++, routeId);
            ps.setString(count, String.valueOf(isPaid));
        }, getMapper());
    }

    @Override
    public boolean updateAllByIds(List<Long> ticketsIds, boolean isPaid) {
        LOG.trace("Updating tickets isPaid status by ids");
        int updatingRows = ticketsIds.size();
        LOG.trace("Updating row :: " + updatingRows);
        int[] result;
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TICKET_STATUS_IS_PAID)) {
                    for(Long ticketId : ticketsIds) {
                        int count = 1;
                        statement.setString(count++, String.valueOf(isPaid));
                        statement.setLong(count, ticketId);
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
                LOG.error("Rollback of all changes due to an exception when updating tickets isPaid statuses", e);
            }
        } catch (SQLException e) {
            LOG.error("Couldn't close connection! Failed to update tickets isPaid statuses", e);
        }
        LOG.debug("Rollback of all changes - updatingRows != result.length");
        return false;
    }

    @Override
    public List<Ticket> getAllCanceledTickets() {
        return getAll(SQL_SELECT_ALL_CANCELED_TICKETS, getMapper());
    }

    @Override
    public List<Ticket> getAllByUserName(String firstName, String lastName) {
        return getAllByParameter(SQL_SELECT_ALL_BY_USER_NAME, ps -> {
            int count = 1;
            ps.setString(count++, firstName);
            ps.setString(count, lastName);
        },getMapper());
    }

    @Override
    public List<Ticket> getAllByEmail(String email) {
        return getAllByParameter(SQL_SELECT_ALL_BY_USER_EMAIL, ps -> ps.setString(1, email), getMapper());
    }
}
