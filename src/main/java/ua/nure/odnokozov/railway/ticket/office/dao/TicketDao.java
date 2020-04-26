package ua.nure.odnokozov.railway.ticket.office.dao;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Ticket;

public interface TicketDao {

    List<Ticket> getAllByUserId(long userId);

    Ticket getById(Long ticketId);
    
    boolean create(Ticket ticket);
    
    boolean update(Ticket ticket);
    
    boolean delete(long ticketId);

    boolean createAllTicket(List<Ticket> tickets);

    List<Ticket> getAllByRouteIdAndUserId(long routeId, long userId);

    List<Ticket> getAllByRouteIdAndUserId(long routeId, long userId, boolean isPaid);

    boolean updateAllByIds(List<Long> ticketsIds, boolean b);
}
