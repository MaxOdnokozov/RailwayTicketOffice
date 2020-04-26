package ua.nure.odnokozov.railway.ticket.office.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ua.nure.odnokozov.railway.ticket.office.dao.TicketDao;
import ua.nure.odnokozov.railway.ticket.office.dao.impl.TicketDaoImpl;
import ua.nure.odnokozov.railway.ticket.office.domain.Ticket;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.RouteBuilder;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.SeatBuilder;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.TicketBuilder;
import ua.nure.odnokozov.railway.ticket.office.domain.builder.UserBuilder;
import ua.nure.odnokozov.railway.ticket.office.dto.DTOConverter;
import ua.nure.odnokozov.railway.ticket.office.dto.RouteDTO;
import ua.nure.odnokozov.railway.ticket.office.dto.TicketDTO;

public class TicketService {

    private static final Logger LOG = Logger.getLogger(TicketService.class);

    private TicketDao ticketDao = new TicketDaoImpl();

    public boolean createTickets(RouteDTO route, long userId, int routeCarriageNumber, List<Long> seatsIds) {
        LOG.info("Creating user tickets");
        List<Ticket> tickets = new ArrayList<>();
        for (Long seatId : seatsIds) {
            tickets.add(TicketBuilder.getInstance().route(RouteBuilder.getInstance().id(route.getId()).build())
                    .user(UserBuilder.getInstance().id(userId).build())
                    .seat(SeatBuilder.getInstance().id(seatId).build())
                    .departureStopId(route.getDepartureStop().getId()).arrivalStopId(route.getArrivalStop().getId())
                    .price(getPrice(route, routeCarriageNumber)).isPaid(false).build());
        }
        return ticketDao.createAllTicket(tickets);
    }

    private BigDecimal getPrice(RouteDTO route, int routeCarriageNumber) {
        return route.getRouteCarriages().stream()
                .filter(routeCarriage -> routeCarriage.getNumber() == routeCarriageNumber).findFirst().get().getPrice();
    }

    public List<TicketDTO> getAllByRouteIdAndUserID(long routeId, long userId) {
        LOG.info("Getting all user tickets by route id");
        return DTOConverter.toTicketsDTO(ticketDao.getAllByRouteIdAndUserId(routeId, userId));
    }

    public List<TicketDTO> getAllByUserId(long userId) {
        LOG.info("Getting all tickets by user id" + userId);
        List<Ticket> tickets = ticketDao.getAllByUserId(userId);
        LOG.trace("Tickets :: " + tickets);
        return DTOConverter.toTicketsDTO(tickets);
    }

    public List<TicketDTO> getAllUnpaidTickets(long routeId, long userId) {
        LOG.info("Getting all user unpaid tickets by route id");
        List<Ticket> tickets = ticketDao.getAllByRouteIdAndUserId(routeId, userId, false);
        LOG.trace("Tickets :: " + tickets);
        return DTOConverter.toTicketsDTO(tickets);
    }

    public BigDecimal getTotalPrice(List<TicketDTO> tickets) {
        BigDecimal result = BigDecimal.ZERO;
        for (TicketDTO ticket : tickets) {
            result = result.add(ticket.getPrice());
        }
        return result;
    }

    public boolean payTickets(List<Long> ticketsIds) {
        LOG.info("Updating paid status of tickets by ids");
        return ticketDao.updateAllByIds(ticketsIds, true);
    }

    public List<TicketDTO> getAllActualsByUserId(long userId) {
        List<TicketDTO> tickets = getAllByUserId(userId);
        LOG.info("Getting actual tickets");
        return tickets.stream().filter(
                ticket -> LocalDate.now().compareTo(ticket.getRoute().getDepartureStop().getDepartureDate()) <= 0)
                .collect(Collectors.toList());
    }
}
