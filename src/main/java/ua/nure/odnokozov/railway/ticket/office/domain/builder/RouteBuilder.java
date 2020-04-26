package ua.nure.odnokozov.railway.ticket.office.domain.builder;

import java.util.List;

import ua.nure.odnokozov.railway.ticket.office.domain.Route;
import ua.nure.odnokozov.railway.ticket.office.domain.RouteCarriage;
import ua.nure.odnokozov.railway.ticket.office.domain.Stop;

public class RouteBuilder {

    private Route route;
    
    private RouteBuilder() {
        this.route = new Route();
    }
    
    public static RouteBuilder getInstance() {
        return new RouteBuilder();
    }
    
    public RouteBuilder id(long id) {
        route.setId(id);
        return this;
    }
    
    public RouteBuilder code(String code) {
        route.setCode(code);
        return this;
    }
    
    public RouteBuilder isCanceled(boolean isCanceled) {
        route.setCanceled(isCanceled);
        return this;
    }
    
    public RouteBuilder stops(List<Stop> stops) {
        route.setStops(stops);
        return this;
    }
    
    public RouteBuilder routeCarriages(List<RouteCarriage> routeCarriages) {
        route.setRouteCarriages(routeCarriages);
        return this;
    }
    
    public Route build() {
        return route;
    }
}
