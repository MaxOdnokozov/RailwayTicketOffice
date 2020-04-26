package ua.nure.odnokozov.railway.ticket.office.dao;

import ua.nure.odnokozov.railway.ticket.office.domain.Price;

public interface PriceDao {

    Price getByRouteCarriageId(long seatId);
    
}
