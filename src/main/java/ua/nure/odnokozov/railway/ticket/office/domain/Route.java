package ua.nure.odnokozov.railway.ticket.office.domain;

import java.util.List;

public class Route extends Entity {

    private static final long serialVersionUID = -7745170440878209414L;

    private String code;
    private boolean isCanceled;
    private List<Stop> stops;
    private List<RouteCarriage> routeCarriages;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public List<RouteCarriage> getRouteCarriages() {
        return routeCarriages;
    }

    public void setRouteCarriages(List<RouteCarriage> routeCarriages) {
        this.routeCarriages = routeCarriages;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Route [code=").append(code)
                .append(", isCanceled=").append(isCanceled)
                .append(", stops.size()=").append(stops.size())
                .append("]").toString();
    }
}
