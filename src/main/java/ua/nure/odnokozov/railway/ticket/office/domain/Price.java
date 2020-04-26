package ua.nure.odnokozov.railway.ticket.office.domain;

import java.math.BigDecimal;
import java.util.Map;

public class Price extends Entity {

    private static final long serialVersionUID = 473566900132215839L;

    private long routeCarriageId;
    private Map<Integer, BigDecimal> prices;
    
    public Price() {
    }
    
    public Price(long routeCarriageId, Map<Integer, BigDecimal> prices) {
        this.routeCarriageId = routeCarriageId;
        this.prices = prices;
    }

    public long getRouteCarriageId() {
        return routeCarriageId;
    }

    public void setRouteCarriageId(long routeCarriageId) {
        this.routeCarriageId = routeCarriageId;
    }

    public Map<Integer, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<Integer, BigDecimal> prices) {
        this.prices = prices;
    }

    public void addPrice(Integer stopNumber, BigDecimal price) {
        prices.put(stopNumber, price);
    }
    
    public BigDecimal getStopPrice(Integer stopNumber) {
        return prices.get(stopNumber);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Price[RouteCarriageId =").append(routeCarriageId);
        for (Map.Entry<Integer, BigDecimal> entry : prices.entrySet()) {
            result.append(", stopNumber=").append(entry.getKey()).append(", price =").append(entry.getValue());
        }
        return result.append("]").toString();
    }  
}
