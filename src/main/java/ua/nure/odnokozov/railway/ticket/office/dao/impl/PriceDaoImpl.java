package ua.nure.odnokozov.railway.ticket.office.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import ua.nure.odnokozov.railway.ticket.office.dao.PriceDao;
import ua.nure.odnokozov.railway.ticket.office.domain.Price;

public class PriceDaoImpl extends AbstractDao<Price> implements PriceDao {

    private static final String SQL_SELECT_PRICE_BY_SEAT_ID = "SELECT prices.route_carriage_id, stops.stop_number, "
            + "prices.price FROM prices INNER JOIN stops ON prices.stop_id = stops.id WHERE route_carriage_id = ?";

    private static final String COLUMN_ROUTE_CARRIAGE_ID = "route_carriage_id";
    private static final String COLUMN_STOP_NUMBER = "stop_number";
    private static final String COLUMN_PRICE = "price";

    @Override
    public Price getByRouteCarriageId(long routeCarriageId) {
        return getByParameter(SQL_SELECT_PRICE_BY_SEAT_ID, ps -> ps.setLong(1, routeCarriageId), getMapper());
    }

    public EntityMapper<Price> getMapper() {
        return resultSet -> {
            Map<Integer, BigDecimal> prices = new HashMap<>();
            long routeCarriageId = resultSet.getLong(COLUMN_ROUTE_CARRIAGE_ID);
            resultSet.previous();
            while (resultSet.next()) {
                prices.put(resultSet.getInt(COLUMN_STOP_NUMBER), resultSet.getBigDecimal(COLUMN_PRICE));
            }
            return new Price(routeCarriageId, prices);
        };
    }
}
